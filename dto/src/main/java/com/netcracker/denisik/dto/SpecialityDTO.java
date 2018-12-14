package com.netcracker.denisik.dto;

public class SpecialityDTO extends BaseDTO {
    private String name;
    private FacultyDTO faculty;

    public SpecialityDTO() {
    }

    public SpecialityDTO(long id, String name, FacultyDTO faculty) {
        super(id);
        this.name = name;
        this.faculty = faculty;
    }

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Название специальности: " + getName()
                + "\nФакультет: " + getFaculty().getName() + "\n";
    }
}
