package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity,Integer> {

    StudentEntity getByLogin(String login);

    boolean existsByStudentId(int studentId);

    void addNewLoginPass(int id,String login,String password);

    List<StudentEntity> getAllBySpecialityEntity(String speciality);

    List<StudentEntity> getAllByGroupId(int groupId);

    void setDefaultSpeciality(int id);
}
