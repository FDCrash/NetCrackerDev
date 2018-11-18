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
        boolean bool=false;
        System.out.println("Входное меню");
        do{
            System.out.println("1.Войти");
            System.out.println("2.Зарегистрироваться");
            System.out.println("0.Выйти");
            try {
                k=Integer.parseInt(scanner.next());
                switch (k) {
                    case 1:
                        authenticationMenu();
                        bool=true;
                        break;
                    case 2:
                        registrationMenu();
                        bool=true;
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Выберите позицию из списка");
                }
            }catch (NumberFormatException e){
                System.out.println("Введен неверный символ!");
            }
        }while(!bool);
    }

    private void registrationMenu(){
        int idStud;
        String pass,login;
        try {
            System.out.println("Введите свой номер студенченского билета:");
            idStud = Integer.parseInt(scanner.next());
            System.out.println("Введите желаемый логин:");
            login = scanner.next();
            System.out.println("Введите желаемый пароль:");
            pass = scanner.next();
            System.out.println(userService.registration(idStud,login,pass));
        }catch(NumberFormatException e){
            System.out.println("Введен неверный символ!");
        }
        startMenu();
    }

    private void authenticationMenu(){
        String pass;
        System.out.println("Введите логин:");
        login=scanner.next();
        System.out.println("Введите пароль:");
        pass=scanner.next();
        try{
            switch ((Role) userService.authentication(login, pass)) {
                case ADMIN:
                    adminService.changeStatusAdmin(login);
                    setRole(ADMIN) ;
                    adminMenu();
                    break;
                case EMPLOYEE:
                    setRole(EMPLOYEE);
                    employeeMenu();
                    break;
                case STUDENT:
                    setRole(STUDENT);
                    studentMenu();
                    break;
                default:
                    System.out.println("Неправильный логин или пароль");
                    startMenu();
            }
        }catch (NullPointerException e){
            System.out.println("Пользователя с таким логином не существует");
            startMenu();
        }
    }

    public void adminMenu(){
        int k;
        boolean bool=false;
        do {
            System.out.println("Меню админа:");
            System.out.println("1.CRUD Админов");
            System.out.println("2.CRUD Сотрудников");
            System.out.println("3.CRUD Студентов");
            System.out.println("4.CRUD Пользователей");
            System.out.println("5.CRUD Факультетов");
            System.out.println("6.CRUD Специальностей");
            System.out.println("0.Выйти");
            try {
                k = Integer.parseInt(scanner.next());
                switch (k) {
                    case 1:
                        adminController.editMenu();
                        bool=true;
                        break;
                    case 2:
                        employeeController.editMenu();
                        bool=true;
                        break;
                    case 3:
                        studentController.editMenu();
                        bool=true;
                        break;
                    case 4:
                        userController.editMenu();
                        bool=true;
                        break;
                    case 5:
                        facultyController.editMenu();
                        bool=true;
                        break;
                    case 6:
                        specialityController.editMenu();
                        bool=true;
                        break;
                    case 0:
                        startMenu();
                        bool=true;
                        return;
                    default:
                        System.out.println("Выберите позицию из списка");
                }
            }catch (NumberFormatException e){
                System.out.println("Введен не верный символ!");
            }
        }while(!bool);
    }

    public void employeeMenu(){
        int k;
        boolean bool=false;
        do {
            System.out.println("Меню сотрудника:");
            System.out.println("1.CRUD Студентов");
            System.out.println("2.CRUD Специальностей");
            System.out.println("0.Выйти");
            try {
                k = Integer.parseInt(scanner.next());
                switch (k) {
                    case 1:
                        studentController.editMenu();
                        bool=true;
                        break;
                    case 2:
                        specialityController.editMenu();
                        bool=true;
                        break;
                    case 0:
                        startMenu();
                        bool=true;
                        return;
                    default:
                        System.out.println("Выберите позицию из списка");
                }
            }catch (NumberFormatException e){
                System.out.println("Введен не верный символ!");
            }
        }while(!bool);
    }

    public void studentMenu(){
        int k;
        boolean bool=false;
        do {
            System.out.println("Меню студента:");
            System.out.println("1.Просмотреть зачетку");
            System.out.println("2.Просмотреть группу");
            System.out.println("3.Просмотреть специальность");
            System.out.println("0.Выйти");
            try {
                k = Integer.parseInt(scanner.next());
                switch (k) {
                    case 1:
                        studentController.showWriteBook();
                        bool=true;
                        break;
                    case 2:
                        studentController.getAllByGroup();
                        bool=true;
                        break;
                    case 3:
                        studentController.getAllBySpeciality();
                        bool=true;
                        break;
                    case 0:
                        startMenu();
                        bool=true;
                        return;
                    default:
                        System.out.println("Выберите позицию из списка");
                }
            }catch (NumberFormatException e){
                System.out.println("Введен не верный символ!");
            }
        }while(!bool);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
