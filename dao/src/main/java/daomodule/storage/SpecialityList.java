package daomodule.storage;

import daomodule.entities.SpecialityEntity;

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
        this.specialities=specialities;
    }

    @Override
    public void add(SpecialityEntity specialityEntity) {
        this.specialities.add(specialityEntity);
    }

    public void addAll(List<SpecialityEntity> specialityEntities){
        this.specialities.addAll(specialityEntities);
    }
}
