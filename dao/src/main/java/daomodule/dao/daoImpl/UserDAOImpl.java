package daomodule.dao.daoImpl;


import daomodule.dao.DAO;
import daomodule.entities.AdminEntity;
import daomodule.entities.StudentEntity;
import daomodule.entities.UserEntity;
import daomodule.storage.AdminList;
import daomodule.storage.StudentList;
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
        for(UserEntity userEntity:UserList.getInstance().get()){
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
