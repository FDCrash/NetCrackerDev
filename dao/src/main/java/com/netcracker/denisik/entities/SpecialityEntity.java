package com.netcracker.denisik.entities;

public class SpecialityEntity extends BaseEntity {
    private String name;
    private FacultyEntity faculty;

    public SpecialityEntity() {
    }

    public SpecialityEntity(int id, String name, FacultyEntity faculty) {
        super(id);
        this.name = name;
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialityEntity)) return false;
        if (!super.equals(o)) return false;

        SpecialityEntity that = (SpecialityEntity) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getFaculty().equals(that.getFaculty());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getFaculty().hashCode();
        return result;
    }

    public SpecialityEntity(int id, String name, int facultyId) {
        super(id);
        this.name = name;
        faculty = new FacultyEntity(facultyId, "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

}

