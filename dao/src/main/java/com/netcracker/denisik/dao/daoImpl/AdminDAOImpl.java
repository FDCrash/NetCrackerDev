package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.SystemLogger;
import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.sql.ClosingUtil;
import com.netcracker.denisik.sql.DatabaseConnector;
import com.netcracker.denisik.sql.SqlRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl extends AbstractDao<AdminEntity> {
    private static AdminDAOImpl instance;


    private AdminDAOImpl(){}

    public static AdminDAOImpl getInstance(){
        if(instance==null){
            instance = new AdminDAOImpl();
        }
        return instance;
    }

    @Override
    public AdminEntity get(int id) {
        AdminEntity adminEntity=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ADMIN_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while(result.next()){
                adminEntity=new AdminEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3),result.getString(4)),
                        result.getBoolean(5));
            }
            SystemLogger.getInstance().logInfo(getClass(),"Получение админа из бд");
        }catch (SQLException e){
            System.out.println("Проблемы с бд(админ)");
            SystemLogger.getInstance().logError(getClass(),"Проблемы с бд(админ)");
        }finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return adminEntity;
    }

    @Override
    public List<AdminEntity> getAll() {
        List<AdminEntity> list=new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_ADMINS);
            result = statement.executeQuery();
            while(result.next()){
                list.add(new AdminEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3),result.getString(4)),
                        result.getBoolean(5)));
            }
            SystemLogger.getInstance().logInfo(getClass(),"Получение админов из бд");
        }catch (SQLException e){
            System.out.println("Проблемы с бд(админы)");
            SystemLogger.getInstance().logError(getClass(),"Проблемы с бд(админы)");
        }finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return list;
    }

    @Override
    public AdminEntity add(AdminEntity adminEntity) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            addUser(new UserEntity(adminEntity.getId(),adminEntity.getRole(),
                    adminEntity.getLogin(),adminEntity.getPassword()));
            statement = connection.prepareStatement(SqlRequest.ADD_ADMIN);
            statement.setInt(1, adminEntity.getId());
            statement.setBoolean(2, adminEntity.getStatus());
            statement.executeUpdate();
            SystemLogger.getInstance().logInfo(getClass(),"Добавление админа в бд");
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(админ)");
            SystemLogger.getInstance().logError(getClass(),"Проблемы с записью бд(админ)");
        } finally {
            ClosingUtil.close(statement);
        }
        return get(adminEntity.getId());
    }

    @Override
    public AdminEntity update(AdminEntity admin) {
        delete(admin.getId());
        add(admin);
        SystemLogger.getInstance().logInfo(getClass(),"Обновление админа в бд");
        return get(admin.getId());
    }

    @Override
    public void delete(int id) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_ADMIN_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
            deleteUser(id);
            SystemLogger.getInstance().logInfo(getClass(),"Удаление админа из бд");
        }
        catch (SQLException e){
            System.out.println("Проблемы с удалением из бд(админ)");
            SystemLogger.getInstance().logError(getClass(),"Проблемы с удалинием бд(админ)");
        } finally {
            ClosingUtil.close(statement);
        }
    }

    public void changeStatus(String login) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.CHANGE_STATUS);
            statement.setString(1, login);
            statement.executeUpdate();
            SystemLogger.getInstance().logInfo(getClass(),"Изменение статуса админа в бд");
        } catch (SQLException e) {
            SystemLogger.getInstance().logError(getClass(),"Проблемы с изменением статуса в бд(админ)");
            System.out.println("Проблемы с изменением статуса в бд(админ)");
        } finally {
            ClosingUtil.close(statement);
        }
    }
}
