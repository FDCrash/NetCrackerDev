package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.EmployeeConverter;
import com.netcracker.denisik.dao.daoImpl.EmployeeDAOImpl;
import com.netcracker.denisik.dao.daoImpl.UserDAOImpl;
import com.netcracker.denisik.dto.EmployeeDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements CRUDService<EmployeeDTO> {
    private EmployeeConverter employeeConverter;
    private static EmployeeServiceImpl instance;

    private EmployeeServiceImpl(){
        employeeConverter = new EmployeeConverter();
    }

    public static EmployeeServiceImpl getInstance(){
        if(instance == null){
            instance = new EmployeeServiceImpl();
        }
        return instance;
    }

    @Override
    public void addNew(EmployeeDTO employeeDTO) {
        EmployeeDAOImpl.getInstance().add(employeeConverter.convert(employeeDTO));
    }

    @Override
    public void deleteInfo(int id) {
        EmployeeDAOImpl.getInstance().delete(id);
    }

    @Override
    public void updateInfo(EmployeeDTO employeeDTO) {
        EmployeeDAOImpl.getInstance().update(employeeConverter.convert(employeeDTO));
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return EmployeeDAOImpl.getInstance().getAll().stream().map(employee -> employeeConverter.convert(employee)).
                collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO get(int id) {
        return employeeConverter.convert(EmployeeDAOImpl.getInstance().get(id));
    }
}
