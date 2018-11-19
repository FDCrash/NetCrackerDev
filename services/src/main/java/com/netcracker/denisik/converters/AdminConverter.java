package com.netcracker.denisik.converters;

import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.dto.AdminDTO;
import com.netcracker.denisik.dto.RoleDTO;

public class AdminConverter {
    private Role role;
    private RoleDTO roleDTO;

    public AdminEntity convert(AdminDTO adminDTO){
        return new AdminEntity(adminDTO.getId(),Role.valueOf(adminDTO.getRoleDTO().name()),adminDTO.getLogin(),
                adminDTO.getPassword(),adminDTO.getStatus());
    }

    public AdminDTO convert(AdminEntity adminEntity){
        return new AdminDTO(adminEntity.getId(),RoleDTO.valueOf(adminEntity.getRole().name()),adminEntity.getLogin(),
                adminEntity.getPassword(),adminEntity.getStatus());
    }
}
