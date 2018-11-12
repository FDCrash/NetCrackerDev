package daomodule.entities;

public class AdminEntity extends UserEntity {
    private boolean status;

    public AdminEntity(){}

    public AdminEntity(int id,Role role,String login,String pass){
        super(id,role,login,pass);
        this.status=false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }
}
