package com.netcracker.denisik.entities;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    private long id;
    private int studentId;
    private int groupId;
    private Speciality speciality;
    private WriteBook writeBook;

    public Student() {
    }

    public Student(long id, int studentId, int groupId, Speciality speciality, WriteBook writeBook) {
        this.id = id;
        this.studentId = studentId;
        this.groupId = groupId;
        this.speciality = speciality;
        this.writeBook = writeBook;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "groupId")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(name = "studentId",unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @ManyToOne
    @JoinColumn(name = "specialities_id")
    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "writebook_id")
    public WriteBook getWriteBook() {
        return writeBook;
    }

    public void setWriteBook(WriteBook writeBook) {
        this.writeBook = writeBook;
    }
}
