package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.entities.WriteBook;
import com.netcracker.denisik.sql.DatabaseConnector;
import com.netcracker.denisik.sql.SqlRequest;
import com.netcracker.denisik.storage.StudentList;
import com.netcracker.denisik.storage.UserList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDAOImpl extends AbstractDao<StudentEntity> {
    private static StudentDAOImpl instance;
    private StudentDAOImpl(){}

    public static StudentDAOImpl getInstance(){
        if(instance==null){
            instance = new StudentDAOImpl();
        }
        return instance;
    }

    @Override
    public StudentEntity get(int id) {
        StudentEntity studentEntity=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_STUDENT_BY_ID);
            statement.setInt(1,id);
            result = statement.executeQuery();
            while(result.next()){
                studentEntity=new StudentEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3),result.getString(4)),
                        result.getString(5),result.getInt(6),
                        result.getInt(7),SpecialityDAOImpl.getInstance()
                        .get(result.getInt(8)),WriteBookDAO.getInstance().get(result.getInt(6)));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(студенты)");
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(студенты)");
            }
        }
        return studentEntity;
    }

    @Override
    public List<StudentEntity> getAll() {
        List<StudentEntity> list=new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_STUDENTS);
            result = statement.executeQuery();
            while(result.next()){
                list.add(new StudentEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3),result.getString(4)),
                        result.getString(5),result.getInt(6),
                        result.getInt(7),SpecialityDAOImpl.getInstance()
                        .get(result.getInt(8)),WriteBookDAO.getInstance().get(result.getInt(6))));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(студенты)");
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(студенты)");
            }
        }
        return list;
    }

    @Override
    public StudentEntity add(StudentEntity studentEntity) {
        try {
            addUser(new UserEntity(studentEntity.getId(),studentEntity.getRole(),
                    studentEntity.getLogin(),studentEntity.getName()));
            statement = connection.prepareStatement(SqlRequest.ADD_STUDENT);
            statement.setInt(1, studentEntity.getId());
            statement.setString(2, studentEntity.getName());
            statement.setInt(3,studentEntity.getStudentId());
            statement.setInt(4,studentEntity.getGroupId());
            statement.setInt(5,studentEntity.getSpecialityEntity().getId());
            statement.executeUpdate();
            for(WriteBook writeBook:studentEntity.getWriteBook()) {
                WriteBookDAO.getInstance().add(writeBook,studentEntity.getId());
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(сотрудник)");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием записи в бд(сотрудник)");
            }
        }
        return get(studentEntity.getId());
    }

    @Override
    public StudentEntity update(StudentEntity student) {
        delete(student.getId());
        add(student);
        return get(student.getId());
    }

    @Override
    public void delete(int id) {
        getAll().remove(get(id));
        UserDAOImpl.getInstance().getAll().remove(UserDAOImpl.getInstance().get(id));
    }

    public StudentEntity getByLogin(String login) {
        return getAll().stream()
                .filter(student-> student.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public boolean checkStudId(int id) {
        return getAll().stream()
                .anyMatch(student-> student.getStudentId()==id);
    }

    public void addNewLoginPass(int id, String login, String pass) {
        int k = 0;
        for (StudentEntity studentEntity : getAll()) {
            if (studentEntity.getStudentId() == id) {
                studentEntity.setLogin(login);
                studentEntity.setPassword(pass);
                k = studentEntity.getId();
                break;
            }
        }
        for (UserEntity userEntity : UserList.getInstance().get()) {
            if (userEntity.getId() == k) {
                userEntity.setLogin(login);
                userEntity.setPassword(pass);
                break;
            }
        }
    }

    public List<StudentEntity> getAllByGroup(int id) {
        return getAll().stream()
                .filter(student -> student.getGroupId()==id)
                .collect(Collectors.toList());
    }

    public List<StudentEntity> getAllBySpeciality(String speciality) {
        return getAll().stream()
                .filter(student -> student.getSpecialityEntity().getName().equals(speciality))
                .collect(Collectors.toList());
    }

}
