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

    }

    @Override
    public void update(StudentEntity student) {

    }

    @Override
    public void delete(int id) {

    }

    public boolean checkStudId(int id){

        return false;
    }

    public void addNewLoginPass(int id,String login, String pass){

    }
}
