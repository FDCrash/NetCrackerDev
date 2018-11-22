package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.UserEntity;

public class UserConverter {
    public UserEntity convert(UserDTO userDTO) {
        return new UserEntity(userDTO.getId(), Role.valueOf(userDTO.getRoleDTO().name()), userDTO.getLogin(),
                userDTO.getPassword());
    }

    public UserDTO convert(UserEntity userEntity) {
        return new UserDTO(userEntity.getId(), RoleDTO.valueOf(userEntity.getRole().name()), userEntity.getLogin(),
                userEntity.getPassword());
    }
}
