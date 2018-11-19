package com.netcracker.denisik.storage;

import com.netcracker.denisik.entities.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList implements Storage<EmployeeEntity>{
    private static EmployeeList instance;
    private List<EmployeeEntity> employees=new ArrayList<>();

    private EmployeeList(){
    }

    public static EmployeeList getInstance() {
        if (instance == null) {
            instance = new EmployeeList();
        }
        return instance;
    }

    @Override
    public List<EmployeeEntity> get() {
        return employees;
    }

    @Override
    public void set(List<EmployeeEntity> employees) {
        this.employees=employees;
    }

    @Override
    public void add(EmployeeEntity employeeEntity) {
        this.employees.add(employeeEntity);
    }
}
