package com.netcracker.denisik.dao.daoImpl;


import com.netcracker.denisik.dao.IDao;
import com.netcracker.denisik.entities.*;
import com.netcracker.denisik.storage.UserList;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IDao<UserEntity> {
    private StudentDAOImpl studentDAO;
    private EmployeeDAOImpl employeeDAO;
    private AdminDAOImpl adminDAO;

    public UserDAOImpl() {
        adminDAO = new AdminDAOImpl();
        studentDAO = new StudentDAOImpl();
        employeeDAO = new EmployeeDAOImpl();
    }

    @Override
    public UserEntity get(int id) {
        return getAll().stream()
                .filter(user -> user.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<UserEntity> getAll() {
        return UserList.getInstance().get();
    }

    @Override
    public UserEntity add(UserEntity userEntity) {
        switch (userEntity.getRole()) {
            case ADMIN:
                adminDAO.add(new AdminEntity(new UserEntity(userEntity), false));
                break;
            case EMPLOYEE:
                employeeDAO.add(new EmployeeEntity(new UserEntity(userEntity), ""));
                break;
            case STUDENT:
                studentDAO.add(new StudentEntity(new UserEntity(userEntity),
                        "", 0, 0, 0, new ArrayList<>()));
        }
        return get(userEntity.getId());
    }

    @Override
    public UserEntity update(UserEntity user) {
        int k = user.getId();
        switch (user.getRole()) {
            case ADMIN:
                adminDAO.update(new AdminEntity(new UserEntity(user),
                        adminDAO.get(k).getStatus()));
                break;
            case EMPLOYEE:
                employeeDAO.update(new EmployeeEntity(new UserEntity(user),
                        employeeDAO.get(k).getName()));
                break;
            case STUDENT:
                studentDAO.update(new StudentEntity(new UserEntity(user),
                        studentDAO.get(k).getName(), studentDAO.get(k).getStudentId(),
                        studentDAO.get(k).getGroupId(), studentDAO.get(k).getSpecialityEntity().getId(),
                        studentDAO.get(k).getWriteBook()));
        }
        return get(user.getId());
    }

    @Override
    public void delete(int id) {
        switch (get(id).getRole()) {
            case ADMIN:
                adminDAO.delete(id);
                break;
            case EMPLOYEE:
                employeeDAO.delete(id);
                break;
            case STUDENT:
                studentDAO.delete(id);
        }
    }

    public Enum checkLoginPass(String login, String pass) {
        return getAll().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(pass))
                .findFirst().get().getRole();
    }

    public boolean checkLogin(String login) {
        return getAll().stream()
                .anyMatch(user -> user.getLogin().equals(login));
    }
}
