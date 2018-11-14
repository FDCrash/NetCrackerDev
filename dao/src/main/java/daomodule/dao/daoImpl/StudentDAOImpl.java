package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.StudentEntity;
import daomodule.storage.StudentList;

import java.util.List;

public class StudentDAOImpl implements DAO<StudentEntity> {
    @Override
    public StudentEntity get(int id) {
        return StudentList.getInstance().get().get(id);
    }

    @Override
    public List<StudentEntity> getAll() {
        return StudentList.getInstance().get();
    }

    @Override
    public void add(StudentEntity studentEntity) {
        StudentList.getInstance().add(studentEntity);
    }

    @Override
    public void update(StudentEntity student) {
        for(StudentEntity studentEntity: StudentList.getInstance().get()){
            if(studentEntity.getId()==student.getId()){
                studentEntity.setName(student.getName());
                studentEntity.setGroupId(student.getGroupId());
                studentEntity.setStudentId(student.getStudentId());
                studentEntity.setSpecialityEntity(student.getSpecialityEntity());
                studentEntity.setWriteBook(student.getWriteBook());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        StudentList.getInstance().get().remove(id);
    }

    public boolean checkStudId(int id){
        for (StudentEntity studentEntity: StudentList.getInstance().get()) {
            if(studentEntity.getStudentId()==id){
                return true;
            }
        }
        return false;
    }

    public void addNewLoginPass(int id,String login, String pass){
        for (StudentEntity studentEntity: StudentList.getInstance().get()) {
            if(studentEntity.getStudentId()==id){
                studentEntity.setLogin(login);
                studentEntity.setPassword(pass);
                break;
            }
        }
    }
}
