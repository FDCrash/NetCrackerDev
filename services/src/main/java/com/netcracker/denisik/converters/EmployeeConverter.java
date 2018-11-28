package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.EmployeeDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.UserEntity;

public class EmployeeConverter {
    public EmployeeEntity convert(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity=new EmployeeEntity();
        employeeEntity.setId(employeeDTO.getId());
        employeeEntity.setRole(Role.valueOf(employeeDTO.getRoleDTO().name()));
        employeeEntity.setLogin(employeeDTO.getLogin());
        employeeEntity.setPassword(employeeDTO.getPassword());
        employeeEntity.setName(employeeDTO.getName());
        return employeeEntity;
    }

    public EmployeeDTO convert(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setId(employeeEntity.getId());
        employeeDTO.setRoleDTO(RoleDTO.valueOf(employeeEntity.getRole().name()));
        employeeDTO.setLogin(employeeEntity.getLogin());
        employeeDTO.setPassword(employeeEntity.getPassword());
        employeeDTO.setName(employeeEntity.getName());
        return employeeDTO;
    }
}
