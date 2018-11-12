package converters;

import daomodule.dao.daoImpl.FacultyDAOImpl;
import daomodule.entities.FacultyEntity;
import daomodule.entities.SpecialityEntity;
import dto.SpecialityDTO;

public class SpecialityConverter {
    public SpecialityEntity convert(SpecialityDTO specialityDTO){
        SpecialityEntity specialityEntity=new SpecialityEntity(specialityDTO.getId(),
                specialityDTO.getName(),0);
        for(FacultyEntity facultyEntity:new FacultyDAOImpl().getAll()){
            if(facultyEntity.getName().equals(specialityDTO.getFaculty())){
                specialityEntity.setFaculty(facultyEntity);
                break;
            }
        }
        return specialityEntity;
    }

    public SpecialityDTO convert(SpecialityEntity specialityEntity){
        return new SpecialityDTO(specialityEntity.getId(),specialityEntity.getName()
                ,specialityEntity.getFaculty().getName());
    }
}