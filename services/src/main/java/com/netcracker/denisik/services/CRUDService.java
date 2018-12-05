package com.netcracker.denisik.services;

import java.util.List;

public interface CRUDService<T> {
    void add(T t);

    void delete(int id);

    void update(T t);

    List<T> getAll();

    T get(int id);
}
