package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.FacultyConverter;
import com.netcracker.denisik.dao.daoImpl.FacultyDAOImpl;
import com.netcracker.denisik.dao.daoImpl.SpecialityDAOImpl;
import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.services.AbstractService;
import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.sql.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

public class FacultyServiceImpl extends AbstractService<FacultyDTO> {
    private FacultyConverter facultyConverter;
    private static FacultyServiceImpl instance;

    private FacultyServiceImpl(){
        facultyConverter = new FacultyConverter();
    }

    public static FacultyServiceImpl getInstance(){
        if(instance == null){
            instance = new FacultyServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(FacultyDTO facultyDTO) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            FacultyDAOImpl.getInstance().add(facultyConverter.convert(facultyDTO));
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
            FacultyDAOImpl.getInstance().delete(id);
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
    public void update(FacultyDTO facultyDTO) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            FacultyDAOImpl.getInstance().update(facultyConverter.convert(facultyDTO));
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
    public List<FacultyDTO> getAll() {
        List<FacultyDTO> facultyDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            facultyDTO=FacultyDAOImpl.getInstance().getAll().stream()
                    .map(faculty -> facultyConverter.convert(faculty))
                    .collect(Collectors.toList());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return facultyDTO;
    }

    @Override
    public FacultyDTO get(int id){
        FacultyDTO facultyDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            facultyDTO=facultyConverter.convert(FacultyDAOImpl.getInstance().get(id));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return facultyDTO;
    }

    public int generateId(int bound){
        SplittableRandom splittableRandom = new SplittableRandom();
        int id=-1;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            do {
                id = splittableRandom.nextInt(1, bound);
            } while (FacultyDAOImpl.getInstance().get(id) != null);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return id;
    }
}
