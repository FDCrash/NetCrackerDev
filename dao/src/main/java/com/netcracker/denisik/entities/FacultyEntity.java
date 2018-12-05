package com.netcracker.denisik.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FacultyEntity extends BaseEntity {
    private String name;
    private List<SpecialityEntity> specialities;

    public FacultyEntity() {
    }

    public FacultyEntity(int id, String name) {
        super(id);
        this.name = name;
        specialities = new ArrayList<>();
    }

    public FacultyEntity(int id, String name, List<SpecialityEntity> specialities) {
        super(id);
        this.name = name;
        this.specialities = new ArrayList<>(specialities);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpecialities(List<SpecialityEntity> specialities) {
        this.specialities = specialities;
    }

    public void setSpeciality(SpecialityEntity speciality) {
        this.specialities.add(speciality);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "faculty_speciality", joinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "id"))
    public List<SpecialityEntity> getSpecialities() {
        return specialities;
    }
}
