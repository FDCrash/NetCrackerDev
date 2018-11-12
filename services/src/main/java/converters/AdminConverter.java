package converters;

import daomodule.entities.AdminEntity;
import daomodule.entities.Role;
import dto.AdminDTO;
import dto.RoleDTO;

public class AdminConverter {
    private Role role;
    private RoleDTO roleDTO;

    public AdminEntity convert(AdminDTO adminDTO){
        return new AdminEntity(adminDTO.getId(),(Role) adminDTO.getRoleDTO(),adminDTO.getLogin(),
                adminDTO.getPassword());
    }

    public AdminDTO convert(AdminEntity adminEntity){
        return new AdminDTO(adminEntity.getId(),(RoleDTO) adminEntity.getRole(),adminEntity.getLogin(),
                adminEntity.getPassword());
    }
}
