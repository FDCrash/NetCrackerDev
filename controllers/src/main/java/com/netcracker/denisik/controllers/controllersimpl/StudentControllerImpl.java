package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.controllers.Menu;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.servicesimpl.StudentServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentControllerImpl implements Controller {
    private StudentServiceImpl studentService;
    private UserServiceImpl userService;
    private Scanner scanner;

    public StudentControllerImpl(){
        scanner = new Scanner(System.in);
        userService=new UserServiceImpl();
        studentService = new StudentServiceImpl();
    }

    @Override
    public void editMenu(){
        int k;
        do {
            System.out.println("CRUD студентов:");
            System.out.println("1.Список студентов");
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
        System.out.println("Студенты:");
        for(StudentDTO studentDTO:studentService.getAll()){
            String s = studentDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый студент");
        System.out.println("Введите имя: ");
        String name=scanner.next();
        System.out.println("Введите номер студенченского билета: ");
        int number=Integer.parseInt(scanner.next());
        System.out.println("Введите номер группы: ");
        int group=Integer.parseInt(scanner.next());
        System.out.println("Введите специальность: ");
        String speciality =scanner.next();
        System.out.println("Введите колво оценок: ");
        List<Integer> writeBook=new ArrayList<>();
        int n=Integer.parseInt(scanner.next());
        for(int i=1;i<=n;i++){
            System.out.print(i + ". ");
            writeBook.add(Integer.parseInt(scanner.next()));
        }
        studentService.addNew(new StudentDTO(new UserDTO(userService.generateId(1000),RoleDTO.STUDENT,
                "",""), name,number,group,speciality,writeBook));
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Студенты:");
        int z=1;
        StudentDTO studentDTO;
        for(StudentDTO student:studentService.getAll()){
            String s = student.toString();
            System.out.println(z+". "+s);
            z++;
        }
        System.out.println("Выберите позицию для изменения: ");
        int k=Integer.parseInt(scanner.next());
        studentDTO=studentService.getAll().get(k-1);
        System.out.println(studentDTO);
        System.out.println("Введите имя: ");
        String name=scanner.next();
        System.out.println("Введите номер студенченского билета: ");
        int number=Integer.parseInt(scanner.next());
        System.out.println("Введите номер группы: ");
        int group=Integer.parseInt(scanner.next());
        System.out.println("Введите специальность: ");
        String speciality =scanner.next();
        System.out.println("Введите колво оценок: ");
        List<Integer> writeBook=new ArrayList<>();
        int n=Integer.parseInt(scanner.next());
        for(int i=1;i<=n;i++){
            System.out.print(i + ". ");
            writeBook.add(Integer.parseInt(scanner.next()));
        }
        studentService.updateInfo(new StudentDTO(new UserDTO(studentDTO.getId(),RoleDTO.STUDENT,
                studentDTO.getLogin(),studentDTO.getPassword()),name,number,group,speciality,writeBook));
        userService.updateInfo(new UserDTO(studentDTO.getId(),RoleDTO.STUDENT,
                studentDTO.getLogin(),studentDTO.getPassword()));
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Студенты:");
        int i=1;
        for(StudentDTO studentDTO:studentService.getAll()){
            String s = studentDTO.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        int k=Integer.parseInt(scanner.next());
        studentService.deleteInfo(studentService.getAll().get(k-1).getId());
        editMenu();
    }

    public void showWriteBook(){
        StudentDTO studentDTO=studentService.getByLogin(Menu.getInstance().getLogin());
        System.out.println("Зачетка студента " + studentDTO.getName()+ ":");
        for(Integer i:studentDTO.getWriteBook()){
            System.out.print(i + " ");
        }
        System.out.println();
        Menu.getInstance().studentMenu();
    }

    public void getAllByGroup(){
        int k=studentService.getByLogin(Menu.getInstance().getLogin()).getGroupId();
        System.out.println("Студенты группы: " + k);
        for(StudentDTO studentDTO:studentService.getAllByGroup(k)){
            System.out.println("Студент: " + studentDTO.getName());
        }
        System.out.println("------------------------------");
        Menu.getInstance().studentMenu();
    }

    public void getAllBySpeciality(){
        String speciality=studentService.getByLogin(Menu.getInstance().getLogin()).getSpeciality();
        System.out.println("Студенты специальности: " + speciality);
        for(StudentDTO studentDTO: studentService.getAllBySpeciality(speciality)){
            System.out.println("Студент: " + studentDTO.getName());
            System.out.println("Группа: " + studentDTO.getGroupId() + "\n");
        }
        System.out.println("------------------------------");
        Menu.getInstance().studentMenu();
    }
}
