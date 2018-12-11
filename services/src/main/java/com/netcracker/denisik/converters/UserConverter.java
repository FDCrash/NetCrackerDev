package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User convert(UserDTO userDTO) {
        User user =new User();
        user.setId(userDTO.getId());
        user.setRole(Role.valueOf(userDTO.getRoleDTO().name()));
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        return user;
    }

    public UserDTO convert(User user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRoleDTO(RoleDTO.valueOf(user.getRole().name()));
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        return userDTO;
    }
}
