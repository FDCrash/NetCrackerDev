package com.netcracker.denisik.converters;

import com.netcracker.denisik.dao.FacultyRepository;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.SpecialityEntity;

import java.util.stream.StreamSupport;

public class SpecialityConverter {
    private final FacultyRepository facultyRepository;

    public SpecialityConverter(FacultyRepository facultyRepository){
        this.facultyRepository=facultyRepository;
    }

    public SpecialityEntity convert(SpecialityDTO specialityDTO) {
        SpecialityEntity specialityEntity = new SpecialityEntity();
        specialityEntity.setId(specialityDTO.getId());
        specialityEntity.setName(specialityDTO.getName());
        StreamSupport.stream(facultyRepository.findAll().spliterator(),false)
                .filter(facultyEntity -> facultyEntity.getName().equals(specialityDTO.getFaculty()))
                .findFirst().get();
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