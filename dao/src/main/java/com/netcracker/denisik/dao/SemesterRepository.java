package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.Semester;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends CrudRepository<Semester,Long> {
}
