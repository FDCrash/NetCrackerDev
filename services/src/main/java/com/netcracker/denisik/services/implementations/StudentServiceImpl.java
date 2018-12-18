package com.netcracker.denisik.services.implementations;

import com.netcracker.denisik.converters.StudentConverter;
import com.netcracker.denisik.dao.SpecialityRepository;
import com.netcracker.denisik.dao.StudentRepository;
import com.netcracker.denisik.dao.SubjectRepository;
import com.netcracker.denisik.dto.SemesterDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.entities.Semester;
import com.netcracker.denisik.entities.Speciality;
import com.netcracker.denisik.entities.Student;
import com.netcracker.denisik.entities.Subject;
import com.netcracker.denisik.exteption.ResourceNotFoundException;
import com.netcracker.denisik.exteption.ServiceException;
import com.netcracker.denisik.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class StudentServiceImpl implements CrudService<StudentDTO> {
    private StudentConverter studentConverter;
    private StudentRepository studentRepository;
    private SpecialityRepository specialityRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,StudentConverter studentConverter,
                               SpecialityRepository specialityRepository,SubjectRepository subjectRepository) {
        this.studentConverter = studentConverter;
        this.studentRepository = studentRepository;
        this.specialityRepository= specialityRepository;
        this.subjectRepository=subjectRepository;
    }

    @Override
    public long add(StudentDTO studentDTO) {
        boolean checkRange = studentDTO.getWriteBook().getSemester().stream()
                .anyMatch(semesterDTO -> semesterDTO.getMark()>10 || semesterDTO.getMark()<0);
        Speciality speciality = specialityRepository.findOne(studentDTO.getSpecialityId());
        if(speciality==null){
            throw new ResourceNotFoundException("Speciality not found");
        }else
        if(checkRange){
            throw new ServiceException("Range out of mark");
        }
        checkSubjects(studentDTO.getWriteBook().getSemester());
        return studentRepository.save(studentConverter.convert(studentDTO)).getId();
    }

    @Override
    public void delete(long id) {
        if(studentRepository.findOne(id)==null){
            throw new ResourceNotFoundException("Deleting student by id: " + id);
        }
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

    public void checkSubjects(List<SemesterDTO> semesterDTOS){
        for(SemesterDTO semesterDTO: semesterDTOS) {
            Subject subject = subjectRepository.getByName(semesterDTO.getSubject().getName());
            if (subject == null) {
                throw new ResourceNotFoundException("Subject not found");
            }
        }
    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTOS=StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .map(student -> studentConverter.convert(student))
                .collect(Collectors.toList());
        log.debug("");
        return studentDTOS;
    }

    @Override
    public StudentDTO get(long id) {
        Student student=studentRepository.findOne(id);
        return studentConverter.convert(student);
    }
}
