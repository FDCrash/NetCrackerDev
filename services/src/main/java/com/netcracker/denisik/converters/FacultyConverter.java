package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.entities.Faculty;
import com.netcracker.denisik.entities.Speciality;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FacultyConverter {
    public Faculty convert(FacultyDTO facultyDTO) {
        List<Speciality> specialityEntities = new ArrayList<>();
        int quantity;
        quantity = facultyDTO.getSpecialities().size();
        for (int i = 0; i < quantity; i++) {
            Speciality speciality =new Speciality();
            speciality.setId(facultyDTO.getSpecialities().get(i).getId());
            speciality.setName(facultyDTO.getSpecialities().get(i).getName());
            specialityEntities.add(
                    Speciality.builder()
                    .id(facultyDTO.getSpecialities().get(i).getId())
                    .name(facultyDTO.getSpecialities().get(i).getName())
                    .build());
        }
        Faculty faculty=Faculty.builder()
                .id(facultyDTO.getId())
                .name(facultyDTO.getName())
                .specialities(specialityEntities)
                .build();
        for (int i = 0; i < quantity; i++) {
            specialityEntities.get(i).setFaculty(faculty);
        }
        return faculty;
    }

    public FacultyDTO convert(Faculty faculty) {
        List<SpecialityDTO> specialityDTOS = new ArrayList<>();
        int quantity= faculty.getSpecialities().size();
        for (int i = 0; i < quantity; i++) {
            SpecialityDTO specialityDTO=new SpecialityDTO();
            specialityDTO.setId(faculty.getSpecialities().get(i).getId());
            specialityDTO.setName(faculty.getSpecialities().get(i).getName());
            specialityDTOS.add(
                    SpecialityDTO.builder()
                    .id(faculty.getSpecialities().get(i).getId())
                    .name(faculty.getSpecialities().get(i).getName())
                    .build());
        }
        FacultyDTO facultyDTO=FacultyDTO.builder()
                .id(faculty.getId())
                .name(faculty.getName())
                .specialities(specialityDTOS)
                .build();
        for(int i=0;i<quantity;i++){
            specialityDTOS.get(i).setFaculty(facultyDTO);
        }
        return facultyDTO;
    }
}
