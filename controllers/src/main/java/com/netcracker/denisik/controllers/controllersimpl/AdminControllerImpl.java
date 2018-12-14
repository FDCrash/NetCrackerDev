package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

@org.springframework.stereotype.Controller
public class AdminControllerImpl implements Controller {
    private Scanner scanner;

    @Autowired
    private UserServiceImpl userService;

    public AdminControllerImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void editMenu() {
        int point;
        do {
            System.out.println("CRUD админов:");
            System.out.println("1.Список админов");
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
        System.out.println("Адинистраторы:");
        for (UserDTO adminDTO : userService.getAllAdmins()) {
            String s = adminDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый администратор");
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        if (userService.checkLogin(login)) {
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();

            String name="";
            UserDTO userDTO=new UserDTO();
            userDTO.setRoleDTO(RoleDTO.ADMIN);
            userDTO.setLogin(login);
            userDTO.setPassword(password);
            userDTO.setName(name);
            userService.add(userDTO);
        } else {
            System.out.println("Логин занят");
        }
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Адинистраторы:");
        int iterator = 1;
        UserDTO adminDTO;
        for (UserDTO admin : userService.getAll()) {
            String s = admin.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для изменения: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            adminDTO = userService.getAll().get(index - 1);
            System.out.println(adminDTO.toString());
            System.out.println("Введите логин: ");
            String login = scanner.nextLine();
            if (login.equals(adminDTO.getLogin()) || userService.checkLogin(login)) {
                System.out.println("Введите пароль: ");
                String password = scanner.nextLine();
                adminDTO.setLogin(login);
                adminDTO.setPassword(password);
                userService.update(adminDTO);
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
        System.out.println("Адинистраторы:");
        int iterator = 1;
        for (UserDTO adminDTO : userService.getAll()) {
            String s = adminDTO.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для удаления: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            userService.delete(userService.getAll().get(index - 1).getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }
}
