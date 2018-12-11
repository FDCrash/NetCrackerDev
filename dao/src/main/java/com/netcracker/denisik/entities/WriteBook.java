package com.netcracker.denisik.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "writebook")
public class WriteBook {
    private long id;
    private List<Semester> semester;

    public WriteBook(){
        semester =new ArrayList<>();
    }

    public WriteBook(List<Semester> semester) {
        this.semester = semester;
    }

    @Id
    @OneToOne(mappedBy = "writeBook")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "id",cascade =CascadeType.ALL)
    public List<Semester> getSemester() {
        return semester;
    }

    public void setSemester(List<Semester> semester) {
        this.semester = semester;
    }
}
