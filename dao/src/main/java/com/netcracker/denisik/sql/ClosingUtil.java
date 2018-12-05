package com.netcracker.denisik.sql;

import com.netcracker.denisik.SystemLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClosingUtil {
    private ClosingUtil(){}

    public static void close(PreparedStatement statement){
        if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException e){
                System.out.println();
                SystemLogger.getInstance().logError(ClosingUtil.class, "Ошибка при закрытии запроса");
            }
        }
    }

    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }
            catch(SQLException e){
                System.out.println();
                SystemLogger.getInstance().logError(ClosingUtil.class, "Ошибка при закрытии resultSet");
            }
        }
    }

}
