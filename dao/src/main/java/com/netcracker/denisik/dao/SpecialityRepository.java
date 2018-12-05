package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.SpecialityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends CrudRepository<SpecialityEntity,Integer> {
    List<SpecialityEntity> getAllByFaculty(int facultyId);
}
