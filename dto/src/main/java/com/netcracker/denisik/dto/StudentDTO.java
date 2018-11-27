package com.netcracker.denisik.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO extends UserDTO {
    private String name;
    private int studentId;
    private int groupId;
    private String speciality;
    private List<WriteBookDTO> writeBook;

    public StudentDTO() {
    }

    public StudentDTO(UserDTO userDTO, String name,
                      int studentId, int groupId, String speciality, List<WriteBookDTO> writeBook) {
        super(userDTO);
        this.name = name;
        this.studentId = studentId;
        this.groupId = groupId;
        this.speciality = speciality;
        this.writeBook = new ArrayList<>(writeBook);
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public List<WriteBookDTO> getWriteBook() {
        return writeBook;
    }

    public void setWriteBook(List<WriteBookDTO> writeBook) {
        this.writeBook = writeBook;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int getGroupId() {
        return groupId;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        for (int i = 0; i < getWriteBook().size(); i++) {
            s.append(writeBook.get(i).toString()).append("\n");
        }
        return "Имя: " + getName() + new UserDTO(this).toString() + "\nСпециальность: " + getSpeciality() +
                "\nНомер студенченского билета: " + getStudentId() +
                "\nНомер группы:" + getGroupId() + "\nЗачетная книжка: " + s;
    }
}
