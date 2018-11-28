package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.controllers.Menu;
import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.dto.WriteBookDTO;
import com.netcracker.denisik.services.servicesimpl.StudentServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentControllerImpl implements Controller {
    private Scanner scanner;

    public StudentControllerImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void editMenu() {
        int point;
        do {
            System.out.println("CRUD студентов:");
            System.out.println("1.Список студентов");
            System.out.println("2.Добавить");
            System.out.println("3.Изменить");
            System.out.println("4.Удалить");
            System.out.println("0.Выйти");
            point = Integer.parseInt(scanner.nextLine());
            switchChange(point);
        } while (point < 0 || point > 4);
    }

    @Override
    public void getAll() {
        System.out.println("Студенты:");
        for (StudentDTO studentDTO : StudentServiceImpl.getInstance().getAll()) {
            String s = studentDTO.toString();
            System.out.println(s);
        }
        editMenu();
    }

    @Override
    public void add()  {
        System.out.println("Новый студент");
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Введите номер студенченского билета: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите номер группы: ");
        int group = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите специальность: ");
        String speciality = scanner.nextLine();
        System.out.println("Введите колво семестров: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        try {
            StudentServiceImpl.getInstance().
                    add(new StudentDTO(new UserDTO(UserServiceImpl.getInstance().generateId(1000),
                            RoleDTO.STUDENT, "", ""), name, studentId, group, speciality, fillBook(quantity)));
        }catch (NullPointerException e){
            System.out.println("При вводе допущена ошибка");
        }
        editMenu();
    }

    @Override
    public void update() {
        System.out.println("Студенты:");
        int iterator = 1;
        StudentDTO studentDTO;
        for (StudentDTO student : StudentServiceImpl.getInstance().getAll()) {
            String s = student.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для изменения: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            studentDTO = StudentServiceImpl.getInstance().getAll().get(index - 1);
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
            int quantity = Integer.parseInt(scanner.nextLine());
            StudentServiceImpl.getInstance().update(new StudentDTO(new UserDTO(studentDTO.getId(), RoleDTO.STUDENT,
                    studentDTO.getLogin(), studentDTO.getPassword()), name, number, group, speciality, fillBook(quantity)));
            UserServiceImpl.getInstance().update(new UserDTO(studentDTO.getId(), RoleDTO.STUDENT,
                    studentDTO.getLogin(), studentDTO.getPassword()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вы ввели неверный номер из списка");
        }catch (NullPointerException e){
            System.out.println("При вводе допущена ошибка");
        }
        editMenu();
    }

    private List<WriteBookDTO> fillBook(int n) {
        List<Integer> marks = new ArrayList<>();
        List<String> subjects = new ArrayList<>();
        int kol;
        List<WriteBookDTO> writeBook = new ArrayList<>();
        String subj;
        int mark;
        for (int i = 1; i <= n; i++) {
            marks.clear();
            subjects.clear();
            System.out.println("Введите колво предметов за " + i + " семестр: ");
            kol = Integer.parseInt(scanner.nextLine());
            for (int j = 1; j <= kol; j++) {
                System.out.println("Введите название " + j + " предмета :");
                subj = scanner.nextLine();
                System.out.println("Введите оценку по " + subj + " предмету: ");
                mark = Integer.parseInt(scanner.nextLine());
                marks.add(mark);
                subjects.add(subj);
            }
            writeBook.add(new WriteBookDTO(i, subjects, marks));
        }
        return writeBook;
    }

    @Override
    public void delete() {
        System.out.println("Студенты:");
        int iterator = 1;
        for (StudentDTO studentDTO : StudentServiceImpl.getInstance().getAll()) {
            String s = studentDTO.toString();
            System.out.println(iterator + ". " + s);
            iterator++;
        }
        System.out.println("Выберите позицию для удаления: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            StudentServiceImpl.getInstance().delete(StudentServiceImpl.getInstance().getAll().get(index - 1).getId());
        } catch (IndexOutOfBoundsException e) {
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

    public void getAllByGroup(){
        int index = StudentServiceImpl.getInstance().getByLogin(Menu.getInstance().getLogin()).getGroupId();
        System.out.println("Студенты группы: " + index);
        for (StudentDTO studentDTO : StudentServiceImpl.getInstance().getAllByGroup(index)) {
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
