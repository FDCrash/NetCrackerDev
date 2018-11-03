package daomodule.entities;

import java.util.List;

public class StudentEntity extends UserEntity{
    private int groupId;
    private FacultyEntity faculty;
    private String name;
    private int studentId;
    private String email;
    private SpecialityEntity speciality;
    private List<Integer> writeBook;

    public StudentEntity(){
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int gtoupId) {
        this.groupId = gtoupId;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    public FacultyEntity getFaculty() {
        return faculty;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SpecialityEntity getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityEntity speciality) {
        this.speciality = speciality;
    }

    public void setWriteBook(List<Integer> writeBook) {
        this.writeBook = writeBook;
    }

    public List<Integer> getWriteBook() {
        return writeBook;
    }
}
