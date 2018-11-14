package controllers.controllersimpl;

import controllers.ControllerMD;
import dto.RoleDTO;
import dto.StudentDTO;
import services.servicesimpl.StudentServiceImpl;
import services.servicesimpl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentControllerImpl implements ControllerMD {
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
            if(k==0){

            }
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
        int number=scanner.nextInt();
        System.out.println("Введите номер группы: ");
        int group=scanner.nextInt();
        System.out.println("Введите специальность: ");
        String speciality =scanner.next();
        System.out.println("Введите колво оценок: ");
        List<Integer> writeBook=new ArrayList<>();
        int n=scanner.nextInt();
        for(int i=1;i<=n;i++){
            System.out.print(i + ". ");
            writeBook.add(scanner.nextInt());
        }
        studentService.addNew(new StudentDTO(userService.generateId(1000),RoleDTO.STUDENT,
                "","", name,number,group,speciality,writeBook));
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
        int k=scanner.nextInt();
        studentDTO=studentService.get(k-1);
        System.out.println(studentDTO);
        System.out.println("Введите имя: ");
        String name=scanner.next();
        System.out.println("Введите номер студенченского билета: ");
        int number=scanner.nextInt();
        System.out.println("Введите номер группы: ");
        int group=scanner.nextInt();
        System.out.println("Введите специальность: ");
        String speciality =scanner.next();
        System.out.println("Введите колво оценок: ");
        List<Integer> writeBook=new ArrayList<>();
        int n=scanner.nextInt();
        for(int i=1;i<=n;i++){
            System.out.print(i + ". ");
            writeBook.add(scanner.nextInt());
        }
        studentService.updateInfo(new StudentDTO(studentDTO.getId(),RoleDTO.STUDENT,
                studentDTO.getLogin(),studentDTO.getPassword(),name,number,group,speciality,writeBook));
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
        int k=scanner.nextInt();
        studentService.deleteInfo(k-1);
        editMenu();
    }

    public void showWriteBook(){

    }

    public void getAllByGroup(){

    }

    public void getAllBySpeciality(){

    }
}
