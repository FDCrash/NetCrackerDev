package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.DAO;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.storage.FacultyList;

import java.util.List;

public class FacultyDAOImpl implements DAO<FacultyEntity> {

    public FacultyDAOImpl(){}


    @Override
    public FacultyEntity get(int id) {
        return FacultyList.getInstance().get().get(id);
    }

    @Override
    public List<FacultyEntity> getAll() {
        return FacultyList.getInstance().get();
    }

    @Override
    public FacultyEntity add(FacultyEntity facultyEntity) {
        return null;
    }

    @Override
    public FacultyEntity update(FacultyEntity facultyEntity) {
        return null;
    }


    @Override
    public void delete(int id) {

    }
}
