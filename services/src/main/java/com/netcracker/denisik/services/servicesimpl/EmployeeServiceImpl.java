package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.EmployeeConverter;
import com.netcracker.denisik.dao.daoImpl.EmployeeDAOImpl;
import com.netcracker.denisik.dto.EmployeeDTO;
import com.netcracker.denisik.services.AbstractService;
import com.netcracker.denisik.sql.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl extends AbstractService<EmployeeDTO> {
    private EmployeeConverter employeeConverter;
    private static EmployeeServiceImpl instance;

    private EmployeeServiceImpl() {
        employeeConverter = new EmployeeConverter();
    }

    public static EmployeeServiceImpl getInstance() {
        if (instance == null) {
            instance = new EmployeeServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(EmployeeDTO employeeDTO) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            EmployeeDAOImpl.getInstance().add(employeeConverter.convert(employeeDTO));
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
            EmployeeDAOImpl.getInstance().delete(id);
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
    public void update(EmployeeDTO employeeDTO){
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            EmployeeDAOImpl.getInstance().update(employeeConverter.convert(employeeDTO));
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
    public List<EmployeeDTO> getAll(){
        List<EmployeeDTO> employeeDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            employeeDTO=EmployeeDAOImpl.getInstance().getAll().stream()
                    .map(employee -> employeeConverter.convert(employee))
                    .collect(Collectors.toList());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return employeeDTO;
    }

    @Override
    public EmployeeDTO get(int id){
        EmployeeDTO employeeDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            employeeDTO=employeeConverter.convert(EmployeeDAOImpl.getInstance().get(id));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return employeeDTO;
    }
}
