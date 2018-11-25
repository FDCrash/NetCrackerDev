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
        studentDTO.getWriteBook().stream().
                forEach(writeBookDTO -> writeBooks.
                        add(new WriteBook(writeBookDTO.getSem(),writeBookDTO.getSubjects(),writeBookDTO.getMarks())));
        return new StudentEntity(new UserEntity(studentDTO.getId(),
                Role.valueOf(studentDTO.getRoleDTO().name()), studentDTO.getLogin(),
                studentDTO.getPassword()), studentDTO.getName(), studentDTO.getStudentId(),
                studentDTO.getGroupId(),SpecialityDAOImpl.getInstance().getAll().stream()
                .filter(specialityEntity -> specialityEntity.getName().equals(studentDTO.getSpeciality()))
                .findFirst().get(), writeBooks);
    }

    public StudentDTO convert(StudentEntity studentEntity) {
        List<WriteBookDTO> writeBookDTO=new ArrayList<>();
        studentEntity.getWriteBook().stream().
                forEach(writeBook -> writeBookDTO.
                        add(new WriteBookDTO(writeBook.getSem(),writeBook.getSubjects(),writeBook.getMarks())));
        return new StudentDTO(new UserDTO(studentEntity.getId(), RoleDTO.valueOf(studentEntity.getRole().name()),
                studentEntity.getLogin(), studentEntity.getPassword()), studentEntity.getName(),
                studentEntity.getStudentId(), studentEntity.getGroupId(),
                studentEntity.getSpecialityEntity().getName(), writeBookDTO);
    }
}
