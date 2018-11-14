package daomodule.rwstorage.rwstorageimpl;


import daomodule.entities.EmployeeEntity;

import daomodule.rwstorage.RWStorage;

import org.json.simple.JSONArray;

import java.util.List;

public class EmployeeFileImpl implements RWStorage {
    private Object obj;
    private List<EmployeeEntity> employees;
    private JSONArray jsonArray;

    @Override
    public void fillStorage() {

    }
}
