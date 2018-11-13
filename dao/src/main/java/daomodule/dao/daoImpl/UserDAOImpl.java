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
    private List<UserEntity> users;

    public UserDAOImpl(){    }

    @Override
    public UserEntity get(int id) {
        return users.get(id);
    }

    @Override
    public List<UserEntity> getAll() {
        return UserList.getInstance().getUsers();
    }

    public List<AdminEntity> getAdmins(){
        return AdminList.getInstance().getAdmins();
    }

    public List<StudentEntity> getStudents(){
        return StudentList.getInstance().getStudents();
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
