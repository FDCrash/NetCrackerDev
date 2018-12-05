package com.netcracker.denisik.entities;

import javax.persistence.Entity;

@Entity
public class UserEntity extends BaseEntity {
    private Role role;
    private String password;
    private String login;

    public UserEntity() {
    }

    public UserEntity(UserEntity userEntity) {
        super(userEntity.getId());
        role = userEntity.role;
        login = userEntity.login;
        password = userEntity.password;
    }

    public UserEntity(int id, Role role, String login, String password) {
        super(id);
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

