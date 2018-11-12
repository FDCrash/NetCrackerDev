package converters;

import daomodule.entities.Role;
import daomodule.entities.UserEntity;
import dto.RoleDTO;
import dto.UserDTO;

public class UserConverter {
    public UserEntity convert(UserDTO userDTO){
        UserEntity userEntity=new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setRole((Role)userDTO.getRoleDTO());
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }

    public UserDTO convert(UserEntity userEntity){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setRoleDTO((RoleDTO)userEntity.getRole());
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        return userDTO;
    }
}
