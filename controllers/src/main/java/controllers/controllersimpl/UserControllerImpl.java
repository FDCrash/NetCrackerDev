package controllers.controllersimpl;

import controllers.ControllerMD;
import dto.RoleDTO;
import dto.UserDTO;
import services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

public class UserControllerImpl implements ControllerMD {
    private UserServiceImpl userService;
    private Scanner scanner;

    public UserControllerImpl(){
        userService = new UserServiceImpl();
        scanner= new Scanner(System.in);
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
        System.out.println("Введите логин: ");
        String login=scanner.next();
        System.out.println("Введите пароль: ");
        String password =scanner.next();
        System.out.println("Введите роль(admin/employee/student):");
        String role=scanner.next();
        userService.addNew(new UserDTO(userService.generateId(1000),RoleDTO.valueOf(role.toUpperCase()),
                login,password));
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Пользователи:");
        int i=1;
        UserDTO userDTO;
        for(UserDTO user:userService.getAll()){
            String s = user.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позицию для изменения: ");
        int k=scanner.nextInt();
        userDTO=userService.get(k-1);
        System.out.println(userDTO.toString());
        System.out.println("Введите логин: ");
        String login=scanner.next();
        System.out.println("Введите пароль: ");
        String password =scanner.next();
        System.out.println("Введите роль(admin/employee/student):");
        String role=scanner.next();
        userService.updateInfo(new UserDTO(userDTO.getId(),RoleDTO.valueOf(role.toUpperCase()),login,password));
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Пользователи:");
        int i=1;
        for(UserDTO adminDTO:userService.getAll()){
            String s = adminDTO.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        int k=scanner.nextInt();
        userService.deleteInfo(k-1);
        editMenu();
    }
}
