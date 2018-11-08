package daomodule.entities;

public class UserEntity extends BaseEntity{
    private Role role;
    private String password;
    private String login;

    public UserEntity(){}

    public void setRole(Role role) {
        this.role = role;
    }

    public Enum getRole(){return role;}

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

