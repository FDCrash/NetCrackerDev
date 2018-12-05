package com.netcracker.denisik.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class SpecialityEntity extends BaseEntity {
    private String name;
    private FacultyEntity faculty;

    public SpecialityEntity() {
    }

    public SpecialityEntity(int id, String name, FacultyEntity faculty) {
        super(id);
        this.name = name;
        this.faculty = faculty;
    }

    public SpecialityEntity(int id, String name, int facultyId) {
        super(id);
        this.name = name;
        faculty = new FacultyEntity(facultyId, "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "specialities")
    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

}

