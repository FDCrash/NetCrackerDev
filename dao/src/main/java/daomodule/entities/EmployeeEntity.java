package daomodule.entities;

public class EmployeeEntity extends UserEntity {
    private String name;

    public EmployeeEntity(int id,Role role,String login,String pass,String name){
        this.setId(id);
        this.setRole(role);
        this.setLogin(login);
        this.setPassword(pass);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
