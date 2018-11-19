package dao.rwstorage.rwstorageimpl;


import dao.entities.EmployeeEntity;

import dao.rwstorage.RWStorage;

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
