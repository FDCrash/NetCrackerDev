package com.netcracker.denisik;

import com.netcracker.denisik.controllers.Menu;
import com.netcracker.denisik.controllers.controllersimpl.AdminControllerImpl;
import com.netcracker.denisik.controllers.controllersimpl.EmployeeControllerImpl;
import com.netcracker.denisik.controllers.controllersimpl.FacultyControllerImpl;
import com.netcracker.denisik.services.servicesimpl.*;

public class Main {
    public static void main(String[] args) {
       // new StorageService().fillStorage();
        new FacultyControllerImpl().add();
       // new StorageService().fillFile();
    }
}
