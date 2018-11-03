import daomodule.dao.daoImpl.UserDAOImpl;
import daomodule.entities.StudentEntity;

import java.util.List;

public class ServiceStudent {
    private StudentEntity studentEntity;
    private UserDAOImpl userDAO;

    public StudentEntity readStudent(int id){return studentEntity;}

    public UserDAOImpl getFullInfoStudents(int id){return  userDAO;}

    public List<Integer> getMarksOfControl(int id){return studentEntity.getWriteBook();}

    public void setMarksOfControl(List<Integer> marks){}
}
