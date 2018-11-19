package dao.dao.daoImpl;

import dao.dao.DAO;
import dao.entities.AdminEntity;
import dao.storage.AdminList;
import dao.storage.UserList;

import java.util.List;

public class AdminDAOImpl implements DAO<AdminEntity> {
    @Override
    public AdminEntity get(int id) {
        return getAll().get(id);
    }

    @Override
    public List<AdminEntity> getAll() {
        return AdminList.getInstance().get();
    }

    @Override
    public AdminEntity add(AdminEntity adminEntity) {
        AdminList.getInstance().add(adminEntity);
        UserList.getInstance().addAdmin(adminEntity);
        return adminEntity;
    }

    @Override
    public AdminEntity update(AdminEntity admin) {
        for(AdminEntity adminEntity:getAll()){
            if(admin.getId()==adminEntity.getId()){
                adminEntity.setLogin(admin.getLogin());
                adminEntity.setPassword(admin.getPassword());
                break;
            }
        }
        return admin;
    }

    public void deleteById(int id){
        for(AdminEntity adminEntity:getAll()){
            if(adminEntity.getId()==id){
                getAll().remove(adminEntity);
            }
        }
    }

    @Override
    public void delete(int id) {
        if(!getAll().get(id).getStatus()){
            getAll().remove(id);
        }
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
