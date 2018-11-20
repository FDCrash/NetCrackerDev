package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.converters.EmployeeConverter;
import com.netcracker.denisik.dao.daoImpl.EmployeeDAOImpl;
import com.netcracker.denisik.dto.EmployeeDTO;

import java.util.List;

public class EmployeeServiceImpl implements CRUDService<EmployeeDTO> {
    private EmployeeDAOImpl employeeDAO;
    private EmployeeConverter employeeConverter;

    public EmployeeServiceImpl(){

    }

    @Override
    public void addNew(EmployeeDTO employeeDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(EmployeeDTO employeeDTO) {

    }

    @Override
    public List<EmployeeDTO> getAll() {
        return null;
    }

    @Override
    public EmployeeDTO get(int id) {

        return null;
    }
}
