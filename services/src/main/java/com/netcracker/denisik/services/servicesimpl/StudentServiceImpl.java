package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.StudentConverter;
import com.netcracker.denisik.dao.daoImpl.StudentDAOImpl;
import com.netcracker.denisik.dao.daoImpl.UserDAOImpl;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.services.AbstractService;
import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.sql.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl extends AbstractService<StudentDTO> {
    private StudentConverter studentConverter;
    private static StudentServiceImpl instance;

    private StudentServiceImpl(){
        studentConverter = new StudentConverter();
    }

    public static StudentServiceImpl getInstance(){
        if(instance == null){
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(StudentDTO studentDTO) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            StudentDAOImpl.getInstance().add(studentConverter.convert(studentDTO));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id){
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            StudentDAOImpl.getInstance().delete(id);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public StudentDTO getByLogin(String login) {
        StudentDTO studentDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            studentDTO=studentConverter.convert(StudentDAOImpl.getInstance().getByLogin(login));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return studentDTO;
    }

    public List<StudentDTO> getAllByGroup(int number){
        List<StudentDTO> studentDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            studentDTO=StudentDAOImpl.getInstance().getAllByGroup(number)
                    .stream().map(student -> studentConverter.convert(student))
                    .collect(Collectors.toList());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return studentDTO;
    }

    public List<StudentDTO> getAllBySpeciality(String speciality){
        List<StudentDTO> studentDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            studentDTO=StudentDAOImpl.getInstance().getAllBySpeciality(speciality)
                    .stream().map(student -> studentConverter.convert(student))
                    .collect(Collectors.toList());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return studentDTO;
    }

    @Override
    public void update(StudentDTO studentDTO) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            StudentDAOImpl.getInstance().update(studentConverter.convert(studentDTO));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Override
    public List<StudentDTO> getAll(){
        List<StudentDTO> studentDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            studentDTO=StudentDAOImpl.getInstance().getAll().stream()
                    .map(student -> studentConverter.convert(student))
                    .collect(Collectors.toList());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return studentDTO;
    }

    @Override
    public StudentDTO get(int id){
        StudentDTO studentDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            studentDTO=studentConverter.convert(StudentDAOImpl.getInstance().get(id));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return studentDTO;
    }
}
