package dao.dao.daoImpl;

import dao.dao.DAO;
import dao.entities.AdminEntity;
import dao.storage.AdminList;

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
