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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
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
        log.debug("Check free name for speciality");
        if (speciality != null) {
            log.error("Speciality exist with name: " + specialityDTO.getName());
            throw new ResourceAlreadyExistException("Speciality exist with name : " + specialityDTO.getName());
        }
        log.debug("Check binding faculty with speciality");
        if (faculty == null) {
            log.error("Faculty not binding with speciality");
            throw new ResourceNotFoundException("Faculty not found");
        }
        log.debug("Add/update speciality :" + specialityDTO.getName());
        return specialityRepository.save(specialityConverter.convert(specialityDTO)).getId();
    }

    @Override
    public void delete(long id) {
        log.debug("Deleting speciality");
        Speciality speciality = specialityRepository.findOne(id);
        if (speciality == null) {
            log.error("Not found speciality for delete by id:" + id);
            throw new ResourceNotFoundException("Deleting speciality by id: " + id);
        }
        specialityRepository.delete(id);
    }

    @Override
    public List<SpecialityDTO> getAll() {
        List<SpecialityDTO> specialityDTOS = StreamSupport.stream(specialityRepository.findAll().spliterator(), false)
                .map(speciality -> specialityConverter.convert(speciality))
                .collect(Collectors.toList());
        log.debug("Getting all specialities from DB");
        return specialityDTOS;
    }

    @Override
    public SpecialityDTO get(long id) {
        log.debug("Start getting speciality by id");
        Speciality speciality = specialityRepository.findOne(id);
        return specialityConverter.convert(speciality);
    }
}
