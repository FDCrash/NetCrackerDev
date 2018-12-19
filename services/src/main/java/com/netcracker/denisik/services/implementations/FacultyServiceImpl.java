package com.netcracker.denisik.services.implementations;

import com.netcracker.denisik.converters.FacultyConverter;
import com.netcracker.denisik.dao.FacultyRepository;
import com.netcracker.denisik.dao.SpecialityRepository;
import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.entities.Faculty;
import com.netcracker.denisik.entities.Speciality;
import com.netcracker.denisik.exteption.ResourceAlreadyExistException;
import com.netcracker.denisik.exteption.ResourceNotFoundException;
import com.netcracker.denisik.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class FacultyServiceImpl implements CrudService<FacultyDTO> {
    private FacultyConverter facultyConverter;
    private FacultyRepository facultyRepository;
    private SpecialityRepository specialityRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, FacultyConverter facultyConverter,
                              SpecialityRepository specialityRepository) {
        this.facultyConverter = facultyConverter;
        this.facultyRepository = facultyRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public long add(FacultyDTO facultyDTO) {
        Faculty faculty = facultyRepository.getByName(facultyDTO.getName());
        List<Speciality> specialities = new ArrayList<>();
        facultyDTO.getSpecialities()
                .forEach(string -> specialities.add(specialityRepository.getByName(string)));
        log.debug("Check free name for new faculty");
        if (faculty != null) {
            log.error("Faculty exist with name : " + facultyDTO.getName());
            throw new ResourceAlreadyExistException("Faculty exist with name : " + facultyDTO.getName());
        }
        log.debug("Check specialities for new faculty");
        checkSpecialities(facultyDTO.getSpecialities());
        log.info("Add/update faculty : " + facultyDTO.getName());
        return facultyRepository.save(facultyConverter.convert(facultyDTO)).getId();
    }

    public void checkSpecialities(List<String> specialities) {
        for (String name : specialities) {
            Speciality speciality = specialityRepository.getByName(name);
            if (speciality != null) {
                log.error("Speciality exist with name : " + name);
                throw new ResourceAlreadyExistException("Speciality exist with name : " + name);
            }
        }
    }

    @Override
    public void delete(long id) {
        log.debug("Deleting faculty");
        Faculty faculty = facultyRepository.findOne(id);
        if (faculty == null) {
            log.error("Not found faculty for delete by id:" + id);
            throw new ResourceNotFoundException("Deleting faculty by id: " + id);
        }
        facultyRepository.delete(id);
    }

    @Override
    public List<FacultyDTO> getAll() {
        List<FacultyDTO> facultyDTOS = StreamSupport.stream(facultyRepository.findAll().spliterator(), false)
                .map(faculty -> facultyConverter.convert(faculty))
                .collect(Collectors.toList());
        log.debug("Getting all faculties from DB");
        return facultyDTOS;
    }

    @Override
    public FacultyDTO get(long id) {
        log.debug("Start getting faculty by id");
        Faculty faculty = facultyRepository.findOne(id);
        return facultyConverter.convert(faculty);
    }
}
