package com.netcracker.denisik.converters;

import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.dto.AdminDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.entities.UserEntity;

public class AdminConverter {

    public AdminEntity convert(AdminDTO adminDTO){
        return new AdminEntity(new UserEntity(adminDTO.getId(),Role.valueOf(adminDTO.getRoleDTO().name()),adminDTO.getLogin(),
                adminDTO.getPassword()),adminDTO.getStatus());
    }

    public AdminDTO convert(AdminEntity adminEntity){
        return new AdminDTO(adminEntity.getId(),RoleDTO.valueOf(adminEntity.getRole().name()),adminEntity.getLogin(),
                adminEntity.getPassword(),adminEntity.getStatus());
    }
}
