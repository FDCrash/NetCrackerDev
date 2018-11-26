package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.controllers.Menu;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.dto.WriteBookDTO;
import com.netcracker.denisik.services.servicesimpl.StudentServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentControllerImpl implements Controller {
    private Scanner scanner;

    public StudentControllerImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void editMenu() {
        int k;
        do {
            System.out.println("CRUD студентов:");
            System.out.println("1.Список студентов");
            System.out.println("2.Добавить");
            System.out.println("3.Изменить");
            System.out.println("4.Удалить");
            System.out.println("0.Выйти");
            k = Integer.parseInt(scanner.nextLine());
            switchChange(k);
        } while (k < 0 || k > 4);
    }

    @Override
    public void getAll() {
        System.out.println("Студенты:");
        try{
            for (StudentDTO studentDTO : StudentServiceImpl.getInstance().getAll()) {
                String s = studentDTO.toString();
                System.out.println(s);
            }
        }catch (NullPointerException | NoSuchElementException e){
            System.out.println("Студенты отсутствуют");
        }
        editMenu();
    }

    @Override
    public void add() {
        System.out.println("Новый студент");
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Введите номер студенченского билета: ");
        int number = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите номер группы: ");
        int group = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите специальность: ");
        String speciality = scanner.nextLine();
        System.out.println("Введите колво семестров: ");
        int n = Integer.parseInt(scanner.nextLine());
        StudentServiceImpl.getInstance().
                addNew(new StudentDTO(new UserDTO(UserServiceImpl.getInstance().generateId(1000),
                        RoleDTO.STUDENT, "", ""), name, number, group, speciality, fillBook(n)));
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Студенты:");
        int z = 1;
        StudentDTO studentDTO;
        for (StudentDTO student : StudentServiceImpl.getInstance().getAll()) {
            String s = student.toString();
            System.out.println(z + ". " + s);
            z++;
        }
        System.out.println("Выберите позицию для изменения: ");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            studentDTO = StudentServiceImpl.getInstance().getAll().get(k - 1);
            System.out.println(studentDTO);
            System.out.println("Введите имя: ");
            String name = scanner.nextLine();
            System.out.println("Введите номер студенченского билета: ");
            int number = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите номер группы: ");
            int group = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите специальность: ");
            String speciality = scanner.nextLine();
            System.out.println("Введите колво семестров: ");
            int n = Integer.parseInt(scanner.nextLine());
            StudentServiceImpl.getInstance().updateInfo(new StudentDTO(new UserDTO(studentDTO.getId(), RoleDTO.STUDENT,
                    studentDTO.getLogin(), studentDTO.getPassword()), name, number, group, speciality, fillBook(n)));
            UserServiceImpl.getInstance().updateInfo(new UserDTO(studentDTO.getId(), RoleDTO.STUDENT,
                    studentDTO.getLogin(), studentDTO.getPassword()));
        }catch (IndexOutOfBoundsException e){
                System.out.println("Вы ввели неверный номер из списка");
            }
        editMenu();
    }

    private List<WriteBookDTO> fillBook(int n) {
        List<Integer> marks=new ArrayList<>();
        List<String> subjects=new ArrayList<>();
        int kol;
        List<WriteBookDTO> writeBook = new ArrayList<>();
        String subj;
        int mark;
        for (int i = 1; i <= n; i++) {
            marks.clear();
            subjects.clear();
            System.out.println("Введите колво предметов за " + i + " семестр: ");
            kol=Integer.parseInt(scanner.nextLine());
            for(int j=1; j <=kol;j++){
                System.out.println("Введите название " + j + " предмета :");
                subj=scanner.nextLine();
                System.out.println("Введите оценку по " + subj + " предмету: ");
                mark=Integer.parseInt(scanner.nextLine());
                marks.add(mark);
                subjects.add(subj);
            }
            writeBook.add(new WriteBookDTO(i,subjects,marks));
        }
        return writeBook;
    }

    @Override
    public void delete() {
        System.out.println("Студенты:");
        int i = 1;
        for (StudentDTO studentDTO : StudentServiceImpl.getInstance().getAll()) {
            String s = studentDTO.toString();
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("Выберите позицию для удаления: ");
        try {
            int k = Integer.parseInt(scanner.nextLine());
            StudentServiceImpl.getInstance().deleteInfo(StudentServiceImpl.getInstance().getAll().get(k - 1).getId());
        }catch (IndexOutOfBoundsException e){
            System.out.println("Вы ввели неверный номер из списка");
        }
        editMenu();
    }

    public void showWriteBook() {
        StudentDTO studentDTO = StudentServiceImpl.getInstance().getByLogin(Menu.getInstance().getLogin());
        System.out.println("Зачетка студента " + studentDTO.getName() + ":");
        for (WriteBookDTO writeBook : studentDTO.getWriteBook()) {
            System.out.println(writeBook.toString());
        }
        System.out.println();
        Menu.getInstance().studentMenu();
    }

    public void getAllByGroup() {
        int k = StudentServiceImpl.getInstance().getByLogin(Menu.getInstance().getLogin()).getGroupId();
        System.out.println("Студенты группы: " + k);
        for (StudentDTO studentDTO : StudentServiceImpl.getInstance().getAllByGroup(k)) {
            System.out.println("Студент: " + studentDTO.getName());
        }
        System.out.println("------------------------------");
        Menu.getInstance().studentMenu();
    }

    public void getAllBySpeciality() {
        String speciality = StudentServiceImpl.getInstance().getByLogin(Menu.getInstance().getLogin()).getSpeciality();
        System.out.println("Студенты специальности: " + speciality);
        for (StudentDTO studentDTO : StudentServiceImpl.getInstance().getAllBySpeciality(speciality)) {
            System.out.println("Студент: " + studentDTO.getName());
            System.out.println("Группа: " + studentDTO.getGroupId() + "\n");
        }
        System.out.println("------------------------------");
        Menu.getInstance().studentMenu();
    }
}
