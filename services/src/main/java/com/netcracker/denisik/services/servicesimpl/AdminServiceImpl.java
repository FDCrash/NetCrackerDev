package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.AdminConverter;
import com.netcracker.denisik.dao.daoImpl.AdminDAOImpl;
import com.netcracker.denisik.dto.AdminDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements CRUDService<AdminDTO> {
    private AdminConverter adminConverter;
    private static AdminServiceImpl instance;

    private AdminServiceImpl() {
        adminConverter = new AdminConverter();
    }

    public static AdminServiceImpl getInstance() {
        if (instance == null) {
            instance = new AdminServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(AdminDTO adminDTO) {
        AdminDAOImpl.getInstance().add(adminConverter.convert(adminDTO));
    }

    @Override
    public void delete(int id) {
        AdminDAOImpl.getInstance().delete(id);
    }

    @Override
    public void update(AdminDTO adminDTO) {
        AdminDAOImpl.getInstance().update(adminConverter.convert(adminDTO));
    }

    @Override
    public List<AdminDTO> getAll() {
        return AdminDAOImpl.getInstance()
                .getAll().stream()
                .map(admin -> adminConverter.convert(admin)).collect(Collectors.toList());
    }

    @Override
    public AdminDTO get(int id) {
        return adminConverter.convert(AdminDAOImpl.getInstance().get(id));
    }

    public void changeStatusAdmin(String login) {
        AdminDAOImpl.getInstance().changeStatus(login);
    }
}
