package com.netcracker.denisik.converters;

import com.netcracker.denisik.dao.daoImpl.SpecialityDAOImpl;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.dto.WriteBookDTO;
import com.netcracker.denisik.entities.*;

import java.util.ArrayList;
import java.util.List;

public class StudentConverter {
    public StudentEntity convert(StudentDTO studentDTO) {
        List<WriteBook> writeBooks=new ArrayList<>();
        studentDTO.getWriteBook().
                forEach(writeBookDTO -> writeBooks.
                        add(new WriteBook(writeBookDTO.getSem(),writeBookDTO.getSubjects(),writeBookDTO.getMarks())));
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setId(studentDTO.getId());
        studentEntity.setRole(Role.valueOf(studentDTO.getRoleDTO().name()));
        studentEntity.setLogin(studentDTO.getLogin());
        studentEntity.setPassword(studentDTO.getPassword());
        studentEntity.setName(studentDTO.getName());
        studentEntity.setStudentId(studentDTO.getStudentId());
        studentEntity.setGroupId(studentDTO.getGroupId());
        studentEntity.setSpecialityEntity(SpecialityDAOImpl.getInstance().getAll().stream()
                .filter(specialityEntity -> specialityEntity.getName().equals(studentDTO.getSpeciality()))
                .findFirst().get());
        studentEntity.setWriteBook(writeBooks);
        return studentEntity;
    }

    public StudentDTO convert(StudentEntity studentEntity) {
        List<WriteBookDTO> writeBookDTO=new ArrayList<>();
        studentEntity.getWriteBook().
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
