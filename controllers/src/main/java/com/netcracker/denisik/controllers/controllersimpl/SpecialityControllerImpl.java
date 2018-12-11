package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.services.servicesimpl.SpecialityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Scanner;

public class SpecialityControllerImpl implements Controller {
    private Scanner scanner;
    @Autowired
    private SpecialityServiceImpl specialityService;


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
        for (SpecialityDTO specialityDTO : specialityService.getAll()) {
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
        FacultyDTO faculty=new FacultyDTO();
        faculty.setName(scanner.nextLine());
        try {
            SpecialityDTO specialityDTO=new SpecialityDTO();
            specialityDTO.setName(name);
            specialityDTO.setFaculty(faculty);
            faculty.setSpeciality(specialityDTO);
            specialityService.add(specialityDTO);
        }catch (NullPointerException e){
            System.out.println("При вводе допущена ошибка");
        }
        editMenu();
    }

    @Override
    public void update()  {
        System.out.println("Специальности:");
        int iterator = 1;
        for (SpecialityDTO specialityDTO : specialityService.getAll()) {
            String s = specialityDTO.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для изменения: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            SpecialityDTO specialityDTO = specialityService.getAll().get(index - 1);
            if (!specialityDTO.getName().equals("Переводится")) {
                System.out.println("Введите название специальности: ");
                String name = scanner.nextLine();
                System.out.println("Введите название факультета: ");
                FacultyDTO faculty = new FacultyDTO();
                faculty.setName(scanner.nextLine());
                specialityDTO.setFaculty(faculty);
                specialityDTO.setName(name);
                faculty.setSpeciality(specialityDTO);
               specialityService.update(specialityDTO);
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
        for (SpecialityDTO specialityDTO : specialityService.getAll()) {
            String s = specialityDTO.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для удаления: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (!specialityService.getAll().get(index - 1).getName().equals("Переводится")) {
                specialityService.
                        delete(specialityService.getAll().get(index - 1).getId());
            } else {
                System.out.println("Эту специальность нельзя удалять");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }
}
