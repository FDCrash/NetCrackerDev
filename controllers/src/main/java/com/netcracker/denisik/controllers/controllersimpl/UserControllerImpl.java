//package com.netcracker.denisik.controllers.controllersimpl;
//
//import com.netcracker.denisik.controllers.Controller;
//import com.netcracker.denisik.dto.RoleDTO;
//import com.netcracker.denisik.dto.UserDTO;
//import com.netcracker.denisik.services.servicesimpl.StudentServiceImpl;
//import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Scanner;
//
//import static com.netcracker.denisik.dto.RoleDTO.ADMIN;
//import static com.netcracker.denisik.dto.RoleDTO.EMPLOYEE;
//
//public class UserControllerImpl implements Controller {
//    private Scanner scanner;
//    @Autowired
//    private UserServiceImpl userService;
//    @Autowired
//    private StudentServiceImpl studentService;
//
//    public UserControllerImpl() {
//        scanner = new Scanner(System.in);
//    }
//
//    @Override
//    public void editMenu() {
//        int point;
//        do {
//            System.out.println("CRUD пользователей:");
//            System.out.println("1.Список пользователей");
//            System.out.println("2.Добавить");
//            System.out.println("3.Изменить");
//            System.out.println("4.Удалить");
//            System.out.println("0.Выйти");
//            point = Integer.parseInt(scanner.nextLine());
//            switchChange(point);
//        } while (point < 0 || point > 4);
//    }
//
//    @Override
//    public void getAll() {
//        System.out.println("Пользователи:");
//        for (UserDTO userDTO : userService.getAll()) {
//            String s = userDTO.toString();
//            System.out.println(s);
//        }
//        editMenu();
//    }
//
//    @Override
//    public void add() {
//        System.out.println("Новый пользователь");
//        System.out.println("Введите роль(admin/employee/student):");
//        String role = scanner.nextLine();
//        System.out.println("Введите логин: ");
//        String login = scanner.nextLine();
//        try {
//            if (userService.checkLogin(login)) {
//                System.out.println("Введите пароль: ");
//                String password = scanner.nextLine();
//                UserDTO userDTO=new UserDTO();
//                userDTO.setRoleDTO(RoleDTO.valueOf(role.toUpperCase()));
//                userDTO.setLogin(login);
//                userDTO.setPassword(password);
//                userService.add(userDTO);
//            } else {
//                System.out.println("Логин занят");
//            }
//        }catch (NullPointerException e){
//            System.out.println("При вводе допущена ошибка");
//        }
//        editMenu();
//    }
//
//    @Override
//    public void update() {
//        System.out.println("Пользователи:");
//        int iterator = 1;
//        String password;
//        UserDTO userDTO;
//        for (UserDTO user : userService.getAll()) {
//            String s = user.toString();
//            System.out.println(iterator + ". " + s);
//            iterator++;
//        }
//        System.out.println("Выберите позицию для изменения: ");
//        try {
//            int index = Integer.parseInt(scanner.nextLine());
//            userDTO = userService.getAll().get(index - 1);
//            System.out.println(userDTO.toString());
//            System.out.println("Введите роль (admin/employee):");
//            String role = scanner.nextLine();
//            System.out.println("Введите логин: ");
//            String login = scanner.nextLine();
//            if (login.equals(userDTO.getLogin()) || userService.checkLogin(login)) {
//                switch (RoleDTO.valueOf(role.toUpperCase())) {
//                    case ADMIN:
//                        System.out.println("Введите пароль: ");
//                        password = scanner.nextLine();
//                        if (userDTO.getRoleDTO().equals(ADMIN)) {
//                            userService.update(new UserDTO(userDTO.getId(), ADMIN, login, password));
//                        } else {
//                            if (userDTO.getRoleDTO().equals(RoleDTO.EMPLOYEE)) {
//                                employeeService.delete(userDTO.getId());
//                            }
//                            if (userDTO.getRoleDTO().equals(RoleDTO.STUDENT)) {
//                                studentService.delete(userDTO.getId());
//                            }
//                            adminService.add(new AdminDTO(new UserDTO(userDTO.getId(),
//                                    ADMIN, login, password), false));
//                        }
//                        break;
//                    case EMPLOYEE:
//                        System.out.println("Введите пароль: ");
//                        password = scanner.nextLine();
//                        if (userDTO.getRoleDTO().equals(RoleDTO.EMPLOYEE)) {
//                            userService.update(new UserDTO(userDTO.getId(), EMPLOYEE, login, password));
//                        } else {
//                            if (userDTO.getRoleDTO().equals(ADMIN)) {
//                                adminService.delete(userDTO.getId());
//                            }
//                            if (userDTO.getRoleDTO().equals(RoleDTO.STUDENT)) {
//                                studentService.delete(userDTO.getId());
//                            }
//                            System.out.println("Введите имя: ");
//                            String name = scanner.nextLine();
//                           employeeService.add(new EmployeeDTO(new UserDTO(userDTO.getId(),
//                                    RoleDTO.EMPLOYEE, login, password), name));
//                        }
//                        break;
//                    case STUDENT:
//                        System.out.println("Логины и пароли студентов менять не нужно");
//                        break;
//                    default:
//                        System.out.println("Неверно введена роль!");
//                        break;
//                }
//            } else {
//                System.out.println("Логин занят");
//            }
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Вы ввели неверный номер из списка");
//        }catch (NullPointerException e){
//            System.out.println("При вводе допущена ошибка");
//        }
//        editMenu();
//    }
//
//    @Override
//    public void delete() {
//        System.out.println("Пользователи:");
//        int iterator = 1;
//        for (UserDTO userDTO : userService.getAll()) {
//            String s = userDTO.toString();
//            System.out.println(iterator + ". " + s);
//            iterator++;
//        }
//        System.out.println("Выберите позицию для удаления: ");
//        try {
//            int index = Integer.parseInt(scanner.nextLine());
//            if (userService.getAll().get(index - 1).getRoleDTO().equals(ADMIN)) {
//                System.out.println("Вы хотите удалить админа, перейдите в CRUD админов!");
//            } else {
//                userService.delete(userService.getAll().get(index - 1).getId());
//            }
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Вы ввели неверный номер из списка");
//        }
//        editMenu();
//    }
//}
