package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.DAO;
import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.storage.AdminList;
import com.netcracker.denisik.storage.UserList;

import java.util.List;

public class AdminDAOImpl implements DAO<AdminEntity> {

    @Override
    public AdminEntity get(int id) {
        for(AdminEntity adminEntity:getAll()){
            if(adminEntity.getId()==id){
                return adminEntity;
            }
        }
        return null;
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
            getAll().remove(get(id));
            new UserDAOImpl().getAll().remove(new UserDAOImpl().get(id));
    }

    public void changeStatus(String login){
        for(AdminEntity adminEntity: getAll()){
            if(adminEntity.getLogin().equals(login)){
                adminEntity.setStatus(true);
                break;
            }
        }
    }
}
