package services.servicesimpl;

import converters.FacultyConverter;
import daomodule.dao.daoImpl.FacultyDAOImpl;
import daomodule.entities.FacultyEntity;
import dto.FacultyDTO;
import services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class FacultyServiceImpl implements CRUDService<FacultyDTO> {
    private FacultyDAOImpl facultyDAO;
    private FacultyConverter facultyConverter;

    public FacultyServiceImpl(){
        facultyDAO = new FacultyDAOImpl();
        facultyConverter = new FacultyConverter();
    }

    @Override
    public void addNew(FacultyDTO facultyDTO) {
        FacultyEntity facultyEntity=facultyConverter.convert(facultyDTO);
        facultyDAO.add(facultyEntity);
    }

    @Override
    public void deleteInfo(int id) {
        facultyDAO.delete(id);
    }

    @Override
    public void updateInfo(FacultyDTO facultyDTO) {
        FacultyEntity facultyEntity= facultyConverter.convert(facultyDTO);
        facultyDAO.update(facultyEntity);
    }

    @Override
    public List<FacultyDTO> getAll() {
        return facultyDAO.getAll().stream().map(faculty->facultyConverter.convert(faculty)).
                collect(Collectors.toList());
    }

    @Override
    public FacultyDTO get(int id) {
        FacultyDTO facultyDTO=facultyConverter.convert(facultyDAO.get(id));
        return facultyDTO;
    }
}
