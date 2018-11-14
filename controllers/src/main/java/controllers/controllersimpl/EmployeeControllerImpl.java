package controllers.controllersimpl;

import controllers.ControllerMD;
import services.servicesimpl.EmployeeServiceImpl;
import services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

public class EmployeeControllerImpl implements ControllerMD {
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

