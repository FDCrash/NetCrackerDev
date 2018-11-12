package converters;

import daomodule.entities.Role;
import daomodule.entities.UserEntity;
import dto.RoleDTO;
import dto.UserDTO;

public class UserConverter {
    public UserEntity convert(UserDTO userDTO){
        return new UserEntity(userDTO.getId(), (Role) userDTO.getRoleDTO(),userDTO.getLogin(),
                userDTO.getPassword());
    }

    public UserDTO convert(UserEntity userEntity){
        return new UserDTO(userEntity.getId(),(RoleDTO)userEntity.getRole(),userEntity.getLogin(),
                userEntity.getPassword());
    }
}
