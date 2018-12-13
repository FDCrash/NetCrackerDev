package com.netcracker.denisik.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity{

    @Column(name = "subject",unique = true)
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Semester> semesters;

    @Builder
    public Subject(long id, String name, List<Semester> semesters) {
        super(id);
        this.name = name;
        this.semesters = semesters;
    }
}
