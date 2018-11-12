package controllers.controllersimpl;

import controllers.ControllerMD;
import services.servicesimpl.UserService;

import java.util.Scanner;

public class EmployeeControllerImpl implements ControllerMD {
    private UserService userService;
    private Scanner scanner;

    @Override
    public void editMenu() {
        int k;
        do {
            System.out.println("CRUD сотрудников:");
            System.out.println("1.Список сотрудников");
            System.out.println("2.Добавить");
            System.out.println("3.Изменить");
            System.out.println("4.Удалить");
            System.out.println("0.Выйти");
            k=scanner.nextInt();
            switchChange(k);
        }while(k<0 || k>4);

    }

    @Override
    public void getAll() {

    }

    @Override
    public void add() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}

