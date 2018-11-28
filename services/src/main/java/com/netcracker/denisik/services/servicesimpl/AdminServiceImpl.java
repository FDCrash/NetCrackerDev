package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.AdminConverter;
import com.netcracker.denisik.dao.daoImpl.AdminDAOImpl;
import com.netcracker.denisik.dto.AdminDTO;
import com.netcracker.denisik.services.AbstractService;
import com.netcracker.denisik.sql.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl extends AbstractService<AdminDTO> {
    private AdminConverter adminConverter;
    private static AdminServiceImpl instance;

    private AdminServiceImpl(){
        adminConverter = new AdminConverter();
    }

    public static AdminServiceImpl getInstance(){
        if(instance == null){
            instance = new AdminServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(AdminDTO adminDTO) {
        try {
            connection= DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            AdminDAOImpl.getInstance().add(adminConverter.convert(adminDTO));
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        try {
            connection= DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            AdminDAOImpl.getInstance().delete(id);
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void update(AdminDTO adminDTO) {
        try {
            connection= DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            AdminDAOImpl.getInstance().update(adminConverter.convert(adminDTO));
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public List<AdminDTO> getAll(){
        List<AdminDTO> adminDTO = null;
        try {
            connection= DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
             adminDTO= AdminDAOImpl.getInstance()
                    .getAll().stream()
                    .map(admin -> adminConverter.convert(admin)).collect(Collectors.toList());
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return adminDTO;
    }

    @Override
    public AdminDTO get(int id) {
        AdminDTO adminDTO = null;
        try {
            connection= DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            adminDTO= adminConverter.convert(AdminDAOImpl.getInstance().get(id));
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return adminDTO;
    }

    public void changeStatusAdmin(String login) {
        AdminDTO adminDTO = null;
        try {
            connection= DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            AdminDAOImpl.getInstance().changeStatus(login);
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
