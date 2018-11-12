package services;

import java.util.List;

public interface CRUDService<T> {
    void addNew(T t);
    void deleteInfo(int id);
    void updateInfo(T t,int id);
    List<T> getAll();
    T get(int id);
}
