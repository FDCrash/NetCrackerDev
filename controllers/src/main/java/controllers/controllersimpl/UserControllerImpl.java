package controllers.controllersimpl;

import controllers.ControllerMD;
import dto.*;
import services.servicesimpl.AdminServiceImpl;
import services.servicesimpl.EmployeeServiceImpl;
import services.servicesimpl.StudentServiceImpl;
import services.servicesimpl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserControllerImpl implements ControllerMD {
    private UserServiceImpl userService;
    private Scanner scanner;
    private AdminServiceImpl adminService;
    private EmployeeServiceImpl employeeService;
    private StudentServiceImpl studentService;

    public UserControllerImpl(){
        userService = new UserServiceImpl();
        scanner= new Scanner(System.in);
        adminService = new AdminServiceImpl();
        employeeService=new EmployeeServiceImpl();
        studentService = new StudentServiceImpl();
    }

    @Override
    public void editMenu() {
        int k;
        do {
            System.out.println("CRUD пользователей:");
            System.out.println("1.Список пользователей");
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
        System.out.println("Пользователи:");
        for(UserDTO userDTO:userService.getAll()){
            String s = userDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый пользователь");
        System.out.println("Введите роль(admin/employee/student):");
        String role=scanner.next();
        System.out.println("Введите логин: ");
        String login=scanner.next();
        System.out.println("Введите пароль: ");
        String password =scanner.next();
        int k=userService.generateId(1000);
        if(role.equals("admin")){
            adminService.addNew(new AdminDTO(k,RoleDTO.ADMIN,login,password,false));
        }
        if(role.equals("employee")){
            employeeService.addNew(new EmployeeDTO(k,RoleDTO.EMPLOYEE,login,password,""));
        }
        if(role.equals("student")){
            studentService.addNew(new StudentDTO(k,RoleDTO.STUDENT,login,password,""
                    ,0,0,"",null));
        }
        userService.addNew(new UserDTO(k,RoleDTO.valueOf(role.toUpperCase()),
                login,password));
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Пользователи:");
        int z=1;
        String login="";
        String password="";
        UserDTO userDTO;
        for(UserDTO user:userService.getAll()){
            String s = user.toString();
            System.out.println(z+". " + s);
            z++;
        }
        System.out.println("Выберите позицию для изменения: ");
        int k=scanner.nextInt();
        userDTO=userService.get(k-1);
        System.out.println(userDTO.toString());
        System.out.println("Введите роль(admin/employee/student):");
        String role=scanner.next();
        if(role.equals("admin")){
            System.out.println("Введите логин: ");
            login=scanner.next();
            System.out.println("Введите пароль: ");
            password =scanner.next();
            adminService.updateInfo(new AdminDTO(userDTO.getId(),RoleDTO.ADMIN,login,password,false));
        }
        if(role.equals("employee")){
            System.out.println("Введите имя: ");
            String name=scanner.next();
            System.out.println("Введите логин: ");
            login=scanner.next();
            System.out.println("Введите пароль: ");
            password =scanner.next();
            employeeService.updateInfo(new EmployeeDTO(userDTO.getId(),RoleDTO.EMPLOYEE,login,password,name));
        }
        if(role.equals("student")){
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
            studentService.updateInfo(new StudentDTO(userDTO.getId(),RoleDTO.STUDENT,
                    "","", name,number,group,speciality,writeBook));
        }
        userService.updateInfo(new UserDTO(userDTO.getId(),RoleDTO.valueOf(role.toUpperCase()),login,password));
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Пользователи:");
        int i=1;
        for(UserDTO userDTO:userService.getAll()){
            String s = userDTO.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        int k=scanner.nextInt();
        if(userService.get(k-1).getRoleDTO().equals(RoleDTO.ADMIN)){
            System.out.println("Вы хотите удалить админа, перейдите в CRUD админов!");
        }else {
            userService.deleteInfo(k - 1);
        }
        editMenu();
    }
}
