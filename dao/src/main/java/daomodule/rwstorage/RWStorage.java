package daomodule.rwstorage;

public interface RWStorage {
    void fillStorage();
    default void writeFile(){}
}
