package com.netcracker.denisik.rwstorage.rwstorageimpl;


import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.rwstorage.RWStorage;

import org.json.simple.JSONArray;

import java.util.List;



public class StudentFileImpl implements RWStorage {
    private Object obj;
    private List<StudentEntity> students;
    private JSONArray jsonArray;

    public StudentFileImpl(){}

    @Override
    public void fillStorage(){

    }

}
