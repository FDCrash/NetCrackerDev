package com.netcracker.denisik.rwstorage.rwstorageimpl;


import com.netcracker.denisik.entities.EmployeeEntity;
import com.netcracker.denisik.rwstorage.RWStorage;

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
