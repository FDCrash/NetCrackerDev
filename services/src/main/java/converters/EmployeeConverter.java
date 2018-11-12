package converters;

import daomodule.entities.EmployeeEntity;
import daomodule.entities.Role;
import dto.EmployeeDTO;
import dto.RoleDTO;

public class EmployeeConverter {
    public EmployeeEntity convert(EmployeeDTO employeeDTO){
        return new EmployeeEntity(employeeDTO.getId(), (Role) employeeDTO.getRoleDTO(),employeeDTO.getLogin(),
                employeeDTO.getPassword(),employeeDTO.getName());
    }

    public EmployeeDTO convert(EmployeeEntity employeeEntity){
        return new EmployeeDTO(employeeEntity.getId(), (RoleDTO) employeeEntity.getRole(),employeeEntity.getLogin(),
                employeeEntity.getPassword(),employeeEntity.getName());
    }
}
