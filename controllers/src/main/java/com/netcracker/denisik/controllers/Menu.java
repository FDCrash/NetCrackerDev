package com.netcracker.denisik.controllers;

import com.netcracker.denisik.controllers.controllersimpl.*;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.services.servicesimpl.AdminServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

public class Menu {
    private Role role;
    private UserServiceImpl userService;
    private Scanner scanner;
    private StudentControllerImpl studentController;
    private EmployeeControllerImpl employeeController;
    private AdminControllerImpl adminController;
    private SpecialityControllerImpl specialityController;
    private FacultyControllerImpl facultyController;
    private AdminServiceImpl adminService;
    private static Menu instance;

    public static Menu getInstance(){
        return instance;
    }

    private Menu(){
    }

    public void startMenu() {

    }

    public void authenticationMenu(){
    }

    public void adminMenu(){

    }

    public void employeeMenu(){

    }

    public void studentMenu(){

    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void searchInfo(){}
}
