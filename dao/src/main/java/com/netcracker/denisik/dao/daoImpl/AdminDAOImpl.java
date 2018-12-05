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
        AdminEntity adminEntity=new AdminEntity();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ADMIN_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while(result.next()){
                adminEntity.setId(result.getInt(1));
                adminEntity.setRole(Role.valueOf(result.getString(2)));
                adminEntity.setLogin(result.getString(3));
                adminEntity.setPassword(result.getString(4));
                adminEntity.setStatus(result.getBoolean(5));
            }
            SystemLogger.getInstance().logInfo(AdminDAOImpl.class,"Получение админа из бд");
        }catch (SQLException e){
            System.out.println("Проблемы с бд(админ)");
            SystemLogger.getInstance().logError(AdminDAOImpl.class,"Проблемы с бд(админ)");
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
                AdminEntity adminEntity=new AdminEntity();
                adminEntity.setId(result.getInt(1));
                adminEntity.setRole(Role.valueOf(result.getString(2)));
                adminEntity.setLogin(result.getString(3));
                adminEntity.setPassword(result.getString(4));
                adminEntity.setStatus(result.getBoolean(5));
                list.add(adminEntity);
            }
            SystemLogger.getInstance().logInfo(AdminDAOImpl.class,"Получение админов из бд");
        }catch (SQLException e){
            System.out.println("Проблемы с бд(админы)");
            SystemLogger.getInstance().logError(AdminDAOImpl.class,"Проблемы с бд(админы)");
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
            addUser(adminEntity);
            statement = connection.prepareStatement(SqlRequest.ADD_ADMIN);
            statement.setInt(1, adminEntity.getId());
            statement.setBoolean(2, adminEntity.getStatus());
            statement.executeUpdate();
            SystemLogger.getInstance().logInfo(AdminDAOImpl.class,"Добавление админа в бд");
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(админ)");
            SystemLogger.getInstance().logError(AdminDAOImpl.class,"Проблемы с записью бд(админ)");
        } finally {
            ClosingUtil.close(statement);
        }
        return get(adminEntity.getId());
    }

    @Override
    public AdminEntity update(AdminEntity admin) {
        delete(admin.getId());
        add(admin);
        SystemLogger.getInstance().logInfo(AdminDAOImpl.class,"Обновление админа в бд");
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
            SystemLogger.getInstance().logInfo(AdminDAOImpl.class,"Удаление админа из бд");
        }
        catch (SQLException e){
            System.out.println("Проблемы с удалением из бд(админ)");
            SystemLogger.getInstance().logError(AdminDAOImpl.class,"Проблемы с удалинием бд(админ)");
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
            SystemLogger.getInstance().logInfo(AdminDAOImpl.class,"Изменение статуса админа в бд");
        } catch (SQLException e) {
            SystemLogger.getInstance().logError(AdminDAOImpl.class,"Проблемы с изменением статуса в бд(админ)");
            System.out.println("Проблемы с изменением статуса в бд(админ)");
        } finally {
            ClosingUtil.close(statement);
        }
    }
}
