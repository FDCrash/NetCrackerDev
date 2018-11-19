package dao.dao.daoImpl;

import dao.dao.DAO;
import dao.entities.EmployeeEntity;
import dao.storage.EmployeeList;

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
    public EmployeeEntity add(EmployeeEntity employeeEntity) {
        return null;
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employeeEntity) {
        return null;
    }


    @Override
    public void delete(int id) {

    }
}
