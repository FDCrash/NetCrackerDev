package com.netcracker.denisik.dto;

import java.util.ArrayList;
import java.util.List;

public class WriteBookDTO {
    private int sem;
    private List<String> subjects;
    private List<Integer> marks;

    public WriteBookDTO(){}

    public WriteBookDTO(int sem, List<String> subjects, List<Integer> marks) {
        this.sem = sem;
        this.subjects = new ArrayList<>(subjects);
        this.marks = new ArrayList<>(marks);
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMarks(List<Integer> marks) {
        this.marks = marks;
    }

    public int getSem() {

        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String toString(){
        StringBuilder s= new StringBuilder("Семестр: " + getSem() + "\n");
        for(int i=0 ; i< getSubjects().size();i++){
            s.append("Предмет: ").append(getSubjects().get(i)).append(";  Оценка: ").append(getMarks().get(i)).append("\n");
        }
        return s.toString();
    }
}
