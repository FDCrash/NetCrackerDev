package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.IDao;
import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.storage.EmployeeList;
import com.netcracker.denisik.storage.UserList;

import java.util.List;

public class EmployeeDAOImpl implements IDao<EmployeeEntity> {

    @Override
    public EmployeeEntity get(int id) {
        return getAll().stream()
                .filter(employee -> employee.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return EmployeeList.getInstance().get();
    }

    @Override
    public EmployeeEntity add(EmployeeEntity employeeEntity) {
        EmployeeList.getInstance().add(employeeEntity);
        UserList.getInstance().addEmployee(employeeEntity);
        return get(employeeEntity.getId());
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employee) {
        delete(employee.getId());
        add(employee);
        return get(employee.getId());
    }

    @Override
    public void delete(int id) {
        getAll().remove(get(id));
        new UserDAOImpl().getAll().remove(new UserDAOImpl().get(id));
    }
}
