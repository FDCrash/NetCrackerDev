package services.servicesimpl;

import converters.AdminConverter;
import daomodule.dao.daoImpl.AdminDAOImpl;
import daomodule.dao.daoImpl.UserDAOImpl;
import daomodule.entities.AdminEntity;
import daomodule.entities.UserEntity;
import dto.AdminDTO;
import services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements CRUDService<AdminDTO> {
    private AdminConverter adminConverter;
    private AdminDAOImpl adminDAO;
    private UserDAOImpl userDAO;

    public AdminServiceImpl(){
        adminConverter = new AdminConverter();
        adminDAO = new AdminDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    public void addNew(AdminDTO adminDTO) {
        adminDAO.add(adminConverter.convert(adminDTO));
    }

    @Override
    public void deleteInfo(int id) {
        adminDAO.delete(id);
        userDAO.deleteById(get(id).getId());
    }

    public void deleteInfoById(int id){
        adminDAO.deleteById(id);
        userDAO.deleteById(id);
    }

    @Override
    public void updateInfo(AdminDTO adminDTO) {
        adminDAO.update(adminConverter.convert(adminDTO));
    }

    @Override
    public List<AdminDTO> getAll() {
        return adminDAO.getAll().stream().map(admin->adminConverter.convert(admin)).collect(Collectors.toList());
    }

    @Override
    public AdminDTO get(int id) {
        return adminConverter.convert(adminDAO.get(id));
    }

    public void changeStatusAdmin(String login){
        adminDAO.changeStatus(login);
    }
}
