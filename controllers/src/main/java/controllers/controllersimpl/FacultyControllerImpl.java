package controllers.controllersimpl;

import controllers.ControllerMD;
import dto.FacultyDTO;
import services.servicesimpl.FacultyServiceImpl;
import services.servicesimpl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacultyControllerImpl implements ControllerMD {
    private FacultyServiceImpl facultyService;
    private Scanner scanner;
    private UserServiceImpl userService;

    public FacultyControllerImpl(){
        facultyService=new FacultyServiceImpl();
        scanner=new Scanner(System.in);
        userService= new UserServiceImpl();
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
            k=scanner.nextInt();
            switchChange(k);
        }while(k<0 || k>4);
    }

    @Override
    public void getAll() {
        System.out.println("Специальности:");
        for(FacultyDTO facultyDTO:facultyService.getAll()){
            String s =facultyDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый факультет");
        System.out.println("Введите название факультета: ");
        String name=scanner.next();
        System.out.println("Введите количество специальностей: ");
        List<String> specialities=new ArrayList<>();
        int n=scanner.nextInt();
        for(int i=1;i<=n;i++){
            System.out.print(i + ". ");
            specialities.add(scanner.next());
        }
        facultyService.addNew(new FacultyDTO(userService.generateId(50),name,specialities));
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Факультеты:");
        int z=1;
        FacultyDTO facultyDTO;
        for(FacultyDTO faculty: facultyService.getAll()){
            String s =faculty.toString();
            System.out.println(z+". "+s);
            z++;
        }
        System.out.println("Выберите позицию для изменения");
        int k=scanner.nextInt();
        facultyDTO=facultyService.get(k-1);
        System.out.println("Введите название факультета: ");
        String name=scanner.next();
        System.out.println("Введите количество специальностей: ");
        List<String> specialities=new ArrayList<>();
        int n=scanner.nextInt();
        for(int i=1;i<=n;i++){
            System.out.print(i + ". ");
            specialities.add(scanner.next());
        }
        facultyService.updateInfo(new FacultyDTO(facultyDTO.getId(),name,specialities));
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Факультеты:");
        int i=1;
        for(FacultyDTO faculty: facultyService.getAll()){
            String s =faculty.toString();
            System.out.println(i+". "+s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        int k=scanner.nextInt();
        facultyService.deleteInfo(k-1);
        editMenu();
    }
}
