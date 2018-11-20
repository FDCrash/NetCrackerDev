package com.netcracker.denisik.converters;

import com.netcracker.denisik.dao.daoImpl.SpecialityDAOImpl;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.entities.UserEntity;

public class StudentConverter {
    public StudentEntity convert(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity(new UserEntity(studentDTO.getId(),
                Role.valueOf(studentDTO.getRoleDTO().name()) , studentDTO.getLogin(),
                studentDTO.getPassword()), studentDTO.getName(), studentDTO.getStudentId(),
                studentDTO.getGroupId(), 0, studentDTO.getWriteBook());
        for (SpecialityEntity specialityEntity : new SpecialityDAOImpl().getAll()) {
            if (specialityEntity.getName().equals(studentDTO.getSpeciality())) {
                studentEntity.setSpecialityEntity(specialityEntity);
                break;
            }
        }
        return studentEntity;
    }

    public StudentDTO convert(StudentEntity studentEntity){
        return new StudentDTO(studentEntity.getId(),RoleDTO.valueOf(studentEntity.getRole().name()),
                studentEntity.getLogin(), studentEntity.getPassword(),studentEntity.getName(),
                studentEntity.getStudentId(), studentEntity.getGroupId(),
                studentEntity.getSpecialityEntity().getName(),studentEntity.getWriteBook());
    }
}
