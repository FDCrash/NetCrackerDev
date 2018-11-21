package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.DAO;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.storage.SpecialityList;
import com.netcracker.denisik.storage.StudentList;

import java.util.List;

public class SpecialityDAOImpl implements DAO<SpecialityEntity> {
    @Override
    public SpecialityEntity get(int id) {
        for (SpecialityEntity specialityEntity : getAll()) {
            if (specialityEntity.getId() == id) {
                return specialityEntity;
            }
        }
        return null;
    }

    @Override
    public List<SpecialityEntity> getAll() {
        return SpecialityList.getInstance().get();
    }

    @Override
    public SpecialityEntity add(SpecialityEntity speciality) {
        SpecialityList.getInstance().add(speciality);
        new FacultyDAOImpl().get(speciality.getFaculty().getId()).setSpeciality(speciality);
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
        new FacultyDAOImpl().get(get(id).getFaculty().getId()).getSpecialities().remove(get(id));
        for (StudentEntity studentEntity : StudentList.getInstance().get()) {
            if (studentEntity.getSpecialityEntity().getId() == id) {
                studentEntity.setSpecialityEntity(new SpecialityEntity(0, "Переводится", 0));
            }
        }
        getAll().remove(get(id));
    }
}
