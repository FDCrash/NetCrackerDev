package daomodule.dao.daoImpl;


import daomodule.dao.DAO;
import daomodule.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements DAO<UserEntity> {
    private List<UserEntity> users;

    public UserDAOImpl(){    }

    @Override
    public Optional<UserEntity> get(UserEntity userEntity) {
        return Optional.empty();
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
