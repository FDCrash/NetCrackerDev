package daomodule.entities;

public class EmployeeEntity extends UserEntity {
    private FacultyEntity faculty;
    private String name;

    public EmployeeEntity(){}

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }
}
