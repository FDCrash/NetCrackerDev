package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.AdminConverter;
import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.dao.daoImpl.AdminDAOImpl;

import com.netcracker.denisik.dto.AdminDTO;

import java.util.List;


public class AdminServiceImpl implements CRUDService<AdminDTO> {
    private AdminConverter adminConverter;
    private AdminDAOImpl adminDAO;

    public AdminServiceImpl(){

    }

    @Override
    public void addNew(AdminDTO adminDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(AdminDTO adminDTO) {

    }

    @Override
    public List<AdminDTO> getAll() {
        return null;
    }

    @Override
    public AdminDTO get(int id) {

        return null;
    }

    public void changeStatusAdmin(String login){

    }
}
