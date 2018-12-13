package com.netcracker.denisik.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "writebook")
public class WriteBook extends BaseEntity{

    @OneToMany(mappedBy = "writeBook",cascade =CascadeType.ALL)
    private List<Semester> semesters;

    @Column(name = "budjet")
    private boolean budget;

    @OneToOne(mappedBy = "writeBook")
    private Student student;

    @Builder
    public WriteBook(long id, List<Semester> semesters, boolean budget, Student student) {
        super(id);
        this.semesters = semesters;
        this.budget = budget;
        this.student = student;
    }
}
