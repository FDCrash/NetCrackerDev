package com.netcracker.denisik.dao;

import com.netcracker.denisik.entities.User;
import com.netcracker.denisik.sql.SQLConstant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getByLogin(String login);

    User getRoleByLoginAndPassword(String login, String password);
//
//    @Modifying
//    @Query(value = SQLConstant., nativeQuery = true)
//    void setLoginAndPassword(String login,String password,long id);
}
