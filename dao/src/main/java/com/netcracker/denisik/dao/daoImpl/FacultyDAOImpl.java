package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.IDao;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.storage.FacultyList;
import com.netcracker.denisik.storage.SpecialityList;
import com.netcracker.denisik.storage.StudentList;

import java.util.List;
import java.util.stream.Collectors;

public class FacultyDAOImpl implements IDao<FacultyEntity> {

    @Override
    public FacultyEntity get(int id) {
        return getAll().stream()
                .filter(faculty -> faculty.getId()==id)
                .findFirst()
                .orElse(null);
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
        StudentList.getInstance().get().stream()
                .filter(student -> student.getSpecialityEntity().getFaculty().getId()==id)
                .collect(Collectors.toList())
                .forEach(speciality -> speciality.setSpecialityEntity(new SpecialityEntity(0,"Переводится", 0)));
        SpecialityList.getInstance().get().removeAll(get(id).getSpecialities());
        getAll().remove(get(id));
    }
}
