package com.netcracker.denisik.dto;

public class StudentDTO {
    private long id;
    private int studentId;
    private int groupId;
    private SpecialityDTO speciality;
    private WriteBookDTO writeBook;

    public StudentDTO() {
    }

    public StudentDTO(long id, int studentId, int groupId, SpecialityDTO speciality, WriteBookDTO writeBook) {
        this.id = id;
        this.studentId = studentId;
        this.groupId = groupId;
        this.speciality = speciality;
        this.writeBook = writeBook;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setSpeciality(SpecialityDTO speciality) {
        this.speciality = speciality;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public WriteBookDTO getWriteBook() {
        return writeBook;
    }

    public void setWriteBook(WriteBookDTO writeBook) {
        this.writeBook = writeBook;
    }

    public SpecialityDTO getSpeciality() {
        return speciality;
    }

    public int getGroupId() {
        return groupId;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        return   "Специальность: " + getSpeciality().getName() +
                "\nНомер студенченского билета: " + getStudentId() +
                "\nНомер группы:" + getGroupId() + "\nЗачетная книжка: " + writeBook.toString();
    }
}
