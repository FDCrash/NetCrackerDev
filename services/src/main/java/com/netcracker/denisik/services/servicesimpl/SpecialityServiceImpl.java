package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.SpecialityConverter;
import com.netcracker.denisik.dao.daoImpl.SpecialityDAOImpl;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.services.AbstractService;
import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.sql.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

public class SpecialityServiceImpl extends AbstractService<SpecialityDTO> {
    private SpecialityConverter specialityConverter;
    private static SpecialityServiceImpl instance;

    private SpecialityServiceImpl(){
        specialityConverter = new SpecialityConverter();
    }

    public static SpecialityServiceImpl getInstance(){
        if(instance == null){
            instance = new SpecialityServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(SpecialityDTO specialityDTO) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            SpecialityDAOImpl.getInstance().add(specialityConverter.convert(specialityDTO));
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
            SpecialityDAOImpl.getInstance().delete(id);
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
    public void update(SpecialityDTO specialityDTO) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            SpecialityDAOImpl.getInstance().update(specialityConverter.convert(specialityDTO));
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
    public List<SpecialityDTO> getAll() {
        List<SpecialityDTO> specialityDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            specialityDTO=SpecialityDAOImpl.getInstance().getAll().stream()
                    .map(speciality -> specialityConverter.convert(speciality))
                    .collect(Collectors.toList());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return specialityDTO;
    }

    @Override
    public SpecialityDTO get(int id){
        SpecialityDTO specialityDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            specialityDTO=specialityConverter.convert(SpecialityDAOImpl.getInstance().get(id));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return specialityDTO;
    }

    public int generateId(int bound) {
        SplittableRandom splittableRandom = new SplittableRandom();
        int id=-1;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            do {
                id = splittableRandom.nextInt(1, bound);

            } while (SpecialityDAOImpl.getInstance().get(id) != null);
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
