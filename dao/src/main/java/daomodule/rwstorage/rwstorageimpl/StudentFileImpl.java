package daomodule.rwstorage.rwstorageimpl;


import daomodule.entities.StudentEntity;
import daomodule.rwstorage.RWStorage;

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
