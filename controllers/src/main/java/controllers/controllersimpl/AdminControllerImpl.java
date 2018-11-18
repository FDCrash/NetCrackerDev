package controllers.controllersimpl;

import controllers.ControllerMD;
import controllers.Menu;
import dto.AdminDTO;
import dto.RoleDTO;
import dto.UserDTO;
import services.servicesimpl.AdminServiceImpl;
import services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

public class AdminControllerImpl implements ControllerMD {
    private AdminServiceImpl adminService;
    private Scanner scanner;
    private UserServiceImpl userService;


    public AdminControllerImpl(){
        scanner = new Scanner(System.in);
        adminService = new AdminServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    public void editMenu(){
        int k;
        do {
            System.out.println("CRUD админов:");
            System.out.println("1.Список админов");
            System.out.println("2.Добавить");
            System.out.println("3.Изменить");
            System.out.println("4.Удалить");
            System.out.println("0.Выйти");
            k=Integer.parseInt(scanner.next());
            switchChange(k);
        }while(k<0 || k>4);
    }

    @Override
    public void getAll(){
        System.out.println("Адинистраторы:");
        for(AdminDTO adminDTO:adminService.getAll()){
            String s = adminDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add(){
        System.out.println("Новый администратор");
        System.out.println("Введите логин: ");
        String login=scanner.next();
        if(userService.checkLogin(login)) {
            System.out.println("Введите пароль: ");
            String password = scanner.next();
            adminService.addNew(new AdminDTO(userService.generateId(1000), RoleDTO.ADMIN,
                    login, password, false));
        }else{
            System.out.println("Логин занят");
        }
        editMenu();
    }

    @Override
    public void update(){
        System.out.println("Адинистраторы:");
        int i=1;
        AdminDTO adminDTO;
        for(AdminDTO admin:adminService.getAll()){
            String s = admin.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позицию для изменения: ");
        int k=Integer.parseInt(scanner.next());
        adminDTO=adminService.get(k-1);
        System.out.println(adminDTO.toString());
        System.out.println("Введите логин: ");
        String login=scanner.next();
        if(login.equals(adminDTO.getLogin()) || userService.checkLogin(login)) {
            System.out.println("Введите пароль: ");
            String password = scanner.next();
            adminService.updateInfo(new AdminDTO(adminDTO.getId(), RoleDTO.ADMIN, login, password, false));
            userService.updateInfo(new UserDTO(adminDTO.getId(), RoleDTO.ADMIN, login, password));
        }else{
            System.out.println("Новый логин занят");
        }
        editMenu();
    }

    @Override
    public void delete() {
        System.out.println("Адинистраторы:");
        int i=1;
        for(AdminDTO adminDTO:adminService.getAll()){
            String s = adminDTO.toString();
            System.out.println(i+". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        int k=Integer.parseInt(scanner.next());
        adminService.deleteInfo(k-1);
        editMenu();
    }
}
