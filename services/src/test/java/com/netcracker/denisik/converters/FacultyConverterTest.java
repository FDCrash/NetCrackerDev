package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.entities.Faculty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FacultyConverterTest {
    private Faculty faculty;
    private FacultyDTO facultyDTO;
    private FacultyConverter converter;

    @Before
    public void setUp(){
        faculty =new Faculty(0, "TEST");
        facultyDTO = new FacultyDTO(0,"TEST");
        converter=new FacultyConverter();
    }

    @After
    public void tearDown(){
        facultyDTO =null;
        faculty =null;
    }

    @Test
    public void convertTo(){

    }

    @Test
    public void convertFrom(){

    }
}
