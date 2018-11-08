package services;

import java.util.List;

public interface CRUDService<T> {
    public void addNew(T t);
    public void deleteInfo(int id);
    public void updateInfo(T t,int id);
    public List<T> getAll();
    public T get(int id);
}
