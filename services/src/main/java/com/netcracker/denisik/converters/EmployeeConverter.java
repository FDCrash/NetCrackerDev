package com.netcracker.denisik.converters;

import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.dto.EmployeeDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.entities.UserEntity;

public class EmployeeConverter {
    public EmployeeEntity convert(EmployeeDTO employeeDTO){
        return new EmployeeEntity(new UserEntity(employeeDTO.getId(), Role.valueOf(employeeDTO.getRoleDTO().name()),employeeDTO.getLogin(),
                employeeDTO.getPassword()),employeeDTO.getName());
    }

    public EmployeeDTO convert(EmployeeEntity employeeEntity){
        return new EmployeeDTO(employeeEntity.getId(), RoleDTO.valueOf(employeeEntity.getRole().name()),employeeEntity.getLogin(),
                employeeEntity.getPassword(),employeeEntity.getName());
    }
}
