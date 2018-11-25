package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.services.servicesimpl.SpecialityServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SpecialityControllerImpl implements Controller {
    private SpecialityServiceImpl specialityService;
    private UserServiceImpl userService;
    private Scanner scanner;

    public SpecialityControllerImpl() {
        specialityService = new SpecialityServiceImpl();
        scanner = new Scanner(System.in);
        userService = new UserServiceImpl();
    }

    @Override
    public void editMenu() {
        int k;
        do {
            System.out.println("CRUD специальностей:");
            System.out.println("1.Список специальностей");
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
        System.out.println("Специальности:");
        try {
            for (SpecialityDTO specialityDTO : specialityService.getAll()) {
                String s = specialityDTO.toString();
                System.out.println(s);
            }
        }catch (NullPointerException | NoSuchElementException e){
            System.out.println("Специальности отсутствуют");
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новая специальность");
        System.out.println("Введите название специальности: ");
        String name = scanner.nextLine();
        System.out.println("Введите название факультета: ");
        String faculty = scanner.nextLine();
        specialityService.addNew(new SpecialityDTO(userService.generateId(50), name, faculty));
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Специальности:");
        int i = 1;
        for (SpecialityDTO specialityDTO : specialityService.getAll()) {
            String s = specialityDTO.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позицию для изменения: ");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            SpecialityDTO specialityDTO = specialityService.getAll().get(k - 1);
            System.out.println("Введите название специальности: ");
            String name = scanner.nextLine();
            System.out.println("Введите название факультета: ");
            String faculty = scanner.nextLine();
            specialityService.updateInfo(new SpecialityDTO(specialityDTO.getId(), name, faculty));
        }catch (IndexOutOfBoundsException e){
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Специальности:");
        int i = 1;
        for (SpecialityDTO specialityDTO : specialityService.getAll()) {
            String s = specialityDTO.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            specialityService.deleteInfo(specialityService.getAll().get(k - 1).getId());
        }catch (IndexOutOfBoundsException e){
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }
}
