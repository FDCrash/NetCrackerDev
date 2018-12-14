package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.SemesterDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.dto.WriteBookDTO;
import com.netcracker.denisik.entities.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentConverter {
    private SpecialityConverter specialityConverter;

    public StudentConverter(){
        specialityConverter=new SpecialityConverter();
    }

    public Student convert(StudentDTO studentDTO) {
        List<Semester> semesterEntities =new ArrayList<>();
        for(SemesterDTO semesterDTO:studentDTO.getWriteBook().getSemesterEntity()){
            Semester semester =new Semester();
            semester.setId(semesterDTO.getId());
            semester.setSem(semesterDTO.getSem());
            semester.setMark(semesterDTO.getMark());
            semester.setSubject(new Subject(semesterDTO.getSubject()));
            semesterEntities.add(semester);
        }
        Student student =new Student();
        student.setId(studentDTO.getId());
        student.setStudentId(studentDTO.getStudentId());
        student.setGroupId(studentDTO.getGroupId());
        student.setWriteBook(new WriteBook(semesterEntities));
        student.setSpeciality(specialityConverter.convert(studentDTO.getSpeciality()));
        return student;
    }

    public StudentDTO convert(Student student) {
        List<SemesterDTO> semesterDTOS=new ArrayList<>();
        for(Semester semester : student.getWriteBook().getSemester()){
            SemesterDTO semesterDTO=new SemesterDTO();
            semesterDTO.setId(semester.getId());
            semesterDTO.setSem(semester.getSem());
            semesterDTO.setMark(semester.getMark());
            semesterDTO.setSubject(semester.getSubject().getSubject());
            semesterDTOS.add(semesterDTO);
        }
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setGroupId(student.getGroupId());
        studentDTO.setSpeciality(specialityConverter.convert(student.getSpeciality()));
        studentDTO.setWriteBook(new WriteBookDTO(semesterDTOS));
        return studentDTO;
    }
}
