package com.netcracker.denisik.storage;

import com.netcracker.denisik.entities.AdminEntity;

import java.util.ArrayList;
import java.util.List;

public class AdminList implements Storage<AdminEntity>{
    private static AdminList instance;
    private List<AdminEntity> admins = new ArrayList<>();

    private AdminList(){
    }

    public static AdminList getInstance() {
        if(instance==null){
            instance=new AdminList();
        }
        return instance;
    }

    @Override
    public List<AdminEntity> get() {
        return admins;
    }

    @Override
    public void set(List<AdminEntity> admins) {

    }

    @Override
    public void add(AdminEntity adminEntity) {

    }
}
