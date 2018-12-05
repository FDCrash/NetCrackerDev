package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.EmployeeDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.servicesimpl.EmployeeServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeControllerImpl implements Controller {
    private Scanner scanner;

    public EmployeeControllerImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void editMenu() {
        int point;
        do {
            System.out.println("CRUD сотрудников:");
            System.out.println("1.Список сотрудников");
            System.out.println("2.Добавить");
            System.out.println("3.Изменить");
            System.out.println("4.Удалить");
            System.out.println("0.Выйти");
            point = Integer.parseInt(scanner.nextLine());
            switchChange(point);
        } while (point < 0 || point > 4);

    }

    @Override
    public void getAll() {
        System.out.println("Сотрудники:");
        for (EmployeeDTO adminDTO : EmployeeServiceImpl.getInstance().getAll()) {
            String s = adminDTO.toString();
            System.out.println(s);
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
        if (UserServiceImpl.getInstance().checkLogin(login)) {
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();
            EmployeeDTO employeeDTO=new EmployeeDTO();
            employeeDTO.setId(UserServiceImpl.getInstance().generateId(1000));
            employeeDTO.setRoleDTO(RoleDTO.EMPLOYEE);
            employeeDTO.setLogin(login);
            employeeDTO.setPassword(password);
            employeeDTO.setName(name);
            EmployeeServiceImpl.getInstance().add(employeeDTO);
        } else {
            System.out.println("Логин занят");
        }
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Сотрудники:");
        int iterator = 1;
        EmployeeDTO employeeDTO;
        for (EmployeeDTO employee : EmployeeServiceImpl.getInstance().getAll()) {
            String s = employee.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для изменения: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            employeeDTO = EmployeeServiceImpl.getInstance().getAll().get(index - 1);
            System.out.println(employeeDTO);
            System.out.println("Введите имя: ");
            String name = scanner.nextLine();
            System.out.println("Введите логин: ");
            String login = scanner.nextLine();
            if (login.equals(employeeDTO.getName()) || UserServiceImpl.getInstance().checkLogin(login)) {
                System.out.println("Введите пароль: ");
                String password = scanner.nextLine();
                employeeDTO.setLogin(login);
                employeeDTO.setPassword(password);
                employeeDTO.setName(name);
                EmployeeServiceImpl.getInstance().update(employeeDTO);
            } else {
                System.out.println("Новый логин занят");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Сотрудники:");
        int i = 1;
        for (EmployeeDTO employeeDTO : EmployeeServiceImpl.getInstance().getAll()) {
            String s = employeeDTO.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позиция для удаления: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            EmployeeServiceImpl.getInstance().delete(EmployeeServiceImpl.getInstance().getAll().get(index - 1).getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }
}

