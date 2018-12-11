package com.netcracker.denisik.entities;

import javax.persistence.*;

@Entity
@Table(name = "specialities")
public class Speciality {
    private long id;
    private String name;
    private Faculty faculty;

    public Speciality() {
    }

    public Speciality(long id, String name, Faculty faculty) {
        this.id=id;
        this.name = name;
        this.faculty = faculty;
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

    @Column(name = "name",unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "faculties_id")
    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

}

