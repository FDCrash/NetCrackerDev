package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.services.servicesimpl.FacultyServiceImpl;
import com.netcracker.denisik.services.servicesimpl.SpecialityServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FacultyControllerImpl implements Controller {
    private FacultyServiceImpl facultyService;
    private Scanner scanner;
    private SpecialityServiceImpl specialityService;

    public FacultyControllerImpl() {
        facultyService = new FacultyServiceImpl();
        scanner = new Scanner(System.in);
        specialityService = new SpecialityServiceImpl();
    }

    @Override
    public void editMenu() {
        int k;
        do {
            System.out.println("CRUD факультетов:");
            System.out.println("1.Список факультетов");
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
        System.out.println("Факультеты:");
        try{
            for (FacultyDTO facultyDTO : facultyService.getAll()) {
                String s = facultyDTO.toString();
                System.out.println(s);
            }
        }catch (NullPointerException | NoSuchElementException e){
            System.out.println("Факультеты отсутствуют");
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
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= n; i++) {
            System.out.print(i + ". ");
            specialities.add(scanner.nextLine());
            specialitiesId.add(specialityService.generateId(50));
        }
        facultyService.addNew(new FacultyDTO(facultyService.generateId(50),
                name, specialities, specialitiesId));
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Факультеты:");
        int z = 1;
        FacultyDTO facultyDTO;
        for (FacultyDTO faculty : facultyService.getAll()) {
            String s = faculty.toString();
            System.out.println(z + ". " + s);
            z++;
        }
        System.out.println("Выберите позицию для изменения");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            facultyDTO = facultyService.getAll().get(k - 1);
            System.out.println("Введите название факультета: ");
            String name = scanner.nextLine();
            facultyService.updateInfo(new FacultyDTO(facultyDTO.getId(), name,
                    facultyDTO.getSpecialities(), facultyDTO.getSpecialitiesId()));
        }catch (IndexOutOfBoundsException e){
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Факультеты:");
        int i = 1;
        for (FacultyDTO faculty : facultyService.getAll()) {
            String s = faculty.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            facultyService.deleteInfo(facultyService.getAll().get(k - 1).getId());
        }catch (IndexOutOfBoundsException e){
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }
}
