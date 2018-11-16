package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.EmployeeEntity;
import daomodule.storage.EmployeeList;
import daomodule.storage.UserList;

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
    public void add(EmployeeEntity employeeEntity) {
        EmployeeList.getInstance().add(employeeEntity);
        UserList.getInstance().addEmployee(employeeEntity);
    }

    @Override
    public void update(EmployeeEntity employee) {
        for(EmployeeEntity employeeEntity: getAll()){
            if(employee.getId()==employeeEntity.getId()){
                employeeEntity.setLogin(employee.getLogin());
                employeeEntity.setPassword(employee.getPassword());
                employeeEntity.setName(employee.getName());
                break;
            }
        }
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
