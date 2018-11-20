package com.netcracker.denisik.controllers.controllersimpl;

import com.netcracker.denisik.controllers.Controller;
import com.netcracker.denisik.services.servicesimpl.AdminServiceImpl;
import com.netcracker.denisik.services.servicesimpl.UserServiceImpl;

import java.util.Scanner;

public class AdminControllerImpl implements Controller {
    private AdminServiceImpl adminService;
    private Scanner scanner;
    private UserServiceImpl userService;

    public AdminControllerImpl(){
    }

    @Override
    public void editMenu(){
    }

    @Override
    public void getAll(){
    }

    @Override
    public void add(){
    }

    @Override
    public void update(){

    }

    @Override
    public void delete() {

    }
}
