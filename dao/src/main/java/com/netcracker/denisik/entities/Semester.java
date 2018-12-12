package com.netcracker.denisik.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "semesters")
public class Semester {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sem")
    private int sem;

    @Column(name = "mark")
    private int mark;

    @ManyToOne
    @JoinColumn(name = "subjects_id")
    private Subject subject;
}
