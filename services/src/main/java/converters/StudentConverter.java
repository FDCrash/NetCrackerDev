package converters;

import daomodule.dao.daoImpl.SpecialityDAOImpl;
import daomodule.entities.Role;
import daomodule.entities.SpecialityEntity;
import daomodule.entities.StudentEntity;
import dto.RoleDTO;
import dto.StudentDTO;

public class StudentConverter {
    public StudentEntity convert(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity(studentDTO.getId(), (Role) studentDTO.getRoleDTO()
                , studentDTO.getLogin(), studentDTO.getPassword(), studentDTO.getName(), studentDTO.getStudentId(),
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
        return new StudentDTO(studentEntity.getId(),(RoleDTO) studentEntity.getRole(), studentEntity.getLogin(),
                studentEntity.getPassword(),studentEntity.getName(),studentEntity.getStudentId(),
                studentEntity.getGroupId(),studentEntity.getSpecialityEntity().getName(),studentEntity.getWriteBook());
    }
}
