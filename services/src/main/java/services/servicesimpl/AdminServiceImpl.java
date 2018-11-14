package services.servicesimpl;

import converters.AdminConverter;
import daomodule.dao.daoImpl.AdminDAOImpl;
import daomodule.entities.AdminEntity;
import dto.AdminDTO;
import services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements CRUDService<AdminDTO> {
    private AdminConverter adminConverter;
    private AdminDAOImpl adminDAO;

    public AdminServiceImpl(){
        adminConverter = new AdminConverter();
        adminDAO = new AdminDAOImpl();
    }

    @Override
    public void addNew(AdminDTO adminDTO) {
        AdminEntity adminEntity=adminConverter.convert(adminDTO);
        adminDAO.add(adminEntity);
    }

    @Override
    public void deleteInfo(int id) {
        adminDAO.delete(id);
    }

    @Override
    public void updateInfo(AdminDTO adminDTO) {
        AdminEntity adminEntity=adminConverter.convert(adminDTO);
        adminDAO.update(adminEntity);
    }

    @Override
    public List<AdminDTO> getAll() {
        return adminDAO.getAll().stream().map(admin->adminConverter.convert(admin)).collect(Collectors.toList());
    }

    @Override
    public AdminDTO get(int id) {
        AdminDTO adminDTO= adminConverter.convert(adminDAO.get(id));
        return adminDTO;
    }

    public void changeStatusAdmin(String login){
        adminDAO.changeStatus(login);
    }
}
