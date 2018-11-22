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
    private UserDAOImpl userDAO;
    private UserConverter userConverter;
    private StudentDAOImpl studentDAO;

    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
        userConverter = new UserConverter();
        studentDAO = new StudentDAOImpl();
    }

    @Override
    public void addNew(UserDTO userDTO) {
        userDAO.add(userConverter.convert(userDTO));
    }

    @Override
    public void deleteInfo(int id) {
        userDAO.delete(id);
    }

    @Override
    public void updateInfo(UserDTO userDTO) {
        userDAO.update(userConverter.convert(userDTO));
    }

    @Override
    public List<UserDTO> getAll() {
        return userDAO.getAll().stream().map(user -> userConverter.convert(user)).collect(Collectors.toList());
    }


    @Override
    public UserDTO get(int id) {
        return userConverter.convert(userDAO.get(id));
    }

    public int generateId(int bound) {
        SplittableRandom splittableRandom = new SplittableRandom();
        int k;
        do {
            k = splittableRandom.nextInt(1, bound);

        } while (userDAO.get(k) != null);
        return k;
    }

    public String registration(int id, String login, String pass) {
        if (studentDAO.checkStudId(id)) {
            if (userDAO.checkLogin(login)) {
                studentDAO.addNewLoginPass(id, login, pass);
                return "Вы успешно зарегистрированы!";
            }
            return "Логин занят!";
        }
        return "Неверный номер студенченского билета!";
    }

    public boolean checkLogin(String login) {
        return userDAO.checkLogin(login);
    }

    public Enum authentication(String login, String pass) {
        return userDAO.checkLoginPass(login, pass);
    }
}
