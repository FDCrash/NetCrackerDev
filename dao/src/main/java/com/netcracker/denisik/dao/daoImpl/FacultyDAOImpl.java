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

    @Override
    public FacultyEntity get(int id) {
        for(FacultyEntity facultyEntity:getAll()){
            if(facultyEntity.getId()==id){
                return facultyEntity;
            }
        }
        return null;
    }

    @Override
    public List<FacultyEntity> getAll() {
        return FacultyList.getInstance().get();
    }

    @Override
    public FacultyEntity add(FacultyEntity facultyEntity) {
        FacultyList.getInstance().add(facultyEntity);
        SpecialityList.getInstance().addAll(facultyEntity.getSpecialities());
        return get(facultyEntity.getId());
    }

    @Override
    public FacultyEntity update(FacultyEntity faculty) {
        get(faculty.getId()).setName(faculty.getName());
        return get(faculty.getId());
    }

    @Override
    public void delete(int id) {
        for(SpecialityEntity specialityEntity:get(id).getSpecialities()){
            new SpecialityDAOImpl().delete(specialityEntity.getId());
        }
        for(StudentEntity studentEntity: StudentList.getInstance().get()){
            if(studentEntity.getSpecialityEntity().getFaculty().getId()==id){
                studentEntity.setSpecialityEntity(new SpecialityEntity(0,"Переводится", 0));
            }
        }
        getAll().remove(get(id));
    }
}
