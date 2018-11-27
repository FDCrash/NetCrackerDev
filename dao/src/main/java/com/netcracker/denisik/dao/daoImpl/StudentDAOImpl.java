package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.entities.WriteBook;
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
        StudentEntity studentEntity = null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_STUDENT_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                studentEntity = new StudentEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3), result.getString(4)),
                        result.getString(5), result.getInt(6),
                        result.getInt(7), SpecialityDAOImpl.getInstance()
                        .get(result.getInt(8)), WriteBookDAO.getInstance().get(result.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(студент)");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием(студент)");
            }
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
                list.add(new StudentEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3), result.getString(4)),
                        result.getString(5), result.getInt(6),
                        result.getInt(7), SpecialityDAOImpl.getInstance()
                        .get(result.getInt(8)), WriteBookDAO.getInstance().get(result.getInt(6))));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(студенты)");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием(студенты)");
            }
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
            addUser(new UserEntity(studentEntity.getId(), studentEntity.getRole(),
                    studentEntity.getLogin(), studentEntity.getPassword()));
            statement = connection.prepareStatement(SqlRequest.ADD_STUDENT);
            statement.setInt(1, studentEntity.getId());
            statement.setString(2, studentEntity.getName());
            statement.setInt(3, studentEntity.getStudentId());
            statement.setInt(4, studentEntity.getGroupId());
            statement.setInt(5, studentEntity.getSpecialityEntity().getId());
            statement.executeUpdate();
            for (WriteBook writeBook : studentEntity.getWriteBook()) {
                WriteBookDAO.getInstance().add(writeBook, studentEntity.getStudentId());
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(студент)");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием записи в бд(студент)");
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
        try {
            WriteBookDAO.getInstance().delete(get(id).getStudentId());
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_STUDENT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
            deleteUser(id);
        } catch (SQLException e) {
            System.out.println("Проблемы с удалением из бд(студент)");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием удаления в бд(студент)");
            }
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
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием LoginPass в бд(студент)");
            }
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

}
