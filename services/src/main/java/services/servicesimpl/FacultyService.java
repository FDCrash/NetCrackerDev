package services.servicesimpl;

import daomodule.dao.daoImpl.FacultyDAOImpl;
import daomodule.entities.FacultyEntity;
import services.CRUDService;

import java.util.List;

public class FacultyService implements CRUDService<FacultyEntity> {
    private FacultyDAOImpl facultyDAO;
    private FacultyEntity facultyEntity;

    @Override
    public void addNew(FacultyEntity facultyEntity) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(FacultyEntity facultyEntity, int id) {

    }

    @Override
    public List<FacultyEntity> getAll() {
        return null;
    }

    @Override
    public FacultyEntity get(int id) {
        return null;
    }
}
