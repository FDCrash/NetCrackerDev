package services.servicesimpl;

import converters.StudentConverter;
import daomodule.dao.daoImpl.UserDAOImpl;
import daomodule.entities.StudentEntity;
import dto.StudentDTO;
import services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class StudentService implements CRUDService<StudentDTO> {
    private StudentEntity studentEntity;
    private UserDAOImpl userDAO;
    private StudentConverter studentConverter;

    public StudentService(){
        studentConverter = new StudentConverter();
        userDAO = new UserDAOImpl();
    }

    public StudentEntity readStudent(int id){return studentEntity;}

    public UserDAOImpl getFullInfoStudents(int id){return  userDAO;}

    public List<Integer> getMarksOfControl(int id){return studentEntity.getWriteBook();}

    public void setMarksOfControl(List<Integer> marks){}

    @Override
    public void addNew(StudentDTO studentDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(StudentDTO studentDTO, int id) {

    }


    @Override
    public List<StudentDTO> getAll() {
        return userDAO.getStudents().stream().map(student->studentConverter.convert(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDTO get(int id) {
        return null;
    }
}
