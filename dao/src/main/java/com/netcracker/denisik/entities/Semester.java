package com.netcracker.denisik.entities;

import javax.persistence.*;

@Entity
@Table(name = "semesters")
public class Semester {
    private long id;
    private int sem;
    private int mark;
    private Subject subject;

    public Semester(){}

    public Semester(long id, int sem, int mark, Subject subject) {
        this.id = id;
        this.sem = sem;
        this.mark = mark;
        this.subject = subject;
    }

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "sem")
    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    @Column(name = "mark")
    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @ManyToOne
    @JoinColumn(name = "subjects_id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
