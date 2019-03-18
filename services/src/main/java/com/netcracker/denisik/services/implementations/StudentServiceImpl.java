package com.netcracker.denisik.services.implementations;

import com.google.gson.Gson;
import com.netcracker.denisik.converters.StudentConverter;
import com.netcracker.denisik.dao.SpecialityRepository;
import com.netcracker.denisik.dao.StudentRepository;
import com.netcracker.denisik.dao.SubjectRepository;
import com.netcracker.denisik.dto.SemesterDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.entities.Student;
import com.netcracker.denisik.exteption.ResourceNotFoundException;
import com.netcracker.denisik.exteption.ServiceException;
import com.netcracker.denisik.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional
@Service
public class StudentServiceImpl implements CrudService<StudentDTO> {
    private StudentConverter studentConverter;
    private StudentRepository studentRepository;
    private SpecialityRepository specialityRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudentConverter studentConverter,
                              SpecialityRepository specialityRepository, SubjectRepository subjectRepository) {
        this.studentConverter = studentConverter;
        this.studentRepository = studentRepository;
        this.specialityRepository = specialityRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public long add(StudentDTO studentDTO) {
        boolean checkRange = studentDTO.getWriteBook().getSemester().stream()
                .anyMatch(semesterDTO -> semesterDTO.getMark() > 10 || semesterDTO.getMark() < 0);
        log.debug("Check speciality for student");
        if (!specialityRepository.existsById(studentDTO.getSpecialityId())) {
            log.error("Not found speciality for student");
            throw new ResourceNotFoundException("Speciality not found");
        }
        log.debug("Check range of marks");
        if (checkRange) {
            log.error("Range out of mark");
            throw new ServiceException("Range out of mark");
        }
        log.debug("Check exist subjects in DB");
        checkSubjects(studentDTO.getWriteBook().getSemester());
        log.debug("Add/update student :" + studentDTO.getName());
        return studentRepository.save(studentConverter.convert(studentDTO)).getId();
    }

    @Override
    public void delete(long id) {
        log.debug("Deleting student");
        if (!studentRepository.existsById(id)) {
            log.error("Not found student for delete by id: " + id);
            throw new ResourceNotFoundException("Deleting student by id: " + id);
        }
        studentRepository.delete(id);
    }

    public List<StudentDTO> getAllByGroup(long number) {
        List<StudentDTO> studentDTOS = studentRepository.getAllByGroupId(number).stream()
                .map(student -> studentConverter.convert(student))
                .collect(Collectors.toList());
        log.debug("To Json operation students by group");
        convertToJson(studentDTOS);
        log.debug("Start get students by group id: " + number);
        return studentDTOS;
    }

    public List<StudentDTO> getAllBySpeciality(String speciality) {
        List<StudentDTO> studentDTOS = studentRepository.getAllBySpecialityName(speciality).stream()
                .map(student -> studentConverter.convert(student))
                .collect(Collectors.toList());
        log.debug("To Json operation students by spec");
        convertToJson(studentDTOS);
        log.debug("Start get students by speciality name : " + speciality);
        return studentDTOS;
    }

    public void checkSubjects(List<SemesterDTO> semesterDTOS) {
        for (SemesterDTO semesterDTO : semesterDTOS) {
            if (!subjectRepository.existsById(semesterDTO.getSubject().getId())) {
                log.error("Subject not found for student");
                throw new ResourceNotFoundException("Subject not found");
            }
        }
    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTOS = StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .map(student -> studentConverter.convert(student))
                .collect(Collectors.toList());
        log.debug("To Json operation students");
        convertToJson(studentDTOS);
        log.debug("Getting students from DB");
        return studentDTOS;
    }

    public void convertToJson(List<StudentDTO> studentDTOS) {
        try(FileWriter writer = new FileWriter("services/src/resources/jsonformatstudent")) {
            new Gson().toJson(studentDTOS, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public StudentDTO get(long id) {
        log.debug("Start getting student by id");
        Student student = studentRepository.findOne(id);
        return studentConverter.convert(student);
    }
}
