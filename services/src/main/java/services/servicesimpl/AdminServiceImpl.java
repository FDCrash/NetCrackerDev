package services.servicesimpl;

import converters.AdminConverter;
import daomodule.dao.daoImpl.AdminDAOImpl;

import dto.AdminDTO;
import services.CRUDService;

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
