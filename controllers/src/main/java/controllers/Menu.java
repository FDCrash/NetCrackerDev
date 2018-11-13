package controllers;

import controllers.controllersimpl.*;
import daomodule.entities.Role;
import services.servicesimpl.UserService;

import java.util.Scanner;

public class Menu {
    private UserService userService;
    private Scanner scanner;
    private StudentControllerImpl studentController;
    private EmployeeControllerImpl employeeController;
    private AdminControllerImpl adminController;
    private SpecialityControllerImpl specialityController;
    private FacultyControllerImpl facultyController;

    public Menu(){
        userService = new UserService();
        scanner = new Scanner(System.in);
        studentController = new StudentControllerImpl();
        employeeController = new EmployeeControllerImpl();
        adminController = new AdminControllerImpl();
        facultyController = new FacultyControllerImpl();
        specialityController = new SpecialityControllerImpl();
    }

    public void startMenu() {
        int k;
        System.out.println("Входное меню");
        do{
            System.out.println("1.Войти");
            System.out.println("2.Зарегистрироваться");
            k=scanner.nextInt();
            switch (k) {
                case 1:
                    authenticationMenu();
                    break;
                case 2:
                    registrationMenu();
                    break;
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<1 || k>2);
    }

    public void registrationMenu(){
        int idStud;
        String pass,login;
        System.out.println("Введите свой номер студенченского билета:");
        idStud=scanner.nextInt();
        System.out.println("Введите желаемый логин:");
        login = scanner.next();
        System.out.println("Введите желаемый пароль:");
        pass = scanner.next();
        System.out.println(userService.registration(idStud,login,pass));
        startMenu();
    }

    public void authenticationMenu(){
        String login,pass;
        System.out.println("Введите логин:");
        login=scanner.next();
        System.out.println("Введите пароль:");
        pass=scanner.next();
        switch ((Role)userService.authentication(login,pass)){
            case ADMIN: userService.changeStatusAdmin(login); adminMenu();
            break;
            case EMPLOYEE: employeeMenu();
            break;
            case STUDENT: studentMenu();
            break;
            default: System.out.println("Неправильный логин или пароль");
        }
    }

    public void adminMenu(){
        int k;
        do {
            System.out.println("Меню админа:");
            System.out.println("1.Поиск информации");
            System.out.println("2.CRUD Админов");
            System.out.println("3.CRUD Сотрудников");
            System.out.println("4.CRUD Студентов");
            System.out.println("5.CRUD Факультетов");
            System.out.println("6.CRUD Специальностей");
           System.out.println("0.Выйти");
            k=scanner.nextInt();
            switch (k){
                case 1: searchInfo();
                    break;
                case 2: adminController.editMenu();
                    break;
                case 3: employeeController.editMenu();
                    break;
                case 4: studentController.editMenu();
                    break;
                case 5: facultyController.editMenu();
                    break;
                case 6: specialityController.editMenu();
                    break;
                case 0: startMenu();
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<0 || k>6);
    }

    public void employeeMenu(){
        int k;
        do {
            System.out.println("Меню сотрудника:");
            System.out.println("1.Поиск информации");
            System.out.println("2.CRUD Студентов");
            System.out.println("3.CRUD Специальностей");
            System.out.println("0.Выйти");
            k=scanner.nextInt();
            switch (k){
                case 1: searchInfo();
                    break;
                case 2: studentController.editMenu();
                    break;
                case 3: specialityController.editMenu();
                    break;
                case 0: startMenu();
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<0 || k>3);
    }

    public void studentMenu(){
        int k;
        do {
            System.out.println("Меню студента:");
            System.out.println("1.Просмотреть зачетку");
            System.out.println("2.Просмотреть группу");
            System.out.println("3.Просмотреть специальность");
            System.out.println("0.Выйти");
            k=scanner.nextInt();
            switch (k){
                case 1: studentController.showWriteBook();
                    break;
                case 2: studentController.getAllByGroup();
                    break;
                case 3: studentController.getAllBySpeciality();
                    break;
                case 0: startMenu();
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<0 || k>3);
    }

    public void searchInfo(){}
}
