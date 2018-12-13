package com.netcracker.denisik.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "faculties_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "speciality")
    private List<Student> student;

    @Builder
    public Speciality(long id, String name, Faculty faculty, List<Student> student) {
        super(id);
        this.name = name;
        this.faculty = faculty;
        this.student = student;
    }
}

