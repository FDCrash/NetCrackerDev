package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.FacultyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends CrudRepository<FacultyEntity,Integer> {

    FacultyEntity getFacultyEntityBySpeciality(int specialityId);

}
