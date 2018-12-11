package com.netcracker.denisik.controllers;


import org.springframework.beans.factory.annotation.Autowired;

public interface Controller {
    default void switchChange(int point) {
        switch (point) {
            case 1:
                getAll();
                break;
            case 2:
                add();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
            case 0:
//                switch (new Menu().getRole()) {
//                    case ADMIN:
//                        new Menu().adminMenu();
//                        break;
//                    case EMPLOYEE:
//                        new Menu().employeeMenu();
//                        break;
//                    case STUDENT:
//                        new Menu().studentMenu();
//                        break;
//                }
                break;
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
