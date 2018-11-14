package daomodule.storage;

import java.util.List;

public interface Storage<T> {
    List<T> get();
    void set(List<T> t);
    void add(T t);
}
