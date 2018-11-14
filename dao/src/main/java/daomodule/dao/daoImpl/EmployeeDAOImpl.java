package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.EmployeeEntity;
import daomodule.storage.EmployeeList;

import java.util.List;

public class EmployeeDAOImpl implements DAO<EmployeeEntity> {
    @Override
    public EmployeeEntity get(int id) {
        return EmployeeList.getInstance().get().get(id);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return EmployeeList.getInstance().get();
    }

    @Override
    public void add(EmployeeEntity employeeEntity) {

    }

    @Override
    public void update(EmployeeEntity employee) {

    }

    @Override
    public void delete(int id) {

    }
}
