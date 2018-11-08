package daomodule.entities;

public class AdminEntity extends UserEntity {
    private boolean status;

    public AdminEntity(int id,Role role,String login,String pass,boolean status){
        this.setId(id);
        this.setRole(role);
        this.setLogin(login);
        this.setPassword(pass);
        this.status=status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }
}
