package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.IDao;
import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.storage.AdminList;
import com.netcracker.denisik.storage.UserList;

import java.util.List;

public class AdminDAOImpl implements IDao<AdminEntity> {
    private static AdminDAOImpl instance;

    private AdminDAOImpl(){}

    public static AdminDAOImpl getInstance(){
        if(instance==null){
            instance = new AdminDAOImpl();
        }
        return instance;
    }

    @Override
    public AdminEntity get(int id) {
        return getAll().stream()
                .filter(admin -> admin.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AdminEntity> getAll() {
        return AdminList.getInstance().get();
    }

    @Override
    public AdminEntity add(AdminEntity adminEntity) {
        AdminList.getInstance().add(adminEntity);
        UserList.getInstance().addAdmin(adminEntity);
        return get(adminEntity.getId());
    }

    @Override
    public AdminEntity update(AdminEntity admin) {
        delete(admin.getId());
        add(admin);
        return get(admin.getId());
    }

    @Override
    public void delete(int id) {
        if(!get(id).getStatus()) {
            getAll().remove(get(id));
            UserDAOImpl.getInstance().getAll().remove(UserDAOImpl.getInstance().get(id));
        }
    }

    public void changeStatus(String login) {
        getAll().stream()
                .filter(admin -> admin.getLogin().equals(login))
                .findFirst().get().setStatus(true);
    }
}
