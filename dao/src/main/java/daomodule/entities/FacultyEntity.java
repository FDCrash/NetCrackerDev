package daomodule.entities;

import java.util.List;

public class FacultyEntity extends BaseEntity {
    private String name;
    private List<SpecialityEntity> specialities;

    public FacultyEntity(int id,String name){
        this.setId(id);
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpeciality(List<SpecialityEntity> speciality) {
        this.specialities = speciality;
    }

    public List<SpecialityEntity> getSpeciality() {
        return specialities;
    }
}
