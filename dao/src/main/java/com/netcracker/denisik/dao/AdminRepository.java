package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.AdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<AdminEntity,Integer> {
    void changeStatus(String login);
}
