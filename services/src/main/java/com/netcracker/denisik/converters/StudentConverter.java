package com.netcracker.denisik.converters;

import com.netcracker.denisik.dao.SpecialityRepository;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.dto.WriteBookDTO;
import com.netcracker.denisik.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class StudentConverter {
    private final SpecialityRepository specialityRepository;

    public StudentConverter(SpecialityRepository studentRepository){
        this.specialityRepository=studentRepository;
    }

    public StudentEntity convert(StudentDTO studentDTO) {
        List<SemesterEntity> semesterEntities =new ArrayList<>();
        studentDTO.getWriteBook().
                forEach(writeBookDTO -> semesterEntities.
                        add(new SemesterEntity(writeBookDTO.getSem(),writeBookDTO.getSubjects(),writeBookDTO.getMarks())));
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setId(studentDTO.getId());
        studentEntity.setRole(Role.valueOf(studentDTO.getRoleDTO().name()));
        studentEntity.setLogin(studentDTO.getLogin());
        studentEntity.setPassword(studentDTO.getPassword());
        studentEntity.setName(studentDTO.getName());
        studentEntity.setStudentId(studentDTO.getStudentId());
        studentEntity.setGroupId(studentDTO.getGroupId());
        StreamSupport.stream(specialityRepository.findAll().spliterator(),false)
                .filter(specialityEntity -> specialityEntity.getName().equals(studentDTO.getSpeciality()))
                .findFirst().get();
        studentEntity.setWriteBook(new WriteBook(semesterEntities));
        return studentEntity;
    }

    public StudentDTO convert(StudentEntity studentEntity) {
        List<WriteBookDTO> writeBookDTO=new ArrayList<>();
        studentEntity.getWriteBook().getSemesterEntity().
                forEach(writeBook -> writeBookDTO.
                        add(new WriteBookDTO(writeBook.getSem(),writeBook.getSubjects(),writeBook.getMarks())));
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setRoleDTO(RoleDTO.valueOf(studentEntity.getRole().name()));
        studentDTO.setLogin(studentEntity.getLogin());
        studentDTO.setPassword(studentEntity.getPassword());
        studentDTO.setName(studentEntity.getName());
        studentDTO.setStudentId(studentEntity.getStudentId());
        studentDTO.setGroupId(studentEntity.getGroupId());
        studentDTO.setSpeciality(studentEntity.getSpecialityEntity().getName());
        studentDTO.setWriteBook(writeBookDTO);
        return studentDTO;
    }
}
