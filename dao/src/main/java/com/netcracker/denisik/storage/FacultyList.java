package com.netcracker.denisik.storage;

import com.netcracker.denisik.entities.FacultyEntity;

import java.util.ArrayList;
import java.util.List;

public class FacultyList implements Storage<FacultyEntity>{
    private static FacultyList instance;
    private List<FacultyEntity> faculties=new ArrayList<>();

    private FacultyList(){
    }

    public static FacultyList getInstance() {
        if(instance==null){
            instance=new FacultyList();
        }
        return instance;
    }


    @Override
    public List<FacultyEntity> get() {
        return faculties;
    }

    @Override
    public void set(List<FacultyEntity> faculties) {

    }

    @Override
    public void add(FacultyEntity facultyEntity) {

    }
}