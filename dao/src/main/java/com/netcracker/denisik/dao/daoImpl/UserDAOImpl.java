package com.netcracker.denisik.dao.daoImpl;


import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.dao.IDao;
import com.netcracker.denisik.entities.*;
import com.netcracker.denisik.sql.Database;
import com.netcracker.denisik.sql.SqlRequest;
import com.netcracker.denisik.storage.UserList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDao <UserEntity>{
    private static UserDAOImpl instance;
    private UserDAOImpl(){}

    public static UserDAOImpl getInstance(){
        if(instance==null){
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public UserEntity get(int id) {
        UserEntity userEntity=null;
        try {
            connection = Database.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_USER_BY_ID);
            statement.setInt(1,id);
            result = statement.executeQuery();
            while(result.next()){
                userEntity =new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3),result.getString(4));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(пользователь)");
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(пользователь)");
            }
        }
        return userEntity;
    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> list=new ArrayList<>();
        try {
            connection = Database.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_USERS);
            result = statement.executeQuery();
            while(result.next()){
                list.add(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3),result.getString(4)));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(пользователи)");
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(пользователи)");
            }
        }
        return list;
    }

    @Override
    public UserEntity add(UserEntity userEntity) {
        switch (userEntity.getRole()) {
            case ADMIN:
                AdminDAOImpl.getInstance().add(new AdminEntity(new UserEntity(userEntity), false));
                break;
            case EMPLOYEE:
                EmployeeDAOImpl.getInstance().add(new EmployeeEntity(new UserEntity(userEntity), ""));
                break;
            case STUDENT:
                StudentDAOImpl.getInstance().add(new StudentEntity(new UserEntity(userEntity),
                        "", 0, 0, 0, new ArrayList<>()));
        }
        return get(userEntity.getId());
    }

    @Override
    public UserEntity update(UserEntity user) {
        int k = user.getId();
        switch (user.getRole()) {
            case ADMIN:
                AdminDAOImpl.getInstance().update(new AdminEntity(new UserEntity(user),
                        AdminDAOImpl.getInstance().get(k).getStatus()));
                break;
            case EMPLOYEE:
                EmployeeDAOImpl.getInstance().update(new EmployeeEntity(new UserEntity(user),
                        EmployeeDAOImpl.getInstance().get(k).getName()));
                break;
            case STUDENT:
                StudentDAOImpl.getInstance().update(new StudentEntity(new UserEntity(user),
                        StudentDAOImpl.getInstance().get(k).getName(), StudentDAOImpl.getInstance().get(k).getStudentId(),
                        StudentDAOImpl.getInstance().get(k).getGroupId(), StudentDAOImpl.getInstance().get(k).getSpecialityEntity().getId(),
                        StudentDAOImpl.getInstance().get(k).getWriteBook()));
        }
        return get(user.getId());
    }

    @Override
    public void delete(int id) {
        switch (get(id).getRole()) {
            case ADMIN:
                AdminDAOImpl.getInstance().delete(id);
                break;
            case EMPLOYEE:
                EmployeeDAOImpl.getInstance().delete(id);
                break;
            case STUDENT:
                StudentDAOImpl.getInstance().delete(id);
        }
    }

    public Enum checkLoginPass(String login, String pass) {
        return getAll().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(pass))
                .findFirst().get().getRole();
    }

    public boolean checkLogin(String login) {
        return getAll().stream()
                .anyMatch(user -> user.getLogin().equals(login));
    }
}
