package com.netcracker.denisik.controllers;

import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.netcracker.denisik.entities.Role.*;

@Controller
public class Menu {
    private Role role;
    private String login;
    private Scanner scanner;
//    private StudentControllerImpl studentController;
//    private EmployeeControllerImpl employeeController;
    private AdminControllerImpl adminController;
    private SpecialityControllerImpl specialityController;
    private FacultyControllerImpl facultyController;
//    private UserControllerImpl userController;
    private UserServiceImpl userService;
//    @Autowired
//    private AdminServiceImpl adminService;

@Autowired
    public Menu(UserServiceImpl userService,AdminControllerImpl adminController) {
        scanner = new Scanner(System.in);
//        studentController = new StudentControllerImpl();
//        employeeController = new EmployeeControllerImpl();
        this.adminController = adminController;
        facultyController = new FacultyControllerImpl();
        specialityController = new SpecialityControllerImpl();
    //    userController = new UserControllerImpl();
        this.userService=userService;
    }

    public void startMenu() {
        int point;
        boolean action = false;
        System.out.println("Входное меню");
        do {
            System.out.println("1.Войти");
            System.out.println("2.Зарегистрироваться");
            System.out.println("0.Выйти");
            try {
                point = Integer.parseInt(scanner.nextLine());
                switch (point) {
                    case 1:
                        authenticationMenu();
                        action = true;
                        break;
                    case 2:
                        registrationMenu();
                        action = true;
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Выберите позицию из списка");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введен неверный символ!");
            }
        } while (!action);
    }

    private void registrationMenu() {
        int studentId;
        String pass, login;
        try {
            System.out.println("Введите свой номер студенченского билета:");
            studentId = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите желаемый логин:");
            login = scanner.nextLine();
            System.out.println("Введите желаемый пароль:");
            pass = scanner.nextLine();
            if(userService.registration(studentId, login, pass)){
                System.out.println("Вы успешно зарегестрированы");
            }else{
                System.out.println("Ошибка регистрации");
            }
        } catch (NumberFormatException e) {
            System.out.println("Введен неверный символ!");
        }
        startMenu();
    }

    private void authenticationMenu() {
        String pass;
        System.out.println("Введите логин:");
        login = scanner.nextLine();
        System.out.println("Введите пароль:");
        pass = scanner.nextLine();
        try {
            switch ((Role) userService.authentication(login, pass)) {
                case ADMIN:
                 //   adminService.changeStatusAdmin(login);
                    setRole(ADMIN);
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
        } catch (NullPointerException | NoSuchElementException e) {
            System.out.println("Неверный логин или пароль");
            startMenu();
        }
    }

    public void adminMenu() {
        int point;
        boolean action = false;
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
                point = Integer.parseInt(scanner.nextLine());
                switch (point) {
                    case 1:
                        adminController.editMenu();
                        action = true;
                        break;
                    case 2:
//                        employeeController.editMenu();
//                        action = true;
//                        break;
//                    case 3:
//                        studentController.editMenu();
//                        action = true;
//                        break;
//                    case 4:
//                        userController.editMenu();
//                        action = true;
//                        break;
                    case 5:
                        facultyController.editMenu();
                        action = true;
                        break;
                    case 6:
                        specialityController.editMenu();
                        action = true;
                        break;
                    case 0:
                        startMenu();
                        action = true;
                        return;
                    default:
                        System.out.println("Выберите позицию из списка");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введен не верный символ!");
            }
        } while (!action);
    }

    public void employeeMenu() {
        int point;
        boolean action = false;
        do {
            System.out.println("Меню сотрудника:");
            System.out.println("1.CRUD Студентов");
            System.out.println("2.CRUD Специальностей");
            System.out.println("0.Выйти");
            try {
                point = Integer.parseInt(scanner.nextLine());
                switch (point) {
                    case 1:
                        //studentController.editMenu();
                        action = true;
                        break;
                    case 2:
                        specialityController.editMenu();
                        action = true;
                        break;
                    case 0:
                        startMenu();
                        action = true;
                        return;
                    default:
                        System.out.println("Выберите позицию из списка");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введен не верный символ!");
            }
        } while (!action);
    }

    public void studentMenu() {
        int point;
        boolean action = false;
        do {
            System.out.println("Меню студента:");
            System.out.println("1.Просмотреть зачетку");
            System.out.println("2.Просмотреть группу");
            System.out.println("3.Просмотреть специальность");
            System.out.println("0.Выйти");
            try {
                point = Integer.parseInt(scanner.nextLine());
                switch (point) {
//                    case 1:
//                        studentController.showWriteBook();
//                        action = true;
//                        break;
//                    case 2:
//                        studentController.getAllByGroup();
//                        action = true;
//                        break;
//                    case 3:
//                        studentController.getAllBySpeciality();
//                        action = true;
//                        break;
                    case 0:
                        startMenu();
                        action = true;
                        return;
                    default:
                        System.out.println("Выберите позицию из списка");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введен не верный символ!");
            }
        } while (!action);
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
