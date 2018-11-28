package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.entities.SpecialityEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpecialityConverterTest {
    private SpecialityEntity specialityEntity;
    private SpecialityDTO specialityDTO;
    private SpecialityConverter converter;

    @Before
    public void setUp(){
        specialityEntity =new SpecialityEntity(0, "TEST",0);
        specialityDTO = new SpecialityDTO(0,"TEST",null);
        converter=new SpecialityConverter();
    }

    @After
    public void tearDown(){
        specialityDTO =null;
        specialityEntity =null;
    }

    @Test
    public void convertTo(){
        SpecialityDTO specialityTestDTO=converter.convert(specialityEntity);

        Assert.assertEquals(specialityDTO.getId(),specialityTestDTO.getId());
        Assert.assertEquals(specialityDTO.getName(),specialityTestDTO.getName());
        Assert.assertEquals("",specialityTestDTO.getFaculty());
    }

    @Test
    public void convertFrom(){
        SpecialityEntity specialityTestEntity=converter.convert(specialityDTO);

        Assert.assertEquals(specialityEntity.getId(),specialityTestEntity.getId());
        Assert.assertEquals(specialityEntity.getName(),specialityTestEntity.getName());
        Assert.assertNull(specialityTestEntity.getFaculty());
    }
}
