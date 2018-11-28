package com.netcracker.denisik.converters;

import com.netcracker.denisik.dao.daoImpl.FacultyDAOImpl;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.SpecialityEntity;

public class SpecialityConverter {
    public SpecialityEntity convert(SpecialityDTO specialityDTO) {
        SpecialityEntity specialityEntity = new SpecialityEntity();
        specialityEntity.setId(specialityDTO.getId());
        specialityEntity.setName(specialityDTO.getName());
        for (FacultyEntity facultyEntity : FacultyDAOImpl.getInstance().getAll()) {
            if (facultyEntity.getName().equals(specialityDTO.getFaculty())) {
                specialityEntity.setFaculty(facultyEntity);
                break;
            }
        }
        return specialityEntity;
    }

    public SpecialityDTO convert(SpecialityEntity specialityEntity) {
        SpecialityDTO specialityDTO=new SpecialityDTO();
        specialityDTO.setId(specialityEntity.getId());
        specialityDTO.setName(specialityEntity.getName());
        specialityDTO.setFaculty(specialityEntity.getFaculty().getName());
        return specialityDTO;
    }
}