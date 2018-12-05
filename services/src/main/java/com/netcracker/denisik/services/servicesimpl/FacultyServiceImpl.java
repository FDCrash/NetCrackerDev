package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.FacultyConverter;
import com.netcracker.denisik.dao.FacultyRepository;
import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FacultyServiceImpl implements CRUDService<FacultyDTO> {
    private FacultyConverter facultyConverter;
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        facultyConverter = new FacultyConverter();
        this.facultyRepository = facultyRepository;
    }

    @Override
    public void add(FacultyDTO facultyDTO) {
        facultyRepository.save(facultyConverter.convert(facultyDTO));
    }

    @Override
    public void delete(int id) {
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
    public FacultyDTO get(int id) {
        return facultyConverter.convert(facultyRepository.findOne(id));
    }
}
