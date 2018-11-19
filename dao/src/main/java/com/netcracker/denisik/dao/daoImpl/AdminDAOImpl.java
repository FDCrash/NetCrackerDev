package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.DAO;
import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.storage.AdminList;

import java.util.List;

public class AdminDAOImpl implements DAO<AdminEntity> {
    @Override
    public AdminEntity get(int id) {
        return AdminList.getInstance().get().get(id);
    }

    @Override
    public List<AdminEntity> getAll() {
        return AdminList.getInstance().get();
    }

    @Override
    public AdminEntity add(AdminEntity adminEntity) {
        return null;
    }

    @Override
    public AdminEntity update(AdminEntity admin) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    public void changeStatus(String login){

    }
}
