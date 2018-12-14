//package com.netcracker.denisik.converters;
//
//import com.netcracker.denisik.dto.RoleDTO;
//import com.netcracker.denisik.dto.UserDTO;
//import com.netcracker.denisik.entities.Role;
//import com.netcracker.denisik.entities.User;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//public class UserConverterTest {
//    private User userEntity;
//    private UserDTO userDTO;
//    private UserConverter converter;
//
//    @Before
//    public void setUp(){
//        userEntity =new User(100,Role.ADMIN,"TEST","PASS");
//        userDTO =new UserDTO(100,RoleDTO.ADMIN,"TEST","PASS");
//        converter=new UserConverter();
//    }
//
//    @After
//    public void tearDown(){
//        userDTO =null;
//        userEntity =null;
//    }
//
//    @Test
//    public void convertTo(){
//        UserDTO userTestDTO=converter.convert(userEntity);
//
//        Assert.assertEquals(userDTO.getId(),userTestDTO.getId());
//        Assert.assertEquals(userDTO.getRoleDTO(),userTestDTO.getRoleDTO());
//        Assert.assertEquals(userDTO.getLogin(),userTestDTO.getLogin());
//        Assert.assertEquals(userDTO.getPassword(),userTestDTO.getPassword());
//    }
//
//    @Test
//    public void convertFrom(){
//        User userTestEntity=converter.convert(userDTO);
//
//        Assert.assertEquals(userEntity.getId(),userTestEntity.getId());
//        Assert.assertEquals(userEntity.getRole(),userTestEntity.getRole());
//        Assert.assertEquals(userEntity.getLogin(),userTestEntity.getLogin());
//        Assert.assertEquals(userEntity.getPassword(),userTestEntity.getPassword());
//    }
//}
