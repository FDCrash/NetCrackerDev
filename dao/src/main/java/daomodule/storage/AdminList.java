package daomodule.storage;

import daomodule.entities.AdminEntity;

import java.util.ArrayList;
import java.util.List;

public class AdminList {
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

    public void setAdmins(List<AdminEntity> admins) {
        this.admins=admins;
    }

    public List<AdminEntity> getAdmins() {
        return admins;
    }
}
