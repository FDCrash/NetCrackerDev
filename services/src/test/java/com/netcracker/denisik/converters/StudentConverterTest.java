package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentConverterTest {
    private StudentEntity studentEntity;
    private StudentDTO studentDTO;
    private StudentConverter converter;

    @Before
    public void setUp() {
        studentEntity = new StudentEntity(new UserEntity(100, Role.ADMIN, "TEST", "PASS")
                , "NAME", 5, 5);
        studentDTO = new StudentDTO(new UserDTO(100, RoleDTO.ADMIN, "TEST", "PASS")
                , "NAME", 5, 5, "Переводится");
        converter = new StudentConverter();
    }

    @After
    public void tearDown() {
        studentDTO = null;
        studentEntity = null;
    }

    @Test
    public void convertTo() {
        StudentDTO studentTestDTO = converter.convert(studentEntity);

        Assert.assertEquals(studentDTO.getId(), studentTestDTO.getId());
        Assert.assertEquals(studentDTO.getRoleDTO(), studentTestDTO.getRoleDTO());
        Assert.assertEquals(studentDTO.getLogin(), studentTestDTO.getLogin());
        Assert.assertEquals(studentDTO.getPassword(), studentTestDTO.getPassword());
        Assert.assertEquals(studentDTO.getName(), studentTestDTO.getName());
        Assert.assertEquals(studentDTO.getStudentId(), studentTestDTO.getStudentId());
        Assert.assertEquals(studentDTO.getGroupId(), studentTestDTO.getGroupId());
        Assert.assertNull(studentTestDTO.getSpeciality());
        Assert.assertEquals(0, studentTestDTO.getWriteBook().size());
    }

    @Test
    public void convertFrom() {
        StudentEntity studentTestEntity = converter.convert(studentDTO);

        Assert.assertEquals(studentEntity.getId(), studentTestEntity.getId());
        Assert.assertEquals(studentEntity.getRole(), studentTestEntity.getRole());
        Assert.assertEquals(studentEntity.getLogin(), studentTestEntity.getLogin());
        Assert.assertEquals(studentEntity.getPassword(), studentTestEntity.getPassword());
        Assert.assertEquals(studentEntity.getName(), studentTestEntity.getName());
        Assert.assertEquals(studentEntity.getStudentId(), studentTestEntity.getStudentId());
        Assert.assertEquals(studentEntity.getGroupId(), studentTestEntity.getGroupId());
        Assert.assertEquals("Переводится", studentTestEntity.getSpecialityEntity().getName());
        Assert.assertEquals(0, studentTestEntity.getWriteBook().getSemester().size());
    }
}
