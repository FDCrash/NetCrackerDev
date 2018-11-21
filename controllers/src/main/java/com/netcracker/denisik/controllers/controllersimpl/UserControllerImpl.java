package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.*;
import com.netcracker.denisik.services.servicesimpl.AdminServiceImpl;
import com.netcracker.denisik.services.servicesimpl.EmployeeServiceImpl;
import com.netcracker.denisik.services.servicesimpl.StudentServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

import static com.netcracker.denisik.dto.RoleDTO.*;

public class UserControllerImpl implements Controller {
    private UserServiceImpl userService;
    private Scanner scanner;
    private AdminServiceImpl adminService;
    private EmployeeServiceImpl employeeService;
    private StudentServiceImpl studentService;

    public UserControllerImpl(){
        userService = new UserServiceImpl();
        scanner= new Scanner(System.in);
        adminService = new AdminServiceImpl();
        employeeService=new EmployeeServiceImpl();
        studentService = new StudentServiceImpl();
    }

    @Override
    public void editMenu() {
        int k;
        do {
            System.out.println("CRUD пользователей:");
            System.out.println("1.Список пользователей");
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
        System.out.println("Пользователи:");
        for(UserDTO userDTO:userService.getAll()){
            String s = userDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый пользователь");
        System.out.println("Введите роль(admin/employee/student):");
        String role=scanner.next();
        System.out.println("Введите логин: ");
        String login=scanner.next();
        if(userService.checkLogin(login)) {
            System.out.println("Введите пароль: ");
            String password = scanner.next();
            int k = userService.generateId(1000);
            userService.addNew(new UserDTO(k, RoleDTO.valueOf(role.toUpperCase()),
                    login, password));
        }else{
            System.out.println("Логин занят");
        }
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Пользователи:");
        int z=1;
        String password;
        UserDTO userDTO;
        for(UserDTO user:userService.getAll()){
            String s = user.toString();
            System.out.println(z+". " + s);
            z++;
        }
        System.out.println("Выберите позицию для изменения: ");
        int k=Integer.parseInt(scanner.next());
        userDTO=userService.getAll().get(k-1);
        System.out.println(userDTO.toString());
        System.out.println("Введите роль (admin/employee):");
        String role=scanner.next();
        System.out.println("Введите логин: ");
        String login = scanner.next();
        if(login.equals(userDTO.getLogin()) || userService.checkLogin(login)) {
            switch (RoleDTO.valueOf(role.toUpperCase())) {
                case ADMIN:
                    System.out.println("Введите пароль: ");
                    password = scanner.next();
                    if (userDTO.getRoleDTO().equals(ADMIN)) {
                        userService.updateInfo(new UserDTO(userDTO.getId(),ADMIN,login,password));
                    } else {
                        if (userDTO.getRoleDTO().equals(RoleDTO.EMPLOYEE)) {
                            employeeService.deleteInfo(userDTO.getId());
                        }
                        if (userDTO.getRoleDTO().equals(RoleDTO.STUDENT)) {
                            studentService.deleteInfo(userDTO.getId());
                        }
                        adminService.addNew(new AdminDTO(new UserDTO(userDTO.getId(),
                                ADMIN, login, password), false));
                    }
                    break;
                case EMPLOYEE:
                    System.out.println("Введите пароль: ");
                    password = scanner.next();
                    if (userDTO.getRoleDTO().equals(RoleDTO.EMPLOYEE)) {
                        userService.updateInfo(new UserDTO(userDTO.getId(),EMPLOYEE,login,password));
                    } else {
                        if (userDTO.getRoleDTO().equals(ADMIN)) {
                            adminService.deleteInfo(userDTO.getId());
                        }
                        if (userDTO.getRoleDTO().equals(RoleDTO.STUDENT)) {
                            studentService.deleteInfo(userDTO.getId());
                        }
                        System.out.println("Введите имя: ");
                        String name = scanner.next();
                        employeeService.addNew(new EmployeeDTO(new UserDTO(userDTO.getId(),
                                RoleDTO.EMPLOYEE, login, password), name));
                    }
                    break;
                case STUDENT:
                    System.out.println("Логины и пароли студентов менять не нужно");
                    break;
                default:
                    System.out.println("Неверно введена роль!");
                    break;
            }
        }else {
            System.out.println("Логин занят");
        }
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Пользователи:");
        int i=1;
        for(UserDTO userDTO:userService.getAll()){
            String s = userDTO.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        int k=Integer.parseInt(scanner.next());
        if(userService.getAll().get(k-1).getRoleDTO().equals(ADMIN)){
            System.out.println("Вы хотите удалить админа, перейдите в CRUD админов!");
        }else {
            userService.deleteInfo(userService.getAll().get(k-1).getId());
        }
        editMenu();
    }
}
