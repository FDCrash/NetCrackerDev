import javax.xml.ws.Service;
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
                    authentificationMenu();
                    break;
                case 2:
                    registrationMenu();
                    break;
                default:
                    System.out.println("Выберите позицию из списка");
            }
        }while(k<1 && k>2);
    }

    public void registrationMenu(){
        ServiceSystem serviceSystem=new ServiceSystem();
        Scanner scanner=new Scanner(System.in);
        int idStud;
        String pass,login;
        System.out.println("Введите свой номер студенченского билета:");
        idStud=scanner.nextInt();
        if (serviceSystem.checkId(idStud)) {
            System.out.println("Введите желаемый логин:");
            login = scanner.next();
            System.out.println("Введите желаемый пароль:");
            pass = scanner.next();
            serviceSystem.registerStudent(idStud,login,pass);
            System.out.println("Вы успещно зарегстрированы!");
        }else{
            System.out.println("Такого номера не существует!");
        }
        startMenu();
    }

    public void authentificationMenu(){
        ServiceSystem serviceSystem=new ServiceSystem();
        Scanner scanner=new Scanner(System.in);
        String login,pass;
        System.out.println("Введите логин:");
        login=scanner.next();
        System.out.println("Введите пароль:");
        pass=scanner.next();
        serviceSystem.authentificationUser(login,pass);
    }

    public void displayError(){}

    public void adminMenu(){}

    public void studentMenu(){}

    public void employeeMenu(){}

    public void anonimMenu(){}

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
