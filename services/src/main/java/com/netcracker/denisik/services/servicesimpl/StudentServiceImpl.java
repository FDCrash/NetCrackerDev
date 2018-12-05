package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.StudentConverter;
import com.netcracker.denisik.dao.StudentRepository;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentServiceImpl implements CRUDService<StudentDTO> {
    private StudentConverter studentConverter;
    private final StudentRepository studentRepository;

    private StudentServiceImpl(StudentRepository studentRepository, StudentConverter studentConverter) {
        this.studentConverter = studentConverter;
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(StudentDTO studentDTO) {
        studentRepository.save(studentConverter.convert(studentDTO));
    }

    @Override
    public void delete(int id) {
        studentRepository.delete(id);
    }

    public StudentDTO getByLogin(String login) {
        return studentConverter.convert(studentRepository.getByLogin(login));
    }

    public List<StudentDTO> getAllByGroup(int number) {
        return StreamSupport.stream(studentRepository.getAllByGroupId(number).spliterator(), false)
                .map(student -> studentConverter.convert(student))
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getAllBySpeciality(String speciality) {
        return StreamSupport.stream(studentRepository.getAllBySpecialityEntity(speciality).spliterator(), false)
                .map(student -> studentConverter.convert(student))
                .collect(Collectors.toList());
    }

    @Override
    public void update(StudentDTO studentDTO) {
        studentRepository.save(studentConverter.convert(studentDTO));
    }


    @Override
    public List<StudentDTO> getAll() {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .map(student -> studentConverter.convert(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO get(int id) {
        return studentConverter.convert(studentRepository.findOne(id));
    }
}
