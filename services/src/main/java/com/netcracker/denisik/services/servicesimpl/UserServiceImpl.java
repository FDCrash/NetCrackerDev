package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.UserConverter;
import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.dao.daoImpl.StudentDAOImpl;
import com.netcracker.denisik.dao.daoImpl.UserDAOImpl;
import com.netcracker.denisik.dto.UserDTO;

import java.util.List;

public class UserServiceImpl implements CRUDService<UserDTO> {
    private UserDAOImpl userDAO;
    private UserConverter userConverter;
    private StudentDAOImpl studentDAO;

    public UserServiceImpl(){

    }

    @Override
    public void addNew(UserDTO userDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(UserDTO userDTO) {

    }

    @Override
    public List<UserDTO> getAll() {
        return null;
    }


    @Override
    public UserDTO get(int id) {
        return null;
    }

    public int generateId(int bound){

            return 0;
    }

    public String registration(int id,String login,String pass){


        return null;
    }

    public Enum authentication(String login, String pass){
        return null;
    }


}
