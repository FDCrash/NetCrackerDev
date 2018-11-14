package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.AdminEntity;
import daomodule.storage.AdminList;

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
    public void add(AdminEntity adminEntity) {
        AdminList.getInstance().add(adminEntity);
    }

    @Override
    public void update(AdminEntity admin) {
        for(AdminEntity adminEntity:AdminList.getInstance().get()){
            if(admin.getId()==adminEntity.getId()){
                adminEntity.setLogin(admin.getLogin());
                adminEntity.setPassword(admin.getPassword());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        if(!AdminList.getInstance().get().get(id).getStatus()){
            AdminList.getInstance().get().remove(id);
        }
    }

    public void changeStatus(String login){
        for(AdminEntity adminEntity: AdminList.getInstance().get()){
            if(adminEntity.getLogin().equals(login)){
                adminEntity.setStatus(true);
                break;
            }
        }
    }
}
