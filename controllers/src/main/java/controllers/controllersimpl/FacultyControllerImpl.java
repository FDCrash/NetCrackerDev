package controllers.controllersimpl;

import controllers.ControllerMD;
import dto.FacultyDTO;
import services.servicesimpl.FacultyServiceImpl;
import services.servicesimpl.SpecialityServiceImpl;
import services.servicesimpl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacultyControllerImpl implements ControllerMD {
    private FacultyServiceImpl facultyService;
    private Scanner scanner;
    private SpecialityServiceImpl specialityService;

    public FacultyControllerImpl(){
        facultyService=new FacultyServiceImpl();
        scanner=new Scanner(System.in);
        specialityService= new SpecialityServiceImpl();
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
        System.out.println("Факультеты:");
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
        List<Integer> specialitiesId=new ArrayList<>();
        int n=Integer.parseInt(scanner.next());
        for(int i=1;i<=n;i++){
            System.out.print(i + ". ");
            specialities.add(scanner.next());
            specialitiesId.add(specialityService.generateId(50));
        }
        facultyService.addNew(new FacultyDTO(facultyService.generateId(50),
                name,specialities,specialitiesId));
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
        int k=Integer.parseInt(scanner.next());
        facultyDTO=facultyService.get(k-1);
        System.out.println("Введите название факультета: ");
        String name=scanner.next();
        facultyService.updateInfo(new FacultyDTO(facultyDTO.getId(),name,
                facultyDTO.getSpecialities(),facultyDTO.getSpecialitiesId()));
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
        int k=Integer.parseInt(scanner.next());
        facultyService.deleteInfo(k-1);
        editMenu();
    }
}
