package daomodule.entities;

public class AdminEntity extends UserEntity {
    private boolean status;

    public AdminEntity(){}
    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }
}
