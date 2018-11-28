package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.services.servicesimpl.SpecialityServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class SpecialityControllerImpl implements Controller {
    private Scanner scanner;

    public SpecialityControllerImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void editMenu() {
        int point;
        do {
            System.out.println("CRUD специальностей:");
            System.out.println("1.Список специальностей");
            System.out.println("2.Добавить");
            System.out.println("3.Изменить");
            System.out.println("4.Удалить");
            System.out.println("0.Выйти");
            point = Integer.parseInt(scanner.nextLine());
            switchChange(point);
        } while (point < 0 || point > 4);
    }

    @Override
    public void getAll(){
        System.out.println("Специальности:");
        for (SpecialityDTO specialityDTO : SpecialityServiceImpl.getInstance().getAll()) {
            String s = specialityDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add()  {
        System.out.println("Новая специальность");
        System.out.println("Введите название специальности: ");
        String name = scanner.nextLine();
        System.out.println("Введите название факультета: ");
        String faculty = scanner.nextLine();
        try {
            SpecialityServiceImpl.getInstance().
                    add(new SpecialityDTO(SpecialityServiceImpl.getInstance().generateId(50), name, faculty));
        }catch (NullPointerException e){
            System.out.println("При вводе допущена ошибка");
        }
        editMenu();
    }

    @Override
    public void update()  {
        System.out.println("Специальности:");
        int iterator = 1;
        for (SpecialityDTO specialityDTO : SpecialityServiceImpl.getInstance().getAll()) {
            String s = specialityDTO.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для изменения: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            SpecialityDTO specialityDTO = SpecialityServiceImpl.getInstance().getAll().get(index - 1);
            if (!specialityDTO.getName().equals("Переводится")) {
                System.out.println("Введите название специальности: ");
                String name = scanner.nextLine();
                System.out.println("Введите название факультета: ");
                String faculty = scanner.nextLine();
                SpecialityServiceImpl.getInstance().
                        update(new SpecialityDTO(specialityDTO.getId(), name, faculty));
            } else {
                System.out.println("Эту специальность нельзя изменять");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы ввели неверный номер из списка");
        }catch (NullPointerException e){
            System.out.println("При вводе допущена ошибка");
        }
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Специальности:");
        int iterator = 1;
        for (SpecialityDTO specialityDTO : SpecialityServiceImpl.getInstance().getAll()) {
            String s = specialityDTO.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для удаления: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (!SpecialityServiceImpl.getInstance().getAll().get(index - 1).getName().equals("Переводится")) {
                SpecialityServiceImpl.getInstance().
                        delete(SpecialityServiceImpl.getInstance().getAll().get(index - 1).getId());
            } else {
                System.out.println("Эту специальность нельзя удалять");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }
}
