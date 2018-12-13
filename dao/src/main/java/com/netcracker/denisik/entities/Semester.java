package com.netcracker.denisik.entities;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "semesters")
public class Semester extends BaseEntity{

    @Column(name = "mark")
    private int mark;

    @ManyToOne
    @JoinColumn(name = "subjects_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "writebook_id")
    private WriteBook writeBook;

    @Builder
    public Semester(long id, int mark, Subject subject, WriteBook writeBook) {
        super(id);
        this.mark = mark;
        this.subject = subject;
        this.writeBook = writeBook;
    }
}
