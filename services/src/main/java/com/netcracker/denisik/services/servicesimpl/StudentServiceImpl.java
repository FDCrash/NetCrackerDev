package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.StudentConverter;
import com.netcracker.denisik.converters.UserConverter;
import com.netcracker.denisik.dao.StudentRepository;
import com.netcracker.denisik.dao.UserRepository;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements CRUDService<StudentDTO> {
    private StudentConverter studentConverter;
    private StudentRepository studentRepository;
    private UserRepository userRepository;
    private UserConverter userConverter;

    @Autowired
    private StudentServiceImpl(StudentRepository studentRepository,UserRepository userRepository,StudentConverter studentConverter,UserConverter userConverter) {
        this.studentConverter = studentConverter;
        this.userConverter=userConverter;
        this.studentRepository = studentRepository;
        this.userRepository=userRepository;
    }

    @Override
    public void add(StudentDTO studentDTO) {
        studentRepository.save(studentConverter.convert(studentDTO));
    }

    @Override
    public void delete(long id) {
        studentRepository.delete(id);
    }

    public List<StudentDTO> getAllByGroup(int number) {
        return studentRepository.getAllByGroupId(number).stream()
                .map(student -> studentConverter.convert(student))
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getAllBySpeciality(String speciality) {
        return studentRepository.getAllBySpecialityName(speciality).stream()
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
    public StudentDTO get(long id) {
        return studentConverter.convert(studentRepository.findOne(id));
    }
}
