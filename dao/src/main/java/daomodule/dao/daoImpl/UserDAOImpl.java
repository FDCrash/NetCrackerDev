package daomodule.dao.daoImpl;


import daomodule.dao.DAO;
import daomodule.entities.UserEntity;

import java.util.List;

public class UserDAOImpl implements DAO<UserEntity> {
    private List<UserEntity> users;

    public UserDAOImpl(){    }

    @Override
    public UserEntity get(int id) {
        return users.get(id);
    }

    @Override
    public List<UserEntity> getAll() {
        return users;
    }

    @Override
    public void add(UserEntity user) {
    }

    @Override
    public void update(UserEntity user) {

    }

    @Override
    public void delete(UserEntity user) {

    }
}
