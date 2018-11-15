package controllers;

import controllers.controllersimpl.*;
import daomodule.entities.Role;
import services.servicesimpl.AdminServiceImpl;
import services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

import static daomodule.entities.Role.ADMIN;
import static daomodule.entities.Role.EMPLOYEE;
import static daomodule.entities.Role.STUDENT;

public class Menu {
    private Role role;
    private String login;
    private UserServiceImpl userService;
    private Scanner scanner;
    private StudentControllerImpl studentController;
    private EmployeeControllerImpl employeeController;
    private AdminControllerImpl adminController;
    private SpecialityControllerImpl specialityController;
    private FacultyControllerImpl facultyController;
    private AdminServiceImpl adminService;
    private static Menu instance;
    private UserControllerImpl userController;

    public static Menu getInstance(){
        if(instance==null){
            instance=new Menu();
        }
        return instance;
    }

    private Menu(){
        userService = new UserServiceImpl();
        scanner = new Scanner(System.in);
        studentController = new StudentControllerImpl();
        employeeController = new EmployeeControllerImpl();
        adminController = new AdminControllerImpl();
        facultyController = new FacultyControllerImpl();
        specialityController = new SpecialityControllerImpl();
        adminService = new AdminServiceImpl();
        userController = new UserControllerImpl();
    }

    public void startMenu() {
        int k;
        System.out.println("Входное меню");
        do{
            System.out.println("1.Войти");
            System.out.println("2.Зарегистрироваться");
            System.out.println("0.Выйти");
            k=scanner.nextInt();
            switch (k) {
                case 1:
                    authenticationMenu();
                    break;
                case 2:
                    registrationMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<0 || k>2);
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
        String pass;
        System.out.println("Введите логин:");
        login=scanner.next();
        System.out.println("Введите пароль:");
        pass=scanner.next();
        switch ((Role)userService.authentication(login,pass)){
            case ADMIN: adminService.changeStatusAdmin(login); setRole(ADMIN); adminMenu();
            break;
            case EMPLOYEE: setRole(EMPLOYEE); employeeMenu();
            break;
            case STUDENT: setRole(STUDENT); studentMenu();
            break;
            default: System.out.println("Неправильный логин или пароль");
        }
    }

    public void adminMenu(){
        int k;
        do {
            System.out.println("Меню админа:");
            System.out.println("1.CRUD Админов");
            System.out.println("2.CRUD Сотрудников");
            System.out.println("3.CRUD Студентов");
            System.out.println("4.CRUD Пользователей");
            System.out.println("5.CRUD Факультетов");
            System.out.println("6.CRUD Специальностей");
           System.out.println("0.Выйти");
            k=scanner.nextInt();
            switch (k){
                case 1: adminController.editMenu();
                    break;
                case 2: employeeController.editMenu();
                    break;
                case 3: studentController.editMenu();
                    break;
                case 4: userController.editMenu();
                    break;
                case 5: facultyController.editMenu();
                    break;
                case 6: specialityController.editMenu();
                    break;
                case 0: startMenu();
                    break;
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<0 || k>5);
    }

    public void employeeMenu(){
        int k;
        do {
            System.out.println("Меню сотрудника:");
            System.out.println("1.CRUD Студентов");
            System.out.println("2.CRUD Специальностей");
            System.out.println("0.Выйти");
            k=scanner.nextInt();
            switch (k){
                case 1: studentController.editMenu();
                    break;
                case 2: specialityController.editMenu();
                    break;
                case 0: startMenu();
                    break;
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<0 || k>2);
    }

    public void studentMenu(){
        int k;
        do {
            System.out.println("Меню студента:");
            System.out.println("1.Просмотреть зачетку");
            System.out.println("2.Просмотреть группу");
            System.out.println("3.Просмотреть специальность");
            System.out.println("0.Выйти");
            ///
            //try
            k=scanner.nextInt();
            switch (k){
                case 1: studentController.showWriteBook();
                    break;
                case 2: studentController.getAllByGroup();
                    break;
                case 3: studentController.getAllBySpeciality();
                    break;
                case 0: startMenu();
                    break;
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<0 || k>3);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

}
