package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.sql.PoolManager;
import com.netcracker.denisik.sql.SqlRequest;
import com.netcracker.denisik.storage.AdminList;
import com.netcracker.denisik.storage.UserList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl extends AbstractDao<AdminEntity> {
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
        List<AdminEntity> list=new ArrayList<>();
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_ADMINS);
            result = statement.executeQuery();
            while(result.next()){
                list.add(new AdminEntity(new UserEntity(result.getInt(1),
                        Role.valueOf(result.getString(2)),
                        result.getString(3),result.getString(4)),
                        result.getBoolean(5)));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд");
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием");
            }
        }
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
