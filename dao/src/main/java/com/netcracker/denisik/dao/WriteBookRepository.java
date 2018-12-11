package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.WriteBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteBookRepository extends CrudRepository<WriteBook,Long> {
}
