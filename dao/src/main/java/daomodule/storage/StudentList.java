package daomodule.storage;

import daomodule.entities.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class StudentList implements Storage<StudentEntity>{
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

    @Override
    public List<StudentEntity> get() {
        return students;
    }

    @Override
    public void set(List<StudentEntity> students) {
        this.students=students;
    }

    @Override
    public void add(StudentEntity studentEntity) {
        this.students.add(studentEntity);
    }
}
