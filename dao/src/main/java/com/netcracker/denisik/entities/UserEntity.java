package com.netcracker.denisik.entities;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;

        UserEntity that = (UserEntity) o;

        if (getRole() != that.getRole()) return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        return getLogin() != null ? getLogin().equals(that.getLogin()) : that.getLogin() == null;
    }

    @Override
    public int hashCode() {
        int result = getRole().hashCode();
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        return result;
    }
}

