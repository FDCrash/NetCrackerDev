package services.servicesimpl;

import converters.StudentConverter;
import daomodule.dao.daoImpl.StudentDAOImpl;
import daomodule.entities.StudentEntity;
import dto.StudentDTO;
import services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements CRUDService<StudentDTO> {
    private StudentDAOImpl studentDAO;
    private StudentConverter studentConverter;

    public StudentServiceImpl(){
        studentConverter = new StudentConverter();
        studentDAO = new StudentDAOImpl();
    }

    public List<Integer> getMarksOfControl(int id){return null;}

    public void setMarksOfControl(List<Integer> marks){}

    @Override
    public void addNew(StudentDTO studentDTO) {
        StudentEntity studentEntity= studentConverter.convert(studentDTO);
        studentDAO.add(studentEntity);
    }

    @Override
    public void deleteInfo(int id) {
        studentDAO.delete(id);
    }

    @Override
    public void updateInfo(StudentDTO studentDTO) {
        StudentEntity studentEntity=studentConverter.convert(studentDTO);
        studentDAO.update(studentEntity);
    }


    @Override
    public List<StudentDTO> getAll() {
        return studentDAO.getAll().stream().map(student->studentConverter.convert(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDTO get(int id) {
        StudentDTO studentDTO=studentConverter.convert(studentDAO.get(id));
        return studentDTO;
    }
}
