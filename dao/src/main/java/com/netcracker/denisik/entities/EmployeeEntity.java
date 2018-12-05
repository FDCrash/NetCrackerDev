package com.netcracker.denisik.entities;

import javax.persistence.Entity;

@Entity
public class EmployeeEntity extends UserEntity {
    private String name;

    public EmployeeEntity() {
    }

    public EmployeeEntity(UserEntity userEntity, String name) {
        super(userEntity);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
