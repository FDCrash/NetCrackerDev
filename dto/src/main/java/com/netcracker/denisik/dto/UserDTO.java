package com.netcracker.denisik.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class UserDTO extends BaseDTO{
    private RoleDTO roleDTO;
    private String password;
    private String login;
    private String name;

    @Builder
    public UserDTO(long id, RoleDTO roleDTO, String password, String login, String name) {
        super(id);
        this.roleDTO = roleDTO;
        this.password = password;
        this.login = login;
        this.name = name;
    }
}
