package converters;

import dao.entities.Role;
import dao.entities.UserEntity;
import dto.RoleDTO;
import dto.UserDTO;

public class UserConverter {
    public UserEntity convert(UserDTO userDTO){
        return new UserEntity(userDTO.getId(), Role.valueOf(userDTO.getRoleDTO().name()),userDTO.getLogin(),
                userDTO.getPassword());
    }

    public UserDTO convert(UserEntity userEntity){
        return new UserDTO(userEntity.getId(),RoleDTO.valueOf(userEntity.getRole().name()),userEntity.getLogin(),
                userEntity.getPassword());
    }
}
