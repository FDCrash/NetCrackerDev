package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.entities.FacultyEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FacultyConverterTest {
    private FacultyEntity facultyEntity;
    private FacultyDTO facultyDTO;
    private FacultyConverter converter;

    @Before
    public void setUp(){
        facultyEntity =new FacultyEntity(0, "TEST");
        facultyDTO = new FacultyDTO(0,"TEST");
        converter=new FacultyConverter();
    }

    @After
    public void tearDown(){
        facultyDTO =null;
        facultyEntity =null;
    }

    @Test
    public void convertTo(){
        FacultyDTO facultyTestDTO=converter.convert(facultyEntity);

        Assert.assertEquals(facultyDTO.getId(),facultyTestDTO.getId());
        Assert.assertEquals(facultyDTO.getName(),facultyTestDTO.getName());
        Assert.assertEquals(0,facultyTestDTO.getSpecialities().size());
        Assert.assertEquals(0,facultyTestDTO.getSpecialitiesId().size());
    }

    @Test
    public void convertFrom(){
        FacultyEntity facultyTestEntity=converter.convert(facultyDTO);

        Assert.assertEquals(facultyEntity.getId(),facultyTestEntity.getId());
        Assert.assertEquals(facultyEntity.getName(),facultyTestEntity.getName());
        Assert.assertEquals(0,facultyTestEntity.getSpecialities().size());
    }
}
