package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    boolean existsByLogin(String login);

    Enum getRoleByLoginAndPassword(String login,String password);


}
