import daomodule.dao.daoImpl.FacultyDAOImpl;
import daomodule.dao.daoImpl.SpecialityDAOImpl;
import daomodule.entities.FacultyEntity;
import daomodule.entities.SpecialityEntity;

public class ServiceEditStructure {
    private SpecialityDAOImpl specialityDAO;
    private SpecialityEntity specialityEntity;
    private FacultyDAOImpl facultyDAO;
    private FacultyEntity facultyEntity;

    public void addNewFaculty(FacultyEntity facultyEntity){}

    public  void deleteFacultyInfo(String faculty){}

    public void updateFacultyInfo(FacultyEntity facultyEntity, int id){}

    public void addNewSpeciality(SpecialityEntity specialityEntity){}

    public void deleteSpecialityInfo(String speciality){}

    public void updateSpecialityInfo(SpecialityEntity specialityEntity, int id){}
}
