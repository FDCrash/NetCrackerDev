package com.netcracker.denisik.dto;

public class SemesterDTO extends BaseDTO{
    private int sem;
    private int mark;
    private String subject;

    public SemesterDTO() {
    }

    public SemesterDTO(long id, int sem, int mark, String subject) {
        super(id);
        this.sem = sem;
        this.mark = mark;
        this.subject = subject;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}