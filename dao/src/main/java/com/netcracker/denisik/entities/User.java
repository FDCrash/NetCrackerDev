package com.netcracker.denisik.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private Role role;
    private String password;
    private String login;
    private String name;

    public User() {
    }

    public User(Long id, Role role, String login, String password, String name) {
        this.id=id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.name=name;
    }

    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    @Column(name = "login",unique = true)
    public String getLogin() {
        return login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

