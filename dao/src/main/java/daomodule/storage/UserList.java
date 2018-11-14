package daomodule.storage;

import daomodule.entities.AdminEntity;
import daomodule.entities.EmployeeEntity;
import daomodule.entities.StudentEntity;
import daomodule.entities.UserEntity;

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
        this.users=users;
    }

    @Override
    public void add(UserEntity userEntity) {
        this.users.add(userEntity);
    }

    public void setStudents(List<StudentEntity> students){
        this.users.addAll(students);
    }

    public void setEmployees(List<EmployeeEntity> employees){
        this.users.addAll(employees);
    }

    public void setAdmins(List<AdminEntity> admins){
        this.users.addAll(admins);
    }
}
