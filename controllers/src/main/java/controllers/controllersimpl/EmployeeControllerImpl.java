package controllers.controllersimpl;

import controllers.ControllerMD;
import dto.EmployeeDTO;
import dto.RoleDTO;
import dto.UserDTO;
import services.servicesimpl.EmployeeServiceImpl;
import services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

public class EmployeeControllerImpl implements ControllerMD {
    private EmployeeServiceImpl employeeService;
    private UserServiceImpl userService;
    private Scanner scanner;

    public EmployeeControllerImpl(){
        employeeService=new EmployeeServiceImpl();
        scanner = new Scanner(System.in);
        userService = new UserServiceImpl();
    }

    @Override
    public void editMenu() {
        int k;
        do {
            System.out.println("CRUD сотрудников:");
            System.out.println("1.Список сотрудников");
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
        System.out.println("Сотрудники:");
        for(EmployeeDTO adminDTO:employeeService.getAll()){
            String s = adminDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый сотрудник");
        System.out.println("Введите имя: ");
        String name=scanner.next();
        System.out.println("Введите логин: ");
        String login=scanner.next();
        System.out.println("Введите пароль: ");
        String password =scanner.next();
        employeeService.addNew(new EmployeeDTO(userService.generateId(1000),RoleDTO.EMPLOYEE,
                login,password,name));
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Сотрудники:");
        int i=1;
        EmployeeDTO employeeDTO;
        for(EmployeeDTO employee:employeeService.getAll()){
            String s = employee.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позицию для изменения: ");
        int k=scanner.nextInt();
        employeeDTO=employeeService.get(k-1);
        System.out.println(employeeDTO);
        System.out.println("Введите имя: ");
        String name=scanner.next();
        System.out.println("Введите логин: ");
        String login=scanner.next();
        System.out.println("Введите пароль: ");
        String password =scanner.next();
        employeeService.updateInfo(new EmployeeDTO(employeeDTO.getId(),RoleDTO.EMPLOYEE,login,password,name));
        userService.updateInfo(new UserDTO(employeeDTO.getId(),RoleDTO.EMPLOYEE,login,password));
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Сотрудники:");
        int i=1;
        for(EmployeeDTO adminDTO:employeeService.getAll()){
            String s = adminDTO.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позиция для удаления: ");
        int k=scanner.nextInt();
        employeeService.deleteInfo(k-1);
        editMenu();
    }
}

