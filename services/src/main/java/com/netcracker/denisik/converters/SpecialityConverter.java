package com.netcracker.denisik.converters;

import com.netcracker.denisik.dao.daoImpl.FacultyDAOImpl;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.SpecialityEntity;

public class SpecialityConverter {
    public SpecialityEntity convert(SpecialityDTO specialityDTO) {
        SpecialityEntity specialityEntity = new SpecialityEntity(specialityDTO.getId(),
                specialityDTO.getName(), 0);
        for (FacultyEntity facultyEntity : new FacultyDAOImpl().getAll()) {
            if (facultyEntity.getName().equals(specialityDTO.getFaculty())) {
                specialityEntity.setFaculty(facultyEntity);
                break;
            }
        }
        return specialityEntity;
    }

    public SpecialityDTO convert(SpecialityEntity specialityEntity) {
        return new SpecialityDTO(specialityEntity.getId(), specialityEntity.getName()
                , specialityEntity.getFaculty().getName());
    }
}