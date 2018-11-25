package com.netcracker.denisik;

import com.netcracker.denisik.controllers.Menu;
import com.netcracker.denisik.services.servicesimpl.StorageService;

public class Main {
    public static void main(String[] args) {
       // new StorageService().fillStorage();
        Menu.getInstance().adminMenu();
       // new StorageService().fillFile();
    }
}
