package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.SpecialityEntity;

import java.util.ArrayList;
import java.util.List;

public class FacultyConverter {
    public FacultyEntity convert(FacultyDTO facultyDTO) {
        List<SpecialityEntity> specialityEntities = new ArrayList<>();
        int quantity;
        quantity = facultyDTO.getSpecialities().size();
        for (int i = 0; i < quantity; i++) {
            SpecialityEntity specialityEntity=new SpecialityEntity();
            specialityEntity.setId(facultyDTO.getSpecialitiesId().get(i));
            specialityEntity.setName(facultyDTO.getSpecialities().get(i));
        }
        FacultyEntity facultyEntity = new FacultyEntity(facultyDTO.getId(),
                facultyDTO.getName(), specialityEntities);
        facultyEntity.setId(facultyDTO.getId());
        facultyEntity.setName(facultyDTO.getName());
        facultyEntity.setSpecialities(specialityEntities);
        for (int i = 0; i < quantity; i++) {
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
        FacultyDTO facultyDTO=new FacultyDTO();
        facultyDTO.setId(facultyEntity.getId());
        facultyDTO.setName(facultyEntity.getName());
        facultyDTO.setSpecialities(specilities);
        facultyDTO.setSpecialitiesId(specialitiesId);
        return facultyDTO;
    }
}
