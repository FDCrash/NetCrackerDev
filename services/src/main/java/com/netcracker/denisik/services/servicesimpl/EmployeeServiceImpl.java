package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.EmployeeConverter;
import com.netcracker.denisik.dao.daoImpl.EmployeeDAOImpl;
import com.netcracker.denisik.dao.daoImpl.UserDAOImpl;
import com.netcracker.denisik.dto.EmployeeDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements CRUDService<EmployeeDTO> {
    private EmployeeDAOImpl employeeDAO;
    private EmployeeConverter employeeConverter;
    private UserDAOImpl userDAO;

    public EmployeeServiceImpl(){
        employeeDAO=new EmployeeDAOImpl();
        employeeConverter= new EmployeeConverter();
        userDAO = new UserDAOImpl();
    }

    @Override
    public void addNew(EmployeeDTO employeeDTO) {
        employeeDAO.add(employeeConverter.convert(employeeDTO));
    }

    @Override
    public void deleteInfo(int id) {
        employeeDAO.delete(id);
    }

    @Override
    public void updateInfo(EmployeeDTO employeeDTO) {
        employeeDAO.update(employeeConverter.convert(employeeDTO));
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeDAO.getAll().stream().map(employee->employeeConverter.convert(employee)).
                collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO get(int id) {
        return employeeConverter.convert(employeeDAO.get(id));
    }
}
