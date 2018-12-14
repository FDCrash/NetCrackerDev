package com.netcracker.denisik.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "faculties")
public class Faculty {
    private long id;
    private String name;
    private List<Speciality> specialities;

    public Faculty() {
    }

    public Faculty(long id, String name) {
        this.id=id;
        this.name = name;
        specialities = new ArrayList<>();
    }

    public Faculty(long id, String name, List<Speciality> specialities) {
        this.id=id;
        this.name = name;
        this.specialities = new ArrayList<>(specialities);
    }

    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }


    @OneToMany(mappedBy = "facultyEntity",cascade = CascadeType.ALL)
    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpeciality(Speciality speciality){
        this.specialities.add(speciality);
    }
}
