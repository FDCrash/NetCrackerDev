package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.FacultyEntity;

import java.util.List;

public class FacultyDAOImpl implements DAO<FacultyEntity> {
    private List<FacultyEntity> faculties;

    public FacultyDAOImpl(){}


    @Override
    public FacultyEntity get(int id) {
        return faculties.get(id);
    }

    @Override
    public List<FacultyEntity> getAll() {
        return faculties;
    }

    @Override
    public void add(FacultyEntity faculty) {

    }

    @Override
    public void update(FacultyEntity faculty) {

    }

    @Override
    public void delete(FacultyEntity faculty) {

    }
}
