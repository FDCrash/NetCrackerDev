package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.EmployeeDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.UserEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeConverterTest {
    private EmployeeEntity employeeEntity;
    private EmployeeDTO employeeDTO;
    private EmployeeConverter converter;

    @Before
    public void setUp(){
        employeeEntity =new EmployeeEntity(new UserEntity(100,Role.ADMIN,"TEST","PASS"),"TEXT");
        employeeDTO = new EmployeeDTO(new UserDTO(100,RoleDTO.ADMIN,"TEST","PASS"),"TEXT");
        converter=new EmployeeConverter();
    }

    @After
    public void tearDown(){
        employeeDTO =null;
        employeeEntity =null;
    }

    @Test
    public void convertTo(){
        EmployeeDTO employeeTestDTO=converter.convert(employeeEntity);

        Assert.assertEquals(employeeDTO.getId(),employeeTestDTO.getId());
        Assert.assertEquals(employeeDTO.getRoleDTO(),employeeTestDTO.getRoleDTO());
        Assert.assertEquals(employeeDTO.getLogin(),employeeTestDTO.getLogin());
        Assert.assertEquals(employeeDTO.getPassword(),employeeTestDTO.getPassword());
        Assert.assertEquals(employeeDTO.getName(),employeeTestDTO.getName());
    }

    @Test
    public void convertFrom(){
        EmployeeEntity employeeTestEntity=converter.convert(employeeDTO);

        Assert.assertEquals(employeeEntity.getId(),employeeTestEntity.getId());
        Assert.assertEquals(employeeEntity.getRole(),employeeTestEntity.getRole());
        Assert.assertEquals(employeeEntity.getLogin(),employeeTestEntity.getLogin());
        Assert.assertEquals(employeeEntity.getPassword(),employeeTestEntity.getPassword());
        Assert.assertEquals(employeeEntity.getName(),employeeTestEntity.getName());
    }
}
