package daomodule.storage;

import daomodule.entities.AdminEntity;
import daomodule.entities.EmployeeEntity;
import daomodule.entities.StudentEntity;
import daomodule.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserList {
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users=users;
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
