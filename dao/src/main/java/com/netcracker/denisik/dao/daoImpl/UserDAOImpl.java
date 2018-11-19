package com.netcracker.denisik.dao.daoImpl;


import com.netcracker.denisik.dao.DAO;
import com.netcracker.denisik.storage.EmployeeList;
import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.storage.StudentList;
import com.netcracker.denisik.storage.UserList;

import java.util.List;

public class UserDAOImpl implements DAO<UserEntity> {

    public UserDAOImpl(){
    }

    @Override
    public UserEntity get(int id) {
        return getAll().get(id);
    }

    @Override
    public List<UserEntity> getAll() {
        return UserList.getInstance().get();
    }

    @Override
    public UserEntity add(UserEntity userEntity) {
        UserList.getInstance().add(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity update(UserEntity user) {
        for(UserEntity userEntity:getAll()){
            if(user.getId()==userEntity.getId()){
                userEntity.setLogin(user.getLogin());
                userEntity.setPassword(user.getPassword());
                userEntity.setRole(user.getRole());
                break;
            }
        }
        return user;
    }

    @Override
    public void delete(int id) {
        UserEntity userEntity=getAll().get(id);
        if(userEntity.getRole().equals(Role.STUDENT)){
            for (StudentEntity studentEntity:StudentList.getInstance().get()) {
                if(userEntity.getId()==studentEntity.getId()) {
                    StudentList.getInstance().get().remove(studentEntity);
                    break;
                }
            }
        }
        if(userEntity.getRole().equals(Role.EMPLOYEE)){
            for (EmployeeEntity employeeEntity:EmployeeList.getInstance().get()) {
                if(userEntity.getId()==employeeEntity.getId()) {
                    EmployeeList.getInstance().get().remove(employeeEntity);
                    break;
                }
            }
        }
        getAll().remove(id);
    }

    public void deleteById(int id){
        for(UserEntity userEntity:getAll()){
            if(userEntity.getId()==id){
                getAll().remove(userEntity);
                break;
            }
        }
    }

    public boolean checkId(int id){
        for(UserEntity userEntity:getAll()){
            if(userEntity.getId()==id){
                return false;
            }
        }
        return true;
    }

    public Enum checkLoginPass(String login,String pass){
        for(UserEntity userEntity: getAll()){
            if(userEntity.getLogin().equals(login) && userEntity.getPassword().equals(pass)){
                return userEntity.getRole();
            }
        }
        return null;
    }

    public boolean checkLogin(String login){
        for(UserEntity userEntity: getAll()){
            if(userEntity.getLogin().equals(login)){
                return false;
            }
        }
        return true;
    }
}
