package daomodule.entities;

import java.util.List;

public class FacultyEntity extends BaseEntity {
    private String name;
    private List<String> speciality;

    public FacultyEntity(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpeciality(List<String> speciality) {
        this.speciality = speciality;
    }

    public List<String> getSpeciality() {
        return speciality;
    }
}
