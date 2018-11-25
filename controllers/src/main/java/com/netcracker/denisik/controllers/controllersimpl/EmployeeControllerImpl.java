package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.EmployeeDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.servicesimpl.EmployeeServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EmployeeControllerImpl implements Controller {
    private EmployeeServiceImpl employeeService;
    private UserServiceImpl userService;
    private Scanner scanner;

    public EmployeeControllerImpl() {
        employeeService = new EmployeeServiceImpl();
        scanner = new Scanner(System.in);
        userService = new UserServiceImpl();
    }

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
            k = Integer.parseInt(scanner.nextLine());
            switchChange(k);
        } while (k < 0 || k > 4);

    }

    @Override
    public void getAll() {
        System.out.println("Сотрудники:");
        try{
            for (EmployeeDTO adminDTO : employeeService.getAll()) {
                String s = adminDTO.toString();
                System.out.println(s);
            }
        }catch (NullPointerException | NoSuchElementException e){
            System.out.println("Сотрудники отсутствуют");
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый сотрудник");
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        if (userService.checkLogin(login)) {
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();
            employeeService.addNew(new EmployeeDTO(new UserDTO(userService.generateId(1000), RoleDTO.EMPLOYEE,
                    login, password), name));
        } else {
            System.out.println("Логин занят");
        }
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Сотрудники:");
        int i = 1;
        EmployeeDTO employeeDTO;
        for (EmployeeDTO employee : employeeService.getAll()) {
            String s = employee.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позицию для изменения: ");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            employeeDTO = employeeService.getAll().get(k - 1);
            System.out.println(employeeDTO);
            System.out.println("Введите имя: ");
            String name = scanner.nextLine();
            System.out.println("Введите логин: ");
            String login = scanner.nextLine();
            if (login.equals(employeeDTO.getName()) || userService.checkLogin(login)) {
                System.out.println("Введите пароль: ");
                String password = scanner.nextLine();
                employeeService.updateInfo(new EmployeeDTO(new UserDTO(employeeDTO.getId(), RoleDTO.EMPLOYEE,
                        login, password), name));
            } else {
                System.out.println("Новый логин занят");
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Сотрудники:");
        int i = 1;
        for (EmployeeDTO adminDTO : employeeService.getAll()) {
            String s = adminDTO.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позиция для удаления: ");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            employeeService.deleteInfo(employeeService.getAll().get(k - 1).getId());
        }catch (IndexOutOfBoundsException e){
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }
}

