package dao.dao.daoImpl;

import dao.dao.DAO;
import dao.entities.FacultyEntity;
import dao.entities.SpecialityEntity;
import dao.entities.StudentEntity;
import dao.storage.FacultyList;
import dao.storage.SpecialityList;
import dao.storage.StudentList;

import java.util.List;

public class SpecialityDAOImpl implements DAO<SpecialityEntity> {

    @Override
    public SpecialityEntity get(int id) {
        return getAll().get(id);
    }

    @Override
    public List<SpecialityEntity> getAll() {
        return SpecialityList.getInstance().get();
    }

    @Override
    public SpecialityEntity add(SpecialityEntity speciality) {
        SpecialityList.getInstance().add(speciality);
        for(FacultyEntity facultyEntity:FacultyList.getInstance().get()){
            if(speciality.getFaculty().getId()==facultyEntity.getId()){
                facultyEntity.setSpeciality(speciality);
                break;
            }
        }
        return speciality;
    }

    @Override
    public SpecialityEntity update(SpecialityEntity speciality) {
        for(SpecialityEntity specialityEntity: getAll()){
            if(specialityEntity.getId()==speciality.getId()){
                specialityEntity.setName(speciality.getName());
                specialityEntity.setFaculty(speciality.getFaculty());
                break;
            }
        }
        return speciality;
    }

    @Override
    public void delete(int id) {
        SpecialityEntity specialityEntity=getAll().get(id);
        for(FacultyEntity facultyEntity:FacultyList.getInstance().get()){
            if(facultyEntity.getId()==specialityEntity.getFaculty().getId()){
                facultyEntity.getSpecialities().remove(specialityEntity);
                break;
            }
        }
        for(StudentEntity studentEntity: StudentList.getInstance().get()){
            if(studentEntity.getSpecialityEntity().getId()==specialityEntity.getId()){
                studentEntity.setSpecialityEntity(new SpecialityEntity(0,"Переводится", 0));
            }
        }
        getAll().remove(specialityEntity);
    }
    public boolean checkId(int id) {
        for (SpecialityEntity specialityEntity : getAll()) {
            if (specialityEntity.getId() == id) {
                return false;
            }
        }
        return true;
    }
}
