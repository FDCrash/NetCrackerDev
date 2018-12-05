package com.netcracker.denisik.entities;

import java.util.ArrayList;
import java.util.List;

public class WriteBook {
    private List<Semester> semester;

    public WriteBook(){
        semester=new ArrayList<>();
    }

    public WriteBook(List<Semester> semester) {
        this.semester = semester;
    }

    public WriteBook(WriteBook writeBook){
        this.semester=writeBook.getSemester();
    }

    public List<Semester> getSemester() {
        return semester;
    }

    public void setSemester(List<Semester> semester) {
        this.semester = semester;
    }
}
