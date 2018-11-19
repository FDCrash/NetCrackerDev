package services.servicesimpl;

import converters.EmployeeConverter;
import dao.dao.daoImpl.EmployeeDAOImpl;
import dto.EmployeeDTO;
import services.CRUDService;

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
