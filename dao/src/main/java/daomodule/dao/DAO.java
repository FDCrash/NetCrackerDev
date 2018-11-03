package daomodule.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    public Optional<T> get(T t);

    public List<T> getAll();

    public void add(T t);

    public void update(T t);

    public void delete(T t);

}
