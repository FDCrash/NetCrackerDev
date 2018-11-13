package controllers.controllersimpl;

import controllers.ControllerMD;
import dto.StudentDTO;
import services.servicesimpl.StudentService;
import services.servicesimpl.UserService;

import java.util.Scanner;

public class StudentControllerImpl implements ControllerMD {
    private StudentService studentService;
    private UserService userService;
    private Scanner scanner;

    public StudentControllerImpl(){
        scanner = new Scanner(System.in);
        studentService = new StudentService();
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

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    public void showWriteBook(){

    }

    public void getAllByGroup(){

    }

    public void getAllBySpeciality(){

    }
}
