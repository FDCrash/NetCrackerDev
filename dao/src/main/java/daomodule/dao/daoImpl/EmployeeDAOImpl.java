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
        EmployeeList.getInstance().add(employeeEntity);
    }

    @Override
    public void update(EmployeeEntity employee) {
        for(EmployeeEntity employeeEntity: EmployeeList.getInstance().get() ){
            if(employee.getId()==employeeEntity.getId()){
                employeeEntity.setLogin(employee.getLogin());
                employeeEntity.setPassword(employee.getPassword());
                employeeEntity.setName(employee.getName());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        EmployeeList.getInstance().get().remove(id);
    }
}
