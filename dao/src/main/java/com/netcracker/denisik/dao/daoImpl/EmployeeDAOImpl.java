package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.DAO;
import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.storage.EmployeeList;
import com.netcracker.denisik.storage.UserList;

import java.util.List;

public class EmployeeDAOImpl implements DAO<EmployeeEntity> {
    @Override
    public EmployeeEntity get(int id) {
        return getAll().get(id);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return EmployeeList.getInstance().get();
    }

    @Override
    public EmployeeEntity add(EmployeeEntity employeeEntity) {
        EmployeeList.getInstance().add(employeeEntity);
        UserList.getInstance().addEmployee(employeeEntity);
        return employeeEntity;
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employee) {
        for(EmployeeEntity employeeEntity: getAll()){
            if(employee.getId()==employeeEntity.getId()){
                employeeEntity.setLogin(employee.getLogin());
                employeeEntity.setPassword(employee.getPassword());
                employeeEntity.setName(employee.getName());
                break;
            }
        }
        return employee;
    }

    public void deleteById(int id){
        for(EmployeeEntity employeeEntity:getAll()){
            if(employeeEntity.getId()==id){
                getAll().remove(employeeEntity);
            }
        }
    }

    @Override
    public void delete(int id) {
        getAll().remove(id);
    }
}
