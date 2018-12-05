package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.entities.Semester;
import com.netcracker.denisik.sql.ClosingUtil;
import com.netcracker.denisik.sql.DatabaseConnector;
import com.netcracker.denisik.sql.SqlRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDAOImpl extends AbstractDao<StudentEntity> {
    private static StudentDAOImpl instance;

    private StudentDAOImpl() {
    }

    public static StudentDAOImpl getInstance() {
        if (instance == null) {
            instance = new StudentDAOImpl();
        }
        return instance;
    }

    @Override
    public StudentEntity get(int id) {
        StudentEntity studentEntity = new StudentEntity();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_STUDENT_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                studentEntity.setId(result.getInt(1));
                studentEntity.setRole(Role.valueOf(result.getString(2)));
                studentEntity.setLogin(result.getString(3));
                studentEntity.setPassword(result.getString(4));
                studentEntity.setName(result.getString(5));
                studentEntity.setStudentId(result.getInt(6));
                studentEntity.setGroupId(result.getInt(7));
                studentEntity.setSpecialityEntity(SpecialityDAOImpl.getInstance()
                        .get(result.getInt(8)));
                studentEntity.setWriteBook(WriteBookDAO.getInstance().get(result.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(студент)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return studentEntity;
    }

    @Override
    public List<StudentEntity> getAll() {
        List<StudentEntity> list = new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_STUDENTS);
            result = statement.executeQuery();
            while (result.next()) {
                StudentEntity studentEntity=new StudentEntity();
                studentEntity.setId(result.getInt(1));
                studentEntity.setRole(Role.valueOf(result.getString(2)));
                studentEntity.setLogin(result.getString(3));
                studentEntity.setPassword(result.getString(4));
                studentEntity.setName(result.getString(5));
                studentEntity.setStudentId(result.getInt(6));
                studentEntity.setGroupId(result.getInt(7));
                studentEntity.setSpecialityEntity(SpecialityDAOImpl.getInstance()
                        .get(result.getInt(8)));
                studentEntity.setWriteBook(WriteBookDAO.getInstance().get(result.getInt(6)));
                list.add(studentEntity);
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Проблемы с бд(студенты)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return list;
    }

    @Override
    public StudentEntity add(StudentEntity studentEntity) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            if (studentEntity.getLogin().equals("")) {
                studentEntity.setLogin(null);
            }
            addUser(studentEntity);
            statement = connection.prepareStatement(SqlRequest.ADD_STUDENT);
            statement.setInt(1, studentEntity.getId());
            statement.setString(2, studentEntity.getName());
            statement.setInt(3, studentEntity.getStudentId());
            statement.setInt(4, studentEntity.getGroupId());
            statement.setInt(5, studentEntity.getSpecialityEntity().getId());
            statement.executeUpdate();
            for (Semester semester : studentEntity.getWriteBook().getSemester()) {
                WriteBookDAO.getInstance().add(semester, studentEntity.getStudentId());
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(студент)");
        } finally {
            ClosingUtil.close(statement);
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
        try {
            if(id!=0) {
                WriteBookDAO.getInstance().delete(get(id).getStudentId());
            }
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_STUDENT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
            deleteUser(id);
        } catch (SQLException e) {
            System.out.println("Проблемы с удалением из бд(студент)");
        } finally {
            ClosingUtil.close(statement);
        }
    }

    public StudentEntity getByLogin(String login) {
        return getAll().stream()
                .filter(student -> student.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public boolean checkStudId(int id) {
        return getAll().stream()
                .anyMatch(student -> student.getStudentId() == id);
    }

    public void addNewLoginPass(int id, String login, String pass) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.NEW_LOGIN_PASS);
            statement.setString(1, login);
            statement.setString(2, pass);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с изменением LoginPass из бд(студент)");
        } finally {
            ClosingUtil.close(statement);
        }
    }

    public List<StudentEntity> getAllByGroup(int id) {
        return getAll().stream()
                .filter(student -> student.getGroupId() == id)
                .collect(Collectors.toList());
    }

    public List<StudentEntity> getAllBySpeciality(String speciality) {
        return getAll().stream()
                .filter(student -> student.getSpecialityEntity().getName().equals(speciality))
                .collect(Collectors.toList());
    }

    public void setDefaultSpeciality(int id){
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.SET_DEFAULT_SPECIALITY);
            statement.setInt(1, 0);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с изменением id спец из бд(студент)");
        } finally {
            ClosingUtil.close(statement);
        }
    }

}
