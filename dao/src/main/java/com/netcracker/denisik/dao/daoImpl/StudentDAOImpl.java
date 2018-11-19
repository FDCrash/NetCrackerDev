package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.DAO;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.storage.StudentList;
import com.netcracker.denisik.storage.UserList;

import java.util.ArrayList;
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
    public StudentEntity add(StudentEntity studentEntity) {
        StudentList.getInstance().add(studentEntity);
        UserList.getInstance().addStudent(studentEntity);
        return studentEntity;
    }

    @Override
    public StudentEntity update(StudentEntity student) {
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
        return student;
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

    public StudentEntity getByLogin(String login){
        for (StudentEntity studentEntity: getAll()){
            if(login.equals(studentEntity.getLogin())){
                return studentEntity;
            }
        }
        return null;
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

    public List<StudentEntity> getAllByGroup(int id){
        List<StudentEntity> studentEntities=new ArrayList<>();
        for(StudentEntity studentEntity:getAll()){
            if(studentEntity.getGroupId()==id){
                studentEntities.add(studentEntity);
            }
        }
        return studentEntities;
    }

    public List<StudentEntity> getAllBySpeciality(String speciality){
        List<StudentEntity> studentEntities=new ArrayList<>();
        for(StudentEntity studentEntity:getAll()){
            if(speciality.equals(studentEntity.getSpecialityEntity().getName())){
                studentEntities.add(studentEntity);
            }
        }
        return studentEntities;
    }
}
