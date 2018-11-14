package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.FacultyEntity;
import daomodule.storage.FacultyList;

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
        return FacultyList.getInstance().get();
    }

    @Override
    public void add(FacultyEntity faculty) {

    }

    @Override
    public void update(FacultyEntity faculty) {

    }

    @Override
    public void delete(int id) {

    }
}
