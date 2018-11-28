package com.netcracker.denisik.services;

import java.sql.SQLException;
import java.util.List;

public interface CRUDService<T> {
    void add(T t) throws SQLException;

    void delete(int id) throws SQLException;

    void update(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    T get(int id) throws SQLException;
}
