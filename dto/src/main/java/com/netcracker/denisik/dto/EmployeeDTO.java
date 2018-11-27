package com.netcracker.denisik.dto;

public class EmployeeDTO extends UserDTO {
    private String name;

    public EmployeeDTO() {
    }

    public EmployeeDTO(UserDTO userDTO, String name) {
        super(userDTO);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "\nИмя: " + getName() + new UserDTO(this).toString();
    }
}
