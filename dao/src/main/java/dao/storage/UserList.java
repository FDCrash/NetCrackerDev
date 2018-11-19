package dao.storage;

import dao.entities.AdminEntity;
import dao.entities.EmployeeEntity;
import dao.entities.StudentEntity;
import dao.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserList implements Storage<UserEntity>{
    private static UserList instance;
    private List<UserEntity> users=new ArrayList<>();

    private UserList(){
    }

    public static UserList getInstance() {
        if(instance==null){
            instance=new UserList();
        }
        return instance;
    }

    @Override
    public List<UserEntity> get() {
        return users;
    }

    @Override
    public void set(List<UserEntity> users) {

    }

    @Override
    public void add(UserEntity userEntity) {

    }

    public void setStudents(List<StudentEntity> students){

    }

    public void setEmployees(List<EmployeeEntity> employees){

    }

    public void setAdmins(List<AdminEntity> admins){

    }
}
