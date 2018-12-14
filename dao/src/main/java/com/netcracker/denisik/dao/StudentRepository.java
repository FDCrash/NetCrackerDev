package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

    Student getByStudentId(int studentId);

    List<Student> getAllBySpecialityEntity_Name(String speciality);

    List<Student> getAllByGroupId(int groupId);
}
