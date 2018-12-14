package com.netcracker.denisik.dto;

public class UserDTO extends BaseDTO {
    private RoleDTO roleDTO;
    private String password;
    private String login;
    private String name;

    public UserDTO() {
    }

    public UserDTO(long id, RoleDTO roleDTO, String password, String login, String name) {
        super(id);
        this.roleDTO = roleDTO;
        this.password = password;
        this.login = login;
        this.name = name;
    }

    public UserDTO(UserDTO userDTO) {
        super(userDTO.getId());
        this.roleDTO = userDTO.roleDTO;
        this.password = userDTO.password;
        this.login = userDTO.login;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Логин:" + getLogin() + "\nРоль:"
                + getRoleDTO().name() + "\n" + "Имя: " + getName() + "\n";
    }
}
