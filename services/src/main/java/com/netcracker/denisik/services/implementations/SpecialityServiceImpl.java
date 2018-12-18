package com.netcracker.denisik.services.implementations;

import com.netcracker.denisik.converters.SpecialityConverter;
import com.netcracker.denisik.dao.FacultyRepository;
import com.netcracker.denisik.dao.SpecialityRepository;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.entities.Faculty;
import com.netcracker.denisik.entities.Speciality;
import com.netcracker.denisik.exteption.ResourceAlreadyExistException;
import com.netcracker.denisik.exteption.ResourceNotFoundException;
import com.netcracker.denisik.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class SpecialityServiceImpl implements CrudService<SpecialityDTO> {
    private SpecialityConverter specialityConverter;
    private SpecialityRepository specialityRepository;
    private FacultyRepository facultyRepository;

    @Autowired
    public SpecialityServiceImpl(SpecialityRepository specialityRepository, SpecialityConverter specialityConverter,
                                 FacultyRepository facultyRepository) {
        this.specialityConverter = specialityConverter;
        this.specialityRepository = specialityRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public long add(SpecialityDTO specialityDTO) {
        Speciality speciality = specialityRepository.getByName(specialityDTO.getName());
        Faculty faculty = facultyRepository.findOne(specialityDTO.getFacultyId());
        if (speciality != null) {
            throw new ResourceAlreadyExistException("Speciality exist with name : " + specialityDTO.getName());
        } else if (faculty == null) {
            throw new ResourceNotFoundException("Faculty not found");
        }
        return specialityRepository.save(specialityConverter.convert(specialityDTO)).getId();
    }

    @Override
    public void delete(long id) {
        Speciality speciality=specialityRepository.findOne(id);
        if (speciality == null) {
            throw new ResourceNotFoundException("Deleting speciality by id: " + id);
        }
        specialityRepository.delete(id);
    }

    @Override
    public List<SpecialityDTO> getAll() {
        List<SpecialityDTO> specialityDTOS = StreamSupport.stream(specialityRepository.findAll().spliterator(), false)
                .map(speciality -> specialityConverter.convert(speciality))
                .collect(Collectors.toList());
        log.debug("");
        return specialityDTOS;
    }

    @Override
    public SpecialityDTO get(long id) {
        Speciality speciality = specialityRepository.findOne(id);
        return specialityConverter.convert(speciality);
    }
}
