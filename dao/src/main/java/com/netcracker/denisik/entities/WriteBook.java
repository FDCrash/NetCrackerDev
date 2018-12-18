package com.netcracker.denisik.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "writebook")
public class WriteBook extends BaseEntity {

    @OneToMany(mappedBy = "writeBook",cascade =CascadeType.ALL)
    private List<Semester> semesters;

    @Column(name = "budget")
    private boolean budget;

    @OneToOne(mappedBy = "writeBook")
    private Student student;

    @Builder
    public WriteBook(long id, List<Semester> semesters, boolean budget, Student student) {
        super(id);
        this.semesters = semesters;
        this.budget = budget;
        this.student = student;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int)getId();
        result = 31 * result + getSemesters().hashCode();
        result = 31 * result + (isBudget() ? 1 : 0);
        result = 31 * result + (getStudent() != null ? (int)getStudent().getGroupId() : 0);
        return result;
    }
}
