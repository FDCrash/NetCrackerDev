package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.services.servicesimpl.SpecialityServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

public class SpecialityControllerImpl implements Controller {
    private SpecialityServiceImpl specialityService;
    private UserServiceImpl userService;
    private Scanner scanner;

    public  SpecialityControllerImpl(){
        specialityService=new SpecialityServiceImpl();
        scanner=new Scanner(System.in);
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
            k=scanner.nextInt();
            switchChange(k);
        }while(k<0 || k>4);
    }

    @Override
    public void getAll() {
        System.out.println("Специальности:");
        for(SpecialityDTO specialityDTO:specialityService.getAll()){
            String s =specialityDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новая специальность");
        System.out.println("Введите название специальности: ");
        String name=scanner.next();
        System.out.println("Введите название факультета: ");
        String faculty =scanner.next();
        specialityService.addNew(new SpecialityDTO(userService.generateId(50),name,faculty));
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Специальности:");
        int i=1;
        for(SpecialityDTO specialityDTO:specialityService.getAll()){
            String s =specialityDTO.toString();
            System.out.println(i+". "+s);
            i++;
        }
        System.out.println("Выберите позицию для изменения: ");
        int k=Integer.parseInt(scanner.next());
        SpecialityDTO specialityDTO=specialityService.get(k-1);
        System.out.println("Введите название специальности: ");
        String name=scanner.next();
        System.out.println("Введите название факультета: ");
        String faculty =scanner.next();
        specialityService.updateInfo(new SpecialityDTO(specialityDTO.getId(),name,faculty));
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Специальности:");
        int i=1;
        for(SpecialityDTO specialityDTO:specialityService.getAll()){
            String s = specialityDTO.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        int k=Integer.parseInt(scanner.next());
        specialityService.deleteInfo(k-1);
        editMenu();
    }
}
