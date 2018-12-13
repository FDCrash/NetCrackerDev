package com.netcracker.denisik.entities;

import lombok.*;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@NoArgsConstructor
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User{

    @Column(name = "id")
    private long id;

    @Column(name = "groupId")
    private int groupId;

    @ManyToOne
    @JoinColumn(name = "specialities_id")
    private Speciality speciality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "writebook_id")
    private WriteBook writeBook;

    @Builder(builderMethodName = "builderStudent")
    public Student(User user, long id, int groupId, Speciality speciality, WriteBook writeBook) {
        super(id, user.getRole(), user.getPassword(), user.getLogin(), user.getName());
        this.id = id;
        this.groupId = groupId;
        this.speciality = speciality;
        this.writeBook = writeBook;
    }
}
