package controllers;

public interface ControllerMD {
    default void switchChange(int k){
    }
    void editMenu();
    void getAll();
    void add();
    void update();
    void delete();
}
