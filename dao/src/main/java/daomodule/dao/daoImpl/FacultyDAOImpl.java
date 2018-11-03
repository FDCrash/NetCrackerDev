package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.FacultyEntity;

import java.util.List;
import java.util.Optional;

public class FacultyDAOImpl implements DAO<FacultyEntity> {
    private List<FacultyEntity> faculties;

    public FacultyDAOImpl(){}

    @Override
    public Optional<FacultyEntity> get(FacultyEntity facultyEntity) {
        return Optional.empty();
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
