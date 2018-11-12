package dto;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO extends UserDTO {
    private String name;
    private int studentId;
    private int groupId;
    private String speciality;
    private String faculty;
    private List<Integer> writeBook ;

    public StudentDTO(){}

    public StudentDTO(int id, RoleDTO roleDTO, String login, String pass, String name,
                      int studentId, int groupId, String speciality, String faculty, List<Integer> writeBook) {
        super(id, roleDTO,login,pass);
        this.name=name;
        this.studentId=studentId;
        this.groupId=groupId;
        this.speciality=speciality;
        this.faculty=faculty;
        this.writeBook=new ArrayList<>(writeBook);
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

    public List<Integer> getWriteBook() {
        return writeBook;
    }

    public void setWriteBook(List<Integer> writeBook) {
        this.writeBook = writeBook;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getFaculty() {
        return faculty;
    }
}
