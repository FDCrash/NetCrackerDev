package daomodule.storage;

import daomodule.entities.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private static StudentList instance;
    private List<StudentEntity> students=new ArrayList<>();

    private StudentList(){
    }

    public static StudentList getInstance() {
        if(instance==null){
            instance=new StudentList();
        }
        return instance;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students=students;
    }
}
