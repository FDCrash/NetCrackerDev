package com.netcracker.denisik.dao;

import java.util.List;

public interface DAO<T> {
    T get(int id);
    List<T> getAll();
    T add(T t);
    T update(T t);
    void delete(int id);
}
