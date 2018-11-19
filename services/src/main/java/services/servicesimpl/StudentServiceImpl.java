package services.servicesimpl;

import converters.StudentConverter;
import dao.dao.daoImpl.StudentDAOImpl;
import dto.StudentDTO;
import services.CRUDService;

import java.util.List;

public class StudentServiceImpl implements CRUDService<StudentDTO> {
    private StudentDAOImpl studentDAO;
    private StudentConverter studentConverter;

    public StudentServiceImpl(){

    }

    public List<Integer> getMarksOfControl(int id){return null;}

    public void setMarksOfControl(List<Integer> marks){}

    @Override
    public void addNew(StudentDTO studentDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(StudentDTO studentDTO) {

    }


    @Override
    public List<StudentDTO> getAll() {
        return null;
    }

    @Override
    public StudentDTO get(int id) {
        return null;
    }
}
