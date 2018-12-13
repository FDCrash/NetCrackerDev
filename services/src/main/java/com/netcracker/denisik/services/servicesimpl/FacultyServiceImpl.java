package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.FacultyConverter;
import com.netcracker.denisik.dao.FacultyRepository;
import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FacultyServiceImpl implements CRUDService<FacultyDTO> {
    private FacultyConverter facultyConverter;
    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, FacultyConverter facultyConverter) {
        this.facultyConverter = facultyConverter;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public void add(FacultyDTO facultyDTO) {
        facultyRepository.save(facultyConverter.convert(facultyDTO));
    }

    @Override
    public void delete(long id) {
        facultyRepository.delete(id);
    }

    @Override
    public void update(FacultyDTO facultyDTO) {
        facultyRepository.save(facultyConverter.convert(facultyDTO));
    }

    @Override
    public List<FacultyDTO> getAll() {
        return StreamSupport.stream(facultyRepository.findAll().spliterator(), false)
                .map(faculty -> facultyConverter.convert(faculty))
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDTO get(long id) {
        return facultyConverter.convert(facultyRepository.findOne(id));
    }
}
