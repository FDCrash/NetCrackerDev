package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.sql.DatabaseConnector;
import com.netcracker.denisik.sql.SqlRequest;
import com.netcracker.denisik.storage.EmployeeList;
import com.netcracker.denisik.storage.UserList;

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
        EmployeeEntity employeeEntity = null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_EMPLOYEE_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                employeeEntity = new EmployeeEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3), result.getString(4)),
                        result.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с чтением бд(сотрудник)");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с чтением закрытием(сотрудник)");
            }
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
                list.add(new EmployeeEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3), result.getString(4)),
                        result.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с чтением бд(сотрудники)");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с чтением закрытием(сотрудники)");
            }
        }
        return list;
    }

    @Override
    public EmployeeEntity add(EmployeeEntity employeeEntity) {
        try {
            addUser(new UserEntity(employeeEntity.getId(),employeeEntity.getRole(),
                    employeeEntity.getLogin(),employeeEntity.getName()));
            statement = connection.prepareStatement(SqlRequest.ADD_EMPLOYEE);
            statement.setInt(1, employeeEntity.getId());
            statement.setString(2, employeeEntity.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(сотрудник)");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием записи в бд(сотрудник)");
            }
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
        getAll().remove(get(id));
        UserDAOImpl.getInstance().getAll().remove(UserDAOImpl.getInstance().get(id));
    }
}
