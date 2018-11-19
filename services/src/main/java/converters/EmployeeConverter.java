package converters;

import dao.entities.EmployeeEntity;
import dao.entities.Role;
import dto.EmployeeDTO;
import dto.RoleDTO;

public class EmployeeConverter {
    public EmployeeEntity convert(EmployeeDTO employeeDTO){
        return new EmployeeEntity(employeeDTO.getId(), Role.valueOf(employeeDTO.getRoleDTO().name()),employeeDTO.getLogin(),
                employeeDTO.getPassword(),employeeDTO.getName());
    }

    public EmployeeDTO convert(EmployeeEntity employeeEntity){
        return new EmployeeDTO(employeeEntity.getId(), RoleDTO.valueOf(employeeEntity.getRole().name()),employeeEntity.getLogin(),
                employeeEntity.getPassword(),employeeEntity.getName());
    }
}
