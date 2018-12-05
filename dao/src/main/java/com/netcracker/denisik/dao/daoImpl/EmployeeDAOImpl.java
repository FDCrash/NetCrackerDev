package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.sql.ClosingUtil;
import com.netcracker.denisik.sql.DatabaseConnector;
import com.netcracker.denisik.sql.SqlRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl extends AbstractDao<EmployeeEntity> {
    private static EmployeeDAOImpl instance;

    private EmployeeDAOImpl() {
    }

    public static EmployeeDAOImpl getInstance() {
        if (instance == null) {
            instance = new EmployeeDAOImpl();
        }
        return instance;
    }

    @Override
    public EmployeeEntity get(int id) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_EMPLOYEE_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                employeeEntity.setId(result.getInt(1));
                employeeEntity.setRole(Role.valueOf(result.getString(2)));
                employeeEntity.setLogin(result.getString(3));
                employeeEntity.setPassword(result.getString(4));
                employeeEntity.setName(result.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с чтением бд(сотрудник)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return employeeEntity;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        List<EmployeeEntity> list = new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_EMPLOYEES);
            result = statement.executeQuery();
            while (result.next()) {
                EmployeeEntity employeeEntity=new EmployeeEntity();
                employeeEntity.setId(result.getInt(1));
                employeeEntity.setRole(Role.valueOf(result.getString(2)));
                employeeEntity.setLogin(result.getString(3));
                employeeEntity.setPassword(result.getString(4));
                employeeEntity.setName(result.getString(5));
                list.add(employeeEntity);
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с чтением бд(сотрудники)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return list;
    }

    @Override
    public EmployeeEntity add(EmployeeEntity employeeEntity) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            addUser(employeeEntity);
            statement = connection.prepareStatement(SqlRequest.ADD_EMPLOYEE);
            statement.setInt(1, employeeEntity.getId());
            statement.setString(2, employeeEntity.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(сотрудник)");
        } finally {
            ClosingUtil.close(statement);
        }
        return get(employeeEntity.getId());
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employee) {
        delete(employee.getId());
        add(employee);
        return get(employee.getId());
    }

    @Override
    public void delete(int id) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_EMPLOYEE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
            deleteUser(id);
        } catch (SQLException e) {
            System.out.println("Проблемы с удалением из бд(сотрудник)");
        } finally {
            ClosingUtil.close(statement);
        }
    }
}
