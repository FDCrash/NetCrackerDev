package daomodule.storage;

import daomodule.entities.EmployeeEntity;

import java.util.List;

public class EmployeeList {
    private static EmployeeList instance;
    private List<EmployeeEntity> employees;

    public static EmployeeList getInstance() {
        if(instance==null){
            instance=new EmployeeList();
        }
        return instance;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }
}
