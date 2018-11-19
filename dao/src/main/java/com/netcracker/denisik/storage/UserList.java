package com.netcracker.denisik.storage;

import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;

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

    public void addAdmin(AdminEntity adminEntity) {
        this.users.add(adminEntity);
    }

    public void addEmployee(EmployeeEntity employeeEntity) {
        this.users.add(employeeEntity);
    }

    public void addStudent(StudentEntity studentEntity) {
        this.users.add(studentEntity);
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
