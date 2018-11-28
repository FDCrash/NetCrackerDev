package com.netcracker.denisik.entities;


import java.util.ArrayList;
import java.util.List;

public class StudentEntity extends UserEntity {
    private String name;
    private int studentId;
    private int groupId;
    private SpecialityEntity specialityEntity;
    private List<WriteBook> writeBook;

    public StudentEntity() {
    }

    public StudentEntity(UserEntity userEntity, String name, int studentId,
                         int groupId) {
        super(userEntity);
        this.name = name;
        this.studentId = studentId;
        this.groupId = groupId;
        this.specialityEntity= new SpecialityEntity();
        this.writeBook = new ArrayList<>();
    }

    public StudentEntity(UserEntity userEntity, String name, int studentId,
                         int groupId,SpecialityEntity specialityEntity, List<WriteBook> writeBook) {
        super(userEntity);
        this.name = name;
        this.studentId = studentId;
        this.groupId = groupId;
        this.specialityEntity= specialityEntity;
        this.writeBook = new ArrayList<>(writeBook);
    }

    public StudentEntity(UserEntity userEntity, String name, int studentId,
                         int groupId, int specialityId, List<WriteBook> writeBook) {
        super(userEntity);
        this.name = name;
        this.studentId = studentId;
        this.groupId = groupId;
        this.specialityEntity = new SpecialityEntity(specialityId, "Переводится", 0);
        this.writeBook = new ArrayList<>(writeBook);
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

    public SpecialityEntity getSpecialityEntity() {
        return specialityEntity;
    }

    public void setSpecialityEntity(SpecialityEntity speciality) {
        this.specialityEntity = speciality;
    }

    public void setWriteBook(List<WriteBook> writeBook) {
        this.writeBook = writeBook;
    }

    public List<WriteBook> getWriteBook() {
        return writeBook;
    }

}
