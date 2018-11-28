package com.netcracker.denisik.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    T get(int id);

    List<T> getAll();

    T add(T t) throws SQLException;

    T update(T t);

    void delete(int id);
}
