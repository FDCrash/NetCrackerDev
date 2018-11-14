package services.servicesimpl;

import converters.EmployeeConverter;
import daomodule.dao.daoImpl.EmployeeDAOImpl;
import daomodule.entities.EmployeeEntity;
import dto.EmployeeDTO;
import services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements CRUDService<EmployeeDTO> {
    private EmployeeDAOImpl employeeDAO;
    private EmployeeConverter employeeConverter;

    public EmployeeServiceImpl(){
        employeeDAO=new EmployeeDAOImpl();
        employeeConverter= new EmployeeConverter();
    }

    @Override
    public void addNew(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity= employeeConverter.convert(employeeDTO);
        employeeDAO.add(employeeEntity);
    }

    @Override
    public void deleteInfo(int id) {
        employeeDAO.delete(id);
    }

    @Override
    public void updateInfo(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity= employeeConverter.convert(employeeDTO);
        employeeDAO.update(employeeEntity);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeDAO.getAll().stream().map(employee->employeeConverter.convert(employee)).
                collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO get(int id) {
        EmployeeDTO employeeDTO= employeeConverter.convert(employeeDAO.get(id));
        return employeeDTO;
    }
}
