package daomodule.dao.daoImpl;


import daomodule.dao.DAO;
import daomodule.entities.Role;
import daomodule.entities.UserEntity;
import daomodule.storage.UserList;

import java.util.List;

public class UserDAOImpl implements DAO<UserEntity> {

    public UserDAOImpl(){
    }

    @Override
    public UserEntity get(int id) {
        return UserList.getInstance().get().get(id);
    }

    @Override
    public List<UserEntity> getAll() {
        return UserList.getInstance().get();
    }

    @Override
    public void add(UserEntity userEntity) {
        UserList.getInstance().add(userEntity);
    }

    @Override
    public void update(UserEntity user) {
        for(UserEntity userEntity:getAll()){
            if(user.getId()==userEntity.getId()){
                userEntity.setLogin(user.getLogin());
                userEntity.setPassword(user.getPassword());
                userEntity.setRole(user.getRole());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        if(!UserList.getInstance().get().get(id).getRole().equals(Role.ADMIN)){
            UserList.getInstance().get().remove(id);
        }
    }

    public void deleteById(int id){
        for(UserEntity userEntity:UserList.getInstance().get()){
            if(userEntity.getId()==id){
                UserList.getInstance().get().remove(userEntity);
            }
        }
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
