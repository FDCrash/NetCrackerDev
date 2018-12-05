package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.UserConverter;
import com.netcracker.denisik.dao.daoImpl.StudentDAOImpl;
import com.netcracker.denisik.dao.daoImpl.UserDAOImpl;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

public class UserServiceImpl implements CRUDService<UserDTO> {
    private UserConverter userConverter;
    private static UserServiceImpl instance;

    private UserServiceImpl() {
        userConverter = new UserConverter();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(UserDTO userDTO) {
        UserDAOImpl.getInstance().add(userConverter.convert(userDTO));
    }

    @Override
    public void delete(int id) {
        UserDAOImpl.getInstance().delete(id);
    }

    @Override
    public void update(UserDTO userDTO) {
        UserDAOImpl.getInstance().update(userConverter.convert(userDTO));
    }

    @Override
    public List<UserDTO> getAll() {
        return UserDAOImpl.getInstance().getAll().stream()
                .map(user -> userConverter.convert(user))
                .collect(Collectors.toList());
    }


    @Override
    public UserDTO get(int id) {
        return userConverter.convert(UserDAOImpl.getInstance().get(id));
    }

    public int generateId(int bound) {
        SplittableRandom splittableRandom = new SplittableRandom();
        int id;
        do {
            id = splittableRandom.nextInt(1, bound);
        } while (UserDAOImpl.getInstance().get(id) != null);
        return id;
    }

    public boolean registration(int id, String login, String pass) {
        if (StudentDAOImpl.getInstance().checkStudId(id)) {
            if (checkLogin(login)) {
                StudentDAOImpl.getInstance().addNewLoginPass(id, login, pass);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean checkLogin(String login) {
        return UserDAOImpl.getInstance().checkUserLogin(login);
    }

    public Enum authentication(String login, String pass) {
        return UserDAOImpl.getInstance().checkLoginPass(login, pass);
    }
}
