package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.AdminDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.UserEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdminConverterTest {
    private AdminEntity adminEntity;
    private AdminDTO adminDTO;
    private AdminConverter converter;

    @Before
    public void setUp(){
        adminEntity=new AdminEntity(new UserEntity(100,Role.ADMIN,"TEST","PASS"),true);
        adminDTO= new AdminDTO(new UserDTO(100,RoleDTO.ADMIN,"TEST","PASS"),true);
        converter=new AdminConverter();
    }

    @After
    public void tearDown(){
        adminDTO=null;
        adminEntity=null;
    }

    @Test
    public void convertTo(){
        AdminDTO adminTestDTO=converter.convert(adminEntity);

        Assert.assertEquals(adminDTO.getId(),adminTestDTO.getId());
        Assert.assertEquals(adminDTO.getRoleDTO(),adminTestDTO.getRoleDTO());
        Assert.assertEquals(adminDTO.getLogin(),adminTestDTO.getLogin());
        Assert.assertEquals(adminDTO.getPassword(),adminTestDTO.getPassword());
        Assert.assertTrue(adminTestDTO.getStatus());
    }

    @Test
    public void convertFrom(){
        AdminEntity adminTestEntity=converter.convert(adminDTO);

        Assert.assertEquals(adminEntity.getId(),adminTestEntity.getId());
        Assert.assertEquals(adminEntity.getRole(),adminTestEntity.getRole());
        Assert.assertEquals(adminEntity.getLogin(),adminTestEntity.getLogin());
        Assert.assertEquals(adminEntity.getPassword(),adminTestEntity.getPassword());
        Assert.assertTrue(adminTestEntity.getStatus());
    }
}
