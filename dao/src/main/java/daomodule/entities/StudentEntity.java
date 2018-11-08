package daomodule.entities;

import java.util.ArrayList;
import java.util.List;

public class StudentEntity extends UserEntity{
    private String name;
    private int studentId;
    private int groupId;
    private SpecialityEntity specialityEntity;
    private List<Integer> writeBook ;

    public StudentEntity(int id,Role role,String login, String pass,String name,int studentId,int groupId, List<Integer> writeBook) {
        this.setId(id);
        this.setRole(role);
        this.setLogin(login);
        this.setPassword(pass);
        this.name=name;
        this.studentId=studentId;
        this.groupId=groupId;
        this.writeBook=new ArrayList<>(writeBook);
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int gtoupId) {
        this.groupId = gtoupId;
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

    public void setWriteBook(List<Integer> writeBook) {
        this.writeBook = writeBook;
    }

    public List<Integer> getWriteBook() {
        return writeBook;
    }
}