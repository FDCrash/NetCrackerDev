package com.netcracker.denisik.entities;


import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentEntity)) return false;
        if (!super.equals(o)) return false;

        StudentEntity that = (StudentEntity) o;

        if (getStudentId() != that.getStudentId()) return false;
        if (getGroupId() != that.getGroupId()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getSpecialityEntity() != null ? !getSpecialityEntity().equals(that.getSpecialityEntity()) : that.getSpecialityEntity() != null)
            return false;
        return getWriteBook() != null ? getWriteBook().equals(that.getWriteBook()) : that.getWriteBook() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getStudentId();
        result = 31 * result + getGroupId();
        result = 31 * result + (getSpecialityEntity() != null ? getSpecialityEntity().hashCode() : 0);
        result = 31 * result + (getWriteBook() != null ? getWriteBook().hashCode() : 0);
        return result;
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

    public SpecialityEntity getSpecialityEntity() {
        return specialityEntity;
    }

    public void setSpecialityEntity(SpecialityEntity speciality) {
        this.specialityEntity = speciality;
    }


    public WriteBook getWriteBook() {
        return writeBook;
    }

    public void setWriteBook(WriteBook writeBook) {
        this.writeBook = writeBook;
    }
}
