package com.netcracker.denisik.entities;

public class EmployeeEntity extends UserEntity {
    private String name;

    public EmployeeEntity(){}

    public EmployeeEntity(int id,Role role,String login,String pass,String name){
        super(id,role,login,pass);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
