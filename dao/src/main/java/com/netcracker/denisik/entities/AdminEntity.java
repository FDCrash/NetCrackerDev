package com.netcracker.denisik.entities;

import javax.persistence.Entity;

@Entity
public class AdminEntity extends UserEntity {
    private boolean status;

    public AdminEntity() {
    }

    public AdminEntity(UserEntity userEntity, boolean status) {
        super(userEntity);
        this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
}
