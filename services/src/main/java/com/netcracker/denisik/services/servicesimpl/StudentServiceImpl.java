package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.StudentConverter;
import com.netcracker.denisik.dao.StudentRepository;
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

    @Autowired
    private StudentServiceImpl(StudentRepository studentRepository,StudentConverter studentConverter) {
        this.studentConverter = studentConverter;
        this.studentRepository = studentRepository;
    }

    @Override
    public long add(StudentDTO studentDTO) {
        return studentRepository.save(studentConverter.convert(studentDTO)).getId();
    }

    @Override
    public void delete(long id) {
        studentRepository.delete(id);
    }

    public List<StudentDTO> getAllByGroup(long number) {
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
