package converters;

import daomodule.dao.daoImpl.SpecialityDAOImpl;
import daomodule.entities.FacultyEntity;
import daomodule.entities.SpecialityEntity;
import dto.FacultyDTO;

import java.util.ArrayList;
import java.util.List;

public class FacultyConverter {
    public FacultyEntity convert(FacultyDTO facultyDTO) {
        FacultyEntity facultyEntity = new FacultyEntity(facultyDTO.getId(), facultyDTO.getName());
        int k = new SpecialityDAOImpl().getAll().size();
        List<SpecialityEntity> specialityEntities=new ArrayList<>();
        for(int i=0;i<facultyDTO.getSpecialities().size();i++){
            specialityEntities.add(new SpecialityEntity(k-i,facultyDTO.getSpecialities().get(i),facultyEntity));
        }
        facultyEntity.setSpecialities(specialityEntities);
        return facultyEntity;
    }

    public FacultyDTO convert(FacultyEntity facultyEntity){
        FacultyDTO facultyDTO=new FacultyDTO(facultyEntity.getId(),facultyEntity.getName());
        List<String> specilities=new ArrayList<>();
        for(int i=0;i<facultyEntity.getSpeciality().size();i++){
            specilities.add(facultyEntity.getSpeciality().get(i).getName());
        }
        facultyDTO.setSpecialities(specilities);
        return facultyDTO;
    }
}
