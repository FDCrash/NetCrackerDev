package converters;

import dao.entities.AdminEntity;
import dao.entities.Role;
import dto.AdminDTO;
import dto.RoleDTO;

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
