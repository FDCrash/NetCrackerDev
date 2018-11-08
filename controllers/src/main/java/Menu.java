import services.servicesimpl.UserService;

import java.util.Scanner;

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
        if (userService.checker(idStud)) {
            System.out.println("Введите желаемый логин:");
            login = scanner.next();
            System.out.println("Введите желаемый пароль:");
            pass = scanner.next();
            userService.registration(idStud,login,pass);
            System.out.println("Регистрация произошла успешно!");
        }else{
            System.out.println("Такого номера не существует!");
        }
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
        userService.authentication(login,pass);
        Enum i = userService.getRoleForMenu();
        if (daomodule.entities.Role.ADMIN.equals(i)) {
            adminMenu();
        } else if (daomodule.entities.Role.STUDENT.equals(i)) {
            studentMenu();
        } else if (daomodule.entities.Role.EMPLOYEE.equals(i)) {
            employeeMenu();
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