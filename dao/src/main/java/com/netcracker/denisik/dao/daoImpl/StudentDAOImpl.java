package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.IDao;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.storage.StudentList;
import com.netcracker.denisik.storage.UserList;

import java.util.List;
import java.util.stream.Collectors;

public class StudentDAOImpl implements IDao<StudentEntity> {
    @Override
    public StudentEntity get(int id) {
        return getAll().stream()
                .filter(student -> student.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<StudentEntity> getAll() {
        return StudentList.getInstance().get();
    }

    @Override
    public StudentEntity add(StudentEntity studentEntity) {
        StudentList.getInstance().add(studentEntity);
        UserList.getInstance().addStudent(studentEntity);
        return get(studentEntity.getId());
    }

    @Override
    public StudentEntity update(StudentEntity student) {
        delete(student.getId());
        add(student);
        return get(student.getId());
    }

    @Override
    public void delete(int id) {
        getAll().remove(get(id));
        new UserDAOImpl().getAll().remove(new UserDAOImpl().get(id));
    }

    public StudentEntity getByLogin(String login) {
        return getAll().stream()
                .filter(student-> student.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public boolean checkStudId(int id) {
        return getAll().stream()
                .anyMatch(student-> student.getStudentId()==id);
    }

    public void addNewLoginPass(int id, String login, String pass) {
        int k = 0;
        for (StudentEntity studentEntity : getAll()) {
            if (studentEntity.getStudentId() == id) {
                studentEntity.setLogin(login);
                studentEntity.setPassword(pass);
                k = studentEntity.getId();
                break;
            }
        }
        for (UserEntity userEntity : UserList.getInstance().get()) {
            if (userEntity.getId() == k) {
                userEntity.setLogin(login);
                userEntity.setPassword(pass);
                break;
            }
        }
    }

    public List<StudentEntity> getAllByGroup(int id) {
        return getAll().stream()
                .filter(student -> student.getGroupId()==id)
                .collect(Collectors.toList());
    }

    public List<StudentEntity> getAllBySpeciality(String speciality) {
        return getAll().stream()
                .filter(student -> student.getSpecialityEntity().getName().equals(speciality))
                .collect(Collectors.toList());
    }
}
