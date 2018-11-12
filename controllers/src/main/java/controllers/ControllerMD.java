package controllers;

public interface ControllerMD {
    default void switchChange(int k){
        switch (k){
            case 1: getAll();
                break;
            case 2: add();
                break;
            case 3: update();
                break;
            case 4: delete();
                break;
            case 0: new Menu().startMenu();
            default:
                System.out.println("Выберите позицию из списка");
        }
    }
    void editMenu();
    void getAll();
    void add();
    void update();
    void delete();
}
