package services.servicesimpl;

import converters.FacultyConverter;
import daomodule.dao.daoImpl.FacultyDAOImpl;

import dto.FacultyDTO;
import services.CRUDService;

import java.util.List;


public class FacultyServiceImpl implements CRUDService<FacultyDTO> {
    private FacultyDAOImpl facultyDAO;
    private FacultyConverter facultyConverter;

    public FacultyServiceImpl(){

    }

    @Override
    public void addNew(FacultyDTO facultyDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(FacultyDTO facultyDTO) {

    }

    @Override
    public List<FacultyDTO> getAll() {
        return null;
    }

    @Override
    public FacultyDTO get(int id) {

        return null;
    }
}
