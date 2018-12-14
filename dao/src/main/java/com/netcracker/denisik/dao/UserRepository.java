package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User getByLogin(String login);

    User getRoleByLoginAndPassword(String login, String password);
}
