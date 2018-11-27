package com.netcracker.denisik.dto;

public class AdminDTO extends UserDTO {
    private boolean status;

    public AdminDTO() {
    }

    public AdminDTO(UserDTO userDTO, boolean status) {
        super(userDTO);
        this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public String toString() {
        return new UserDTO(this).toString() + "\nСтатус: " + getStatus() + "\n";
    }
}