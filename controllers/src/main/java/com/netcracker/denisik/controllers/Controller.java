package com.netcracker.denisik.controllers;

import java.sql.SQLException;

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
                switch (Menu.getInstance().getRole()) {
                    case ADMIN:
                        Menu.getInstance().adminMenu();
                        break;
                    case EMPLOYEE:
                        Menu.getInstance().employeeMenu();
                        break;
                    case STUDENT:
                        Menu.getInstance().studentMenu();
                        break;
                }
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
