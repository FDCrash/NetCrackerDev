package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.AdminDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.servicesimpl.AdminServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

public class AdminControllerImpl implements Controller {
    private AdminServiceImpl adminService;
    private Scanner scanner;
    private UserServiceImpl userService;


    public AdminControllerImpl() {
        scanner = new Scanner(System.in);
        adminService = new AdminServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    public void editMenu() {
        int k;
        do {
            System.out.println("CRUD админов:");
            System.out.println("1.Список админов");
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
        System.out.println("Адинистраторы:");
        for (AdminDTO adminDTO : adminService.getAll()) {
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
            String password = scanner.next();
            adminService.addNew(new AdminDTO(new UserDTO(userService.generateId(1000), RoleDTO.ADMIN,
                    login, password), false));
        } else {
            System.out.println("Логин занят");
        }
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Адинистраторы:");
        int i = 1;
        AdminDTO adminDTO;
        for (AdminDTO admin : adminService.getAll()) {
            String s = admin.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позицию для изменения: ");
        int k = Integer.parseInt(scanner.next());
        adminDTO = adminService.getAll().get(k - 1);
        System.out.println(adminDTO.toString());
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        if (login.equals(adminDTO.getLogin()) || userService.checkLogin(login)) {
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();
            adminService.updateInfo(new AdminDTO(new UserDTO(adminDTO.getId(), RoleDTO.ADMIN,
                    login, password), false));
        } else {
            System.out.println("Новый логин занят");
        }
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Адинистраторы:");
        int i = 1;
        for (AdminDTO adminDTO : adminService.getAll()) {
            String s = adminDTO.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        int k = Integer.parseInt(scanner.nextLine());
        adminService.deleteInfo(adminService.getAll().get(k - 1).getId());
        editMenu();
    }
}
