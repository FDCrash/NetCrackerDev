package com.netcracker.denisik.dto;

public class AdminDTO extends UserDTO {
    private boolean status;

    public AdminDTO(){}

    public AdminDTO(int id, RoleDTO roleDTO, String login, String pass, boolean status){
        super(id, roleDTO,login,pass);
        this.status=status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }

    public String toString(){
        return "Логин: " +getLogin() + "\nРоль: " + getRoleDTO().name()
                + "\nСтатус: " + getStatus() + "\n";
    }
}