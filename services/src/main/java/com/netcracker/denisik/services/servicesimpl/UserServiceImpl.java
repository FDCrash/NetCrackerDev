package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.UserConverter;
import com.netcracker.denisik.dao.daoImpl.StudentDAOImpl;
import com.netcracker.denisik.dao.daoImpl.UserDAOImpl;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.AbstractService;
import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.sql.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

public class UserServiceImpl extends AbstractService<UserDTO> {
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
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            UserDAOImpl.getInstance().add(userConverter.convert(userDTO));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id){
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            UserDAOImpl.getInstance().delete(id);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void update(UserDTO userDTO) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            UserDAOImpl.getInstance().update(userConverter.convert(userDTO));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            userDTO=UserDAOImpl.getInstance().getAll().stream()
                    .map(user -> userConverter.convert(user))
                    .collect(Collectors.toList());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return userDTO;
    }


    @Override
    public UserDTO get(int id){
        UserDTO userDTO=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            userDTO=userConverter.convert(UserDAOImpl.getInstance().get(id));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return userDTO;
    }

    public int generateId(int bound){
        SplittableRandom splittableRandom = new SplittableRandom();
        int id=-1;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            do {
                id = splittableRandom.nextInt(1, bound);
            } while (UserDAOImpl.getInstance().get(id) != null);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return id;
    }

    public String registration(int id, String login, String pass){
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            do {
                if (StudentDAOImpl.getInstance().checkStudId(id)) {
                    if (!UserDAOImpl.getInstance().checkUserLogin(login)) {
                        StudentDAOImpl.getInstance().addNewLoginPass(id, login, pass);
                        return "Вы успешно зарегистрированы!";
                    }
                    return "Логин занят!";
                }
            } while (UserDAOImpl.getInstance().get(id) != null);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return "Неверный номер студенченского билета!";
    }

    public boolean checkLogin(String login) {
        boolean free=false;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            free=UserDAOImpl.getInstance().checkUserLogin(login);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return free;
    }

    public Enum authentication(String login, String pass){
        Enum role=null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connection.setAutoCommit(false);
            role=UserDAOImpl.getInstance().checkLoginPass(login, pass);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return role;
    }
}
