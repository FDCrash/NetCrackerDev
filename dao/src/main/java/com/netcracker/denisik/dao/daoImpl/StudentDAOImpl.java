package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.dao.IDao;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.entities.WriteBook;
import com.netcracker.denisik.sql.Database;
import com.netcracker.denisik.sql.SqlRequest;
import com.netcracker.denisik.storage.StudentList;
import com.netcracker.denisik.storage.UserList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            connection = Database.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_STUDENT_BY_ID);
            statement.setInt(1,id);
            result = statement.executeQuery();
            while(result.next()){
                studentEntity=new StudentEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3),result.getString(4)),
                        result.getString(5),result.getInt(6),
                        result.getInt(7),SpecialityDAOImpl.getInstance()
                        .get(result.getInt(8)),getStudentWriteBook(result.getInt(6)));
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
            connection = Database.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_STUDENTS);
            result = statement.executeQuery();
            while(result.next()){
                list.add(new StudentEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3),result.getString(4)),
                        result.getString(5),result.getInt(6),
                        result.getInt(7),SpecialityDAOImpl.getInstance()
                        .get(result.getInt(8)),getStudentWriteBook(result.getInt(6))));
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
        StudentList.getInstance().add(studentEntity);
        UserList.getInstance().addStudent(studentEntity);
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

    public List<WriteBook> getStudentWriteBook(int id){
        List<WriteBook> list=new ArrayList<>();
        PreparedStatement statementBook=null;
        ResultSet resultBook=null;
        try {
            connection = Database.getInstance().getConnection();
            statementBook = connection.prepareStatement(SqlRequest.GET_WRITEBOOK_BY_STUD_ID);
            statementBook.setInt(1,id);
            resultBook = statementBook.executeQuery();
            int semester;
            List<String> subjects=new ArrayList<>();
            List<Integer> marks=new ArrayList<>();
            resultBook.next();
            semester=resultBook.getInt(1);
            resultBook.previous();
            while(resultBook.next()){
                if(semester==resultBook.getInt(1)){
                    subjects.add(resultBook.getString(2));
                    marks.add(resultBook.getInt(3));
                }else {
                    list.add(new WriteBook(semester, subjects, marks));
                    subjects.clear();
                    marks.clear();
                    semester=resultBook.getInt(1);
                    subjects.add(resultBook.getString(2));
                    marks.add(resultBook.getInt(3));
                }
                if(resultBook.isLast()){
                    list.add(new WriteBook(semester, subjects, marks));
                }
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(студенты)");
        }finally {
            try {
                statementBook.close();
                resultBook.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(студенты)");
            }
        }
        return list;
    }
}
