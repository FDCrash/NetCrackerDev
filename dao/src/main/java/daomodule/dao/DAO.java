package daomodule.dao;

import java.util.List;

public interface DAO<T> {
    T get(int id);
    List<T> getAll();
    void add(T t);
    void update(T t);
    void delete(int id);
}
