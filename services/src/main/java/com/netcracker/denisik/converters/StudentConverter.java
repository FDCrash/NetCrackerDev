package com.netcracker.denisik.converters;

import com.netcracker.denisik.dto.*;
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
        User user= User.builder()
                .id(studentDTO.getId())
                .login(studentDTO.getLogin())
                .password(studentDTO.getPassword())
                .role(Role.STUDENT)
                .name(studentDTO.getName())
                .build();
        for(SemesterDTO semesterDTO:studentDTO.getWriteBook().getSemester()){
            semesterEntities.add(
                    Semester.builder()
                    .id(semesterDTO.getId())
                    .mark(semesterDTO.getMark())
                    .subject(
                            Subject.builder()
                            .name(semesterDTO.getSubject())
                            .build())
                    .build());
        }
        return Student.builderStudent()
                .user(user)
                .id(studentDTO.getId())
                .groupId(studentDTO.getGroupId())
                .writeBook(
                        WriteBook.builder()
                        .semesters(semesterEntities).build())
                .speciality(specialityConverter.convert(studentDTO.getSpeciality()))
                .build();
    }

    public StudentDTO convert(Student student) {
        List<SemesterDTO> semesterDTOS=new ArrayList<>();
        UserDTO userDTO= UserDTO.builder()
                .id(student.getId())
                .roleDTO(RoleDTO.STUDENT)
                .login(student.getLogin())
                .password(student.getPassword())
                .name(student.getName())
                .build();
        for(Semester semester : student.getWriteBook().getSemesters()){
            semesterDTOS.add(SemesterDTO.builder()
                .id(semester.getId())
                .mark(semester.getMark())
                .subject(semester.getSubject().getName())
                .build());
        }
        return StudentDTO.builderStudent()
                .userDTO(userDTO)
                .groupId(student.getGroupId())
                .speciality(specialityConverter.convert(student.getSpeciality()))
                .writeBook(
                        WriteBookDTO.builder()
                        .semester(semesterDTOS)
                        .build())
                .build();
    }
}
