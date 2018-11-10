import daomodule.entities.Role;
import daomodule.storage.StudentList;
import daomodule.storage.UserList;
import services.servicesimpl.UserService;

import java.util.Scanner;

import static daomodule.entities.Role.*;

public class Menu {

    public void startMenu() {
        int k;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Входное меню");
        do{
            System.out.println("1.Войти");
            System.out.println("2.Зарегистрироваться");
            k=scanner.nextInt();
            switch (k) {
                case 1:
                    authenticationMenu();
                    break;
                case 2:
                    registrationMenu();
                    break;
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<1 || k>2);
    }

    public void registrationMenu(){
        UserService userService=new UserService();
        Scanner scanner=new Scanner(System.in);
        int idStud;
        String pass,login;
        System.out.println("Введите свой номер студенченского билета:");
        idStud=scanner.nextInt();
        System.out.println("Введите желаемый логин:");
        login = scanner.next();
        System.out.println("Введите желаемый пароль:");
        pass = scanner.next();
        System.out.println(userService.registration(idStud,login,pass));
        startMenu();
    }

    public void authenticationMenu(){
        UserService userService=new UserService();

        Scanner scanner=new Scanner(System.in);
        String login,pass;
        System.out.println("Введите логин:");
        login=scanner.next();
        System.out.println("Введите пароль:");
        pass=scanner.next();
        switch ((Role)userService.authentication(login,pass)){
            case ADMIN: adminMenu();    userService.changeStatusAdmin(login);
            break;
            case EMPLOYEE: employeeMenu();
            break;
            case STUDENT: studentMenu();
            break;
            default: System.out.println("Неправильный логин или пароль");
        }

    }

    public void displayError(){}

    public void adminMenu(){
        System.out.println("Меню админа:");
    }

    public void studentMenu(){
        System.out.println("Меню студента:");
    }

    public void employeeMenu(){
        System.out.println("Меню сотрудника:");
    }

    public void editAdmin(){}

    public void editEmployee(){}

    public void editStudent(){}

    public void editAnonim(){}

    public void editFaculty(){}

    public void editSpeciality(){}

    public void editMarksOfModule(){}

    public void editMarksOfControl(){}

    public void changeNextCourse(){}

    public void searchInfo(){}

    public void checkWriteBook(){}

    public void checkMarksOfModule(){}

    public void checkMarksOfControl(){}
}
