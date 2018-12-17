package com.netcracker.denisik.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class UserDTO extends UserFormDTO{
    private RoleDTO roleDTO;

    @Builder
    public UserDTO(long id, RoleDTO roleDTO, String password, String login, String name) {
        super(id, password, login, name);
        this.roleDTO = roleDTO;
    }
}
