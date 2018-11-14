package daomodule.dao.daoImpl;


import daomodule.dao.DAO;

import daomodule.entities.UserEntity;

import daomodule.storage.UserList;

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
    public void add(UserEntity user) {
    }

    @Override
    public void update(UserEntity user) {
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
