package daomodule.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T get(int id);
    List<T> getAll();
    void add(T t);
    void update(T t);
    void delete(T t);
}
