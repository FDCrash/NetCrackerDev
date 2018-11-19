package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.services.servicesimpl.EmployeeServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

public class EmployeeControllerImpl implements Controller {
    private EmployeeServiceImpl employeeService;
    private UserServiceImpl userService;
    private Scanner scanner;

    public EmployeeControllerImpl(){
    }

    @Override
    public void editMenu() {
    }

    @Override
    public void getAll() {
    }

    @Override
    public void add() {

    }

    @Override
    public void update() {
    }

    @Override
    public void delete() {
    }
}

