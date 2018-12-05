package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.services.servicesimpl.FacultyServiceImpl;
import com.netcracker.denisik.services.servicesimpl.SpecialityServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacultyControllerImpl implements Controller {
    private Scanner scanner;

    public FacultyControllerImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void editMenu() {
        int point;
        do {
            System.out.println("CRUD факультетов:");
            System.out.println("1.Список факультетов");
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
        System.out.println("Факультеты:");
        for (FacultyDTO facultyDTO : FacultyServiceImpl.getInstance().getAll()) {
            String s = facultyDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый факультет");
        System.out.println("Введите название факультета: ");
        String name = scanner.nextLine();
        System.out.println("Введите количество специальностей: ");
        List<String> specialities = new ArrayList<>();
        List<Integer> specialitiesId = new ArrayList<>();
        int quantity = Integer.parseInt(scanner.nextLine());
        try {
            for (int i = 1; i <= quantity; i++) {
                System.out.print(i + ". ");
                specialities.add(scanner.nextLine());
                specialitiesId.add(SpecialityServiceImpl.getInstance().generateId(50));
            }
            FacultyDTO facultyDTO=new FacultyDTO();
            facultyDTO.setId(FacultyServiceImpl.getInstance().generateId(50));
            facultyDTO.setName(name);
            facultyDTO.setSpecialitiesId(specialitiesId);
            facultyDTO.setSpecialities(specialities);
            FacultyServiceImpl.getInstance().add(facultyDTO);
        }catch (NullPointerException e){
            System.out.println("При введении допущена ошибка");
        }
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Факультеты:");
        int iterator = 1;
        FacultyDTO facultyDTO;
        for (FacultyDTO faculty : FacultyServiceImpl.getInstance().getAll()) {
            String s = faculty.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для изменения");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            facultyDTO = FacultyServiceImpl.getInstance().getAll().get(index - 1);
            if(!facultyDTO.getName().equals("Абитура")) {
                System.out.println("Введите название факультета: ");
                String name = scanner.nextLine();
                facultyDTO.setName(name);
                FacultyServiceImpl.getInstance().update(facultyDTO);
            }else{
                System.out.println("Этот факультет нельзя изменять");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Факультеты:");
        int iterator = 1;
        for (FacultyDTO faculty : FacultyServiceImpl.getInstance().getAll()) {
            String s = faculty.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для удаления: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (!FacultyServiceImpl.getInstance().getAll().get(index - 1).getName().equals("Абитура")) {
                FacultyServiceImpl.getInstance().delete(FacultyServiceImpl.getInstance().getAll().get(index - 1).getId());
            }else{
                System.out.println("Этот факультет нельзя удалять");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }
}
