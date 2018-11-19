package dao.dao.daoImpl;

import dao.dao.DAO;
import dao.entities.FacultyEntity;
import dao.entities.SpecialityEntity;
import dao.entities.StudentEntity;
import dao.storage.FacultyList;
import dao.storage.SpecialityList;
import dao.storage.StudentList;

import java.util.List;

public class FacultyDAOImpl implements DAO<FacultyEntity> {

    public FacultyDAOImpl(){}


    @Override
    public FacultyEntity get(int id) {
        return getAll().get(id);
    }

    @Override
    public List<FacultyEntity> getAll() {
        return FacultyList.getInstance().get();
    }

    @Override
    public FacultyEntity add(FacultyEntity facultyEntity) {
        FacultyList.getInstance().add(facultyEntity);
        SpecialityList.getInstance().addAll(facultyEntity.getSpecialities());
        return facultyEntity;
    }

    @Override
    public FacultyEntity update(FacultyEntity faculty) {
        for(FacultyEntity facultyEntity:getAll()){
            if(faculty.getId()==facultyEntity.getId()){
                facultyEntity.setName(faculty.getName());
                break;
            }
        }
        return faculty;
    }

    @Override
    public void delete(int id) {
        FacultyEntity facultyEntity=FacultyList.getInstance().get().get(id);
        for(SpecialityEntity specialityEntity:facultyEntity.getSpecialities()){
            SpecialityList.getInstance().get().remove(specialityEntity);
        }
        for(StudentEntity studentEntity: StudentList.getInstance().get()){
            if(studentEntity.getSpecialityEntity().getFaculty().getId()==facultyEntity.getId()){
                studentEntity.setSpecialityEntity(null);
            }
        }
        getAll().remove(id);
    }

    public boolean checkId(int id){
        for(FacultyEntity facultyEntity:getAll()){
            if(facultyEntity.getId()==id){
                return false;
            }
        }
        return true;
    }
}
