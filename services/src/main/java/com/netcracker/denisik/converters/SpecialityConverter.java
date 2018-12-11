package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.entities.Faculty;
import com.netcracker.denisik.entities.Speciality;
import org.springframework.stereotype.Component;

@Component
public class SpecialityConverter {
    public Speciality convert(SpecialityDTO specialityDTO) {
        Faculty faculty = new Faculty();
        faculty.setId(specialityDTO.getFaculty().getId());
        faculty.setName(specialityDTO.getFaculty().getName());
        Speciality speciality = new Speciality();
        speciality.setId(specialityDTO.getId());
        speciality.setName(specialityDTO.getName());
        speciality.setFaculty(faculty);
        faculty.setSpeciality(speciality);
        return speciality;
    }

    public SpecialityDTO convert(Speciality speciality) {
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(speciality.getFaculty().getId());
        facultyDTO.setName(speciality.getFaculty().getName());
        SpecialityDTO specialityDTO = new SpecialityDTO();
        specialityDTO.setId(speciality.getId());
        specialityDTO.setName(speciality.getName());
        specialityDTO.setFaculty(facultyDTO);
        facultyDTO.setSpeciality(specialityDTO);
        return specialityDTO;
    }
}