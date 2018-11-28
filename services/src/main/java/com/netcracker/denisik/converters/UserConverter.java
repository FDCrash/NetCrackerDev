package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.UserEntity;

public class UserConverter {
    public UserEntity convert(UserDTO userDTO) {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setRole(Role.valueOf(userDTO.getRoleDTO().name()));
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }

    public UserDTO convert(UserEntity userEntity) {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setRoleDTO(RoleDTO.valueOf(userEntity.getRole().name()));
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        return userDTO;
    }
}
