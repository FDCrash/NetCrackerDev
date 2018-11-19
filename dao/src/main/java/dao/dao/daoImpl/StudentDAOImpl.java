package dao.dao.daoImpl;

import dao.dao.DAO;
import dao.entities.StudentEntity;
import dao.storage.StudentList;

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
    public StudentEntity add(StudentEntity studentEntity) {
        return null;
    }

    @Override
    public StudentEntity update(StudentEntity studentEntity) {
        return null;
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
