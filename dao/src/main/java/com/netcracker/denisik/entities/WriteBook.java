package com.netcracker.denisik.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WriteBook {
    private List<SemesterEntity> semesterEntity;

    public WriteBook(){
        semesterEntity =new ArrayList<>();
    }

    public WriteBook(List<SemesterEntity> semesterEntity) {
        this.semesterEntity = semesterEntity;
    }

    public WriteBook(WriteBook writeBook){
        this.semesterEntity =writeBook.getSemesterEntity();
    }

    @OneToMany
    public List<SemesterEntity> getSemesterEntity() {
        return semesterEntity;
    }

    public void setSemesterEntity(List<SemesterEntity> semesterEntity) {
        this.semesterEntity = semesterEntity;
    }
}
