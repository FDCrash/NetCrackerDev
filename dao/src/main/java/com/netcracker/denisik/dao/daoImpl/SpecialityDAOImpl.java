package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.IDao;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.storage.SpecialityList;
import com.netcracker.denisik.storage.StudentList;

import java.util.List;

public class SpecialityDAOImpl implements IDao<SpecialityEntity> {
    private static SpecialityDAOImpl instance;

    private SpecialityDAOImpl(){}

    public static SpecialityDAOImpl getInstance(){
        if(instance==null){
            instance = new SpecialityDAOImpl();
        }
        return instance;
    }

    @Override
    public SpecialityEntity get(int id) {
        return getAll().stream()
                .filter(speciality -> speciality.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<SpecialityEntity> getAll() {
        return SpecialityList.getInstance().get();
    }

    @Override
    public SpecialityEntity add(SpecialityEntity speciality) {
        SpecialityList.getInstance().add(speciality);
        FacultyDAOImpl.getInstance().get(speciality.getFaculty().getId()).setSpeciality(speciality);
        return get(speciality.getId());
    }

    @Override
    public SpecialityEntity update(SpecialityEntity speciality) {
        delete(speciality.getId());
        add(speciality);
        return get(speciality.getId());
    }

    @Override
    public void delete(int id) {
        StudentList.getInstance().get().stream()
                .filter(student -> student.getSpecialityEntity().getId()==id)
                .forEach(student-> student.setSpecialityEntity(new SpecialityEntity(0,"Переводится", 0)));
        FacultyDAOImpl.getInstance()
                .get(get(id).getFaculty().getId())
                .getSpecialities()
                .remove(get(id));
        getAll().remove(get(id));
    }
}
