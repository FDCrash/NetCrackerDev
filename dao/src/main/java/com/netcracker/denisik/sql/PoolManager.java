package com.netcracker.denisik.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PoolManager {
    private static PoolManager instance;


    private PoolManager() {
    }

    public static PoolManager getInstance(){
        if(instance == null){
            instance = new PoolManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        return DriverManager.getConnection(url, user, pass);
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            try{
                connection.close();
            }
            catch(SQLException e){
                System.out.println("Невозможно закрыть пул");
            }
        }
    }

//    public static Connection getConnection() throws SQLException, ClassNotFoundException {
//        ResourceBundle resource = ResourceBundle.getBundle("database");
//        String url = resource.getString("db.url");
//        String user = resource.getString("db.user");
//        String pass = resource.getString("db.password");
//        return DriverManager.getConnection(url + "?useSSL=false", user, pass);
//    }
}
