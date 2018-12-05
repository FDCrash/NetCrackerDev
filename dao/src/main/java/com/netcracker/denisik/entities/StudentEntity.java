package com.netcracker.denisik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class StudentEntity extends UserEntity {
    private String name;
    private int studentId;
    private int groupId;
    private SpecialityEntity specialityEntity;
    private WriteBook writeBook;

    public StudentEntity() {
    }

    public StudentEntity(UserEntity userEntity, String name, int studentId,
                         int groupId) {
        super(userEntity);
        this.name = name;
        this.studentId = studentId;
        this.groupId = groupId;
        this.specialityEntity= new SpecialityEntity();
        this.writeBook = new WriteBook();
    }

    public StudentEntity(UserEntity userEntity, String name, int studentId,
                         int groupId, SpecialityEntity specialityEntity, WriteBook writeBook) {
        super(userEntity);
        this.name = name;
        this.studentId = studentId;
        this.groupId = groupId;
        this.specialityEntity= specialityEntity;
        this.writeBook = new WriteBook(writeBook);
    }

    public StudentEntity(UserEntity userEntity, String name, int studentId,
                         int groupId, int specialityId, WriteBook writeBook) {
        super(userEntity);
        this.name = name;
        this.studentId = studentId;
        this.groupId = groupId;
        this.specialityEntity = new SpecialityEntity(specialityId, "Переводится", 0);
        this.writeBook = new WriteBook(writeBook);
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @OneToMany(mappedBy = "students")
    public SpecialityEntity getSpecialityEntity() {
        return specialityEntity;
    }

    public void setSpecialityEntity(SpecialityEntity speciality) {
        this.specialityEntity = speciality;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public WriteBook getWriteBook() {
        return writeBook;
    }

    public void setWriteBook(WriteBook writeBook) {
        this.writeBook = writeBook;
    }
}
