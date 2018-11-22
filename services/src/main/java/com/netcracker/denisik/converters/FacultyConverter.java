package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.SpecialityEntity;

import java.util.ArrayList;
import java.util.List;

public class FacultyConverter {
    public FacultyEntity convert(FacultyDTO facultyDTO) {
        List<SpecialityEntity> specialityEntities = new ArrayList<>();
        int n = 0;
        n = facultyDTO.getSpecialities().size();
        for (int i = 0; i < n; i++) {
            specialityEntities.add(new SpecialityEntity(facultyDTO.getSpecialitiesId().get(i),
                    facultyDTO.getSpecialities().get(i), facultyDTO.getId()));
        }
        FacultyEntity facultyEntity = new FacultyEntity(facultyDTO.getId(),
                facultyDTO.getName(), specialityEntities);
        for (int i = 0; i < n; i++) {
            specialityEntities.get(i).setFaculty(facultyEntity);
        }
        return facultyEntity;
    }

    public FacultyDTO convert(FacultyEntity facultyEntity) {
        List<String> specilities = new ArrayList<>();
        List<Integer> specialitiesId = new ArrayList<>();
        for (int i = 0; i < facultyEntity.getSpecialities().size(); i++) {
            specilities.add(facultyEntity.getSpecialities().get(i).getName());
            specialitiesId.add(facultyEntity.getSpecialities().get(i).getId());
        }
        return new FacultyDTO(facultyEntity.getId(), facultyEntity.getName(), specilities, specialitiesId);
    }
}
