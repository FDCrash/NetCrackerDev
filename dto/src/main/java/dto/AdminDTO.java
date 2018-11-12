package dto;

public class AdminDTO extends UserDTO {
    private boolean status;

    public AdminDTO(){}

    public AdminDTO(int id, RoleDTO roleDTO, String login, String pass){
        super(id, roleDTO,login,pass);
        this.status=false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }

}
