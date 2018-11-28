package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.AdminDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.entities.Role;

public class AdminConverter {

    public AdminEntity convert(AdminDTO adminDTO) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setId(adminDTO.getId());
        adminEntity.setRole(Role.valueOf(adminDTO.getRoleDTO().name()));
        adminEntity.setLogin(adminDTO.getLogin());
        adminEntity.setPassword(adminDTO.getPassword());
        adminEntity.setStatus(adminDTO.getStatus());
        return adminEntity;
    }

    public AdminDTO convert(AdminEntity adminEntity) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(adminEntity.getId());
        adminDTO.setRoleDTO(RoleDTO.valueOf(adminEntity.getRole().name()));
        adminDTO.setLogin(adminEntity.getLogin());
        adminDTO.setPassword(adminEntity.getPassword());
        adminDTO.setStatus(adminEntity.getStatus());
        return adminDTO;
    }
}
