package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.entities.Faculty;
import com.netcracker.denisik.entities.Speciality;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class SpecialityConverter {
    public Speciality convert(SpecialityDTO specialityDTO) {
        Faculty faculty = Faculty.builder()
                .id(specialityDTO.getFaculty().getId())
                .name(specialityDTO.getFaculty().getName())
                .build();
        Speciality speciality = Speciality.builder()
                .id(specialityDTO.getId())
                .name(specialityDTO.getName())
                .faculty(faculty)
                .build();
        faculty.setSpecialities(Collections.singletonList(speciality));
        return speciality;

    }

    public SpecialityDTO convert(Speciality speciality) {
        FacultyDTO facultyDTO = FacultyDTO.builder()
                .id(speciality.getFaculty().getId())
                .name(speciality.getFaculty().getName())
                .build();
        SpecialityDTO specialityDTO = SpecialityDTO.builder()
                .id(speciality.getId())
                .name(speciality.getName())
                .faculty(facultyDTO)
                .build();
        facultyDTO.setSpecialities(Collections.singletonList(specialityDTO));
        return specialityDTO;
    }
}