package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.sql.DatabaseConnector;
import com.netcracker.denisik.sql.SqlRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements IDao<T> {
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet result;

    public void addUser(UserEntity userEntity) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.ADD_USER);
            statement.setInt(1, userEntity.getId());
            statement.setString(2, userEntity.getRole().name());
            statement.setString(3, userEntity.getLogin());
            statement.setString(4, userEntity.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(пользователь)");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием записи(пользователь)");
            }
        }
    }

    public void deleteUser(int id) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_USER_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(пользователь)");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием записи(пользователь)");
            }
        }
    }
}
