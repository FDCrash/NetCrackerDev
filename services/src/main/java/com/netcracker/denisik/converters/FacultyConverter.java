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
        }
        Faculty faculty = new Faculty();
        faculty.setId(facultyDTO.getId());
        faculty.setName(facultyDTO.getName());
        faculty.setSpecialities(specialityEntities);
        for (int i = 0; i < quantity; i++) {
            specialityEntities.get(i).setFaculty(faculty);
        }
        return faculty;
    }

    public FacultyDTO convert(Faculty faculty) {
        List<SpecialityDTO> specilities = new ArrayList<>();
        int quantity= faculty.getSpecialities().size();
        for (int i = 0; i < quantity; i++) {
            SpecialityDTO specialityDTO=new SpecialityDTO();
            specialityDTO.setId(faculty.getSpecialities().get(i).getId());
            specialityDTO.setName(faculty.getSpecialities().get(i).getName());
            specilities.add(specialityDTO);
        }
        FacultyDTO facultyDTO=new FacultyDTO();
        facultyDTO.setId(faculty.getId());
        facultyDTO.setName(faculty.getName());
        facultyDTO.setSpecialities(specilities);
        for(int i=0;i<quantity;i++){
            specilities.get(i).setFaculty(facultyDTO);
        }
        return facultyDTO;
    }
}
