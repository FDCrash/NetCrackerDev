package com.netcracker.denisik.entities;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {
    private long id;
    private String subject;

    public Subject(String subject) {
        this.subject = subject;
    }

    public Subject(Long id, String subject) {
        this.id = id;
        this.subject = subject;
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

    @Column(name = "subject",unique = true)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
