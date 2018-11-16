package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.StudentEntity;
import daomodule.entities.UserEntity;
import daomodule.storage.StudentList;
import daomodule.storage.UserList;

import java.util.List;

public class StudentDAOImpl implements DAO<StudentEntity> {
    @Override
    public StudentEntity get(int id) {
        return getAll().get(id);
    }

    @Override
    public List<StudentEntity> getAll() {
        return StudentList.getInstance().get();
    }

    @Override
    public void add(StudentEntity studentEntity) {
        StudentList.getInstance().add(studentEntity);
        UserList.getInstance().addStudent(studentEntity);
    }

    @Override
    public void update(StudentEntity student) {
        for(StudentEntity studentEntity: getAll()){
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
        getAll().remove(id);
    }

    public void deleteById(int id){
        for(StudentEntity studentEntity:getAll()){
            if(studentEntity.getId()==id){
                getAll().remove(studentEntity);
            }
        }
    }

    public boolean checkStudId(int id){
        for (StudentEntity studentEntity: getAll()) {
            if(studentEntity.getStudentId()==id){
                return true;
            }
        }
        return false;
    }

    public void addNewLoginPass(int id,String login, String pass){
        int k=0;
        for (StudentEntity studentEntity: getAll()) {
            if(studentEntity.getStudentId()==id){
                studentEntity.setLogin(login);
                studentEntity.setPassword(pass);
                k=studentEntity.getId();
                break;
            }
        }
        for(UserEntity userEntity: UserList.getInstance().get()){
            if(userEntity.getId()==k){
                userEntity.setLogin(login);
                userEntity.setPassword(pass);
                break;
            }
        }
    }
}
