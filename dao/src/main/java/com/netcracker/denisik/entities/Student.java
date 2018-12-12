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
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "studentId",unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;

    @Column(name = "groupId")
    private int groupId;

    @ManyToOne
    @JoinColumn(name = "specialities_id")
    private Speciality speciality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "writebook_id")
    private WriteBook writeBook;
}
