package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.*;
import com.netcracker.denisik.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StudentConverter {
    private SubjectConverter subjectConverter;

    @Autowired
    public StudentConverter(SubjectConverter subjectConverter) {
        this.subjectConverter = subjectConverter;
    }

    public Student convert(StudentDTO studentDTO) {
        List<Semester> semesterEntities = new ArrayList<>();
        User user = User.builder()
                .id(studentDTO.getId())
                .login(studentDTO.getLogin())
                .password(studentDTO.getPassword())
                .role(Role.STUDENT)
                .name(studentDTO.getName())
                .build();
        for (SemesterDTO semesterDTO : studentDTO.getWriteBook().getSemester()) {
            semesterEntities.add(
                    Semester.builder()
                            .mark(semesterDTO.getMark())
                            .subject(subjectConverter.convert(semesterDTO.getSubject()))
                            .build());
        }
        WriteBook writeBook=WriteBook.builder()
                .budget(studentDTO.getWriteBook().isBudget())
                .semesters(semesterEntities)
                .build();
        Speciality speciality=Speciality.builder()
                .id(studentDTO.getSpecialityId())
                .build();
        Student student = Student.builderStudent()
                .user(user)
                .groupId(studentDTO.getGroupId())
                .writeBook(writeBook)
                .speciality(speciality)
                .build();
        for(Semester semester: student.getWriteBook().getSemesters()){
            semester.setWriteBook(student.getWriteBook());
        }
        return student;
    }

    public StudentDTO convert(Student student) {
        if (student == null) {
            return null;
        }
        List<SemesterDTO> semesterDTOS = new ArrayList<>();
        UserDTO userDTO = UserDTO.builder()
                .id(student.getId())
                .roleDTO(RoleDTO.STUDENT)
                .login(student.getLogin())
                .password(student.getPassword())
                .name(student.getName())
                .build();
        for (Semester semester : student.getWriteBook().getSemesters()) {
            semesterDTOS.add(SemesterDTO.builder()
                    .mark(semester.getMark())
                    .subject(subjectConverter.convert(semester.getSubject()))
                    .build());
        }
        return StudentDTO.builderStudent()
                .userDTO(userDTO)
                .groupId(student.getGroupId())
                .speciality(student.getSpeciality().getName())
                .specialityId(student.getSpeciality().getId())
                .writeBook(
                        WriteBookDTO.builder()
                                .id(student.getWriteBook().getId())
                                .budget(student.getWriteBook().isBudget())
                                .semester(semesterDTOS)
                                .build())
                .build();
    }
}
