package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.AdminDTO;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.servicesimpl.AdminServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AdminControllerImpl implements Controller {
    private Scanner scanner;

    public AdminControllerImpl() {
        scanner = new Scanner(System.in);
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
        try{
            for (AdminDTO adminDTO : AdminServiceImpl.getInstance().getAll()) {
                String s = adminDTO.toString();
                System.out.println(s);
            }
        }catch (NullPointerException | NoSuchElementException e){
            System.out.println("Администраторы отсутствуют");
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый администратор");
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
       if (UserServiceImpl.getInstance().checkLogin(login)) {
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();
            AdminServiceImpl.getInstance().addNew(new AdminDTO(new UserDTO(UserServiceImpl.getInstance()
                    .generateId(1000), RoleDTO.ADMIN,
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
        for (AdminDTO admin :  AdminServiceImpl.getInstance().getAll()) {
            String s = admin.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позицию для изменения: ");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            adminDTO =  AdminServiceImpl.getInstance().getAll().get(k - 1);
            System.out.println(adminDTO.toString());
            System.out.println("Введите логин: ");
            String login = scanner.nextLine();
            if (login.equals(adminDTO.getLogin()) || UserServiceImpl.getInstance().checkLogin(login)) {
                System.out.println("Введите пароль: ");
                String password = scanner.nextLine();
                AdminServiceImpl.getInstance().updateInfo(new AdminDTO(new UserDTO(adminDTO.getId(), RoleDTO.ADMIN,
                        login, password), false));
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
        System.out.println("Адинистраторы:");
        int i = 1;
        for (AdminDTO adminDTO :  AdminServiceImpl.getInstance().getAll()) {
            String s = adminDTO.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            AdminServiceImpl.getInstance().deleteInfo( AdminServiceImpl.getInstance().getAll().get(k - 1).getId());
        }catch (IndexOutOfBoundsException e){
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }
}
