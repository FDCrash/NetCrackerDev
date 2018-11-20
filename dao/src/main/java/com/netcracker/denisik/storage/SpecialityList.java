package com.netcracker.denisik.storage;

import com.netcracker.denisik.entities.SpecialityEntity;

import java.util.ArrayList;
import java.util.List;

public class SpecialityList implements Storage<SpecialityEntity>{
    private static SpecialityList instance;
    private List<SpecialityEntity> specialities=new ArrayList<>();

    private SpecialityList(){
    }

    public static SpecialityList getInstance() {
        if(instance==null){
            instance=new SpecialityList();
        }
        return instance;
    }


    @Override
    public List<SpecialityEntity> get() {
        return specialities;
    }

    @Override
    public void set(List<SpecialityEntity> specialities) {

    }

    @Override
    public void add(SpecialityEntity specialityEntity) {

    }
}