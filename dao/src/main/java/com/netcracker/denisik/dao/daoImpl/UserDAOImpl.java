package com.netcracker.denisik.dao.daoImpl;


import com.netcracker.denisik.dao.DAO;

import com.netcracker.denisik.entities.UserEntity;

import com.netcracker.denisik.storage.UserList;

import java.util.List;

public class UserDAOImpl implements DAO<UserEntity> {

    public UserDAOImpl(){

    }

    @Override
    public UserEntity get(int id) {
        return null;
    }

    @Override
    public List<UserEntity> getAll() {
        return UserList.getInstance().get();
    }

    @Override
    public UserEntity add(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return null;
    }


    @Override
    public void delete(int id) {

    }

    public boolean checkId(int id){

        return true;
    }

    public Enum checkLoginPass(String login,String pass){

        return null;
    }

    public boolean checkLogin(String login){

        return true;
    }
}
