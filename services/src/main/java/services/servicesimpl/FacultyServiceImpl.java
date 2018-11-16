package services.servicesimpl;

import converters.FacultyConverter;
import daomodule.dao.daoImpl.FacultyDAOImpl;
import daomodule.dao.daoImpl.SpecialityDAOImpl;
import daomodule.entities.FacultyEntity;
import daomodule.entities.SpecialityEntity;
import dto.FacultyDTO;
import services.CRUDService;

import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

public class FacultyServiceImpl implements CRUDService<FacultyDTO> {
    private FacultyDAOImpl facultyDAO;
    private FacultyConverter facultyConverter;
    private SpecialityDAOImpl specialityDAO;
    private UserServiceImpl userService;

    public FacultyServiceImpl(){
        facultyDAO = new FacultyDAOImpl();
        facultyConverter = new FacultyConverter();
        specialityDAO = new SpecialityDAOImpl();
        userService = new UserServiceImpl();
    }

    @Override
    public void addNew(FacultyDTO facultyDTO) {
        FacultyEntity facultyEntity=facultyConverter.convert(facultyDTO);
        facultyDAO.add(facultyEntity);
        /*for(SpecialityEntity specialityEntity:facultyEntity.getSpecialities()){
            specialityDAO.add(new SpecialityEntity(specialityEntity.getId(),
                    specialityEntity.getName(),facultyEntity));
        }*/
    }

    @Override
    public void deleteInfo(int id) {
        facultyDAO.delete(id);
    }

    @Override
    public void updateInfo(FacultyDTO facultyDTO) {
        facultyDAO.update(facultyConverter.convert(facultyDTO));
    }

    @Override
    public List<FacultyDTO> getAll() {
        return facultyDAO.getAll().stream().map(faculty->facultyConverter.convert(faculty)).
                collect(Collectors.toList());
    }

    @Override
    public FacultyDTO get(int id) {
        return facultyConverter.convert(facultyDAO.get(id));
    }

    public int generateId(int bound){
        SplittableRandom splittableRandom=new SplittableRandom();
        int k;
        do{
            k=splittableRandom.nextInt(1,bound);

        }while (!facultyDAO.checkId(k));
        return k;
    }
}
