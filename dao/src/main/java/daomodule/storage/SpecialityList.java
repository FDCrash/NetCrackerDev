package daomodule.storage;

import daomodule.entities.SpecialityEntity;

import java.util.ArrayList;
import java.util.List;

public class SpecialityList {
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

    public List<SpecialityEntity> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<SpecialityEntity> specialities) {
        this.specialities=specialities;
    }
}
