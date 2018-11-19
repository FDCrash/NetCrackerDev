package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.DAO;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.storage.FacultyList;
import com.netcracker.denisik.storage.SpecialityList;
import com.netcracker.denisik.storage.StudentList;

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
