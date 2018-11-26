package com.netcracker.denisik;

import com.netcracker.denisik.controllers.Menu;
import com.netcracker.denisik.services.servicesimpl.*;

public class Main {
    public static void main(String[] args) {
       // new StorageService().fillStorage();
        System.out.println(StudentServiceImpl.getInstance().get(6));
       // new StorageService().fillFile();
    }
}
