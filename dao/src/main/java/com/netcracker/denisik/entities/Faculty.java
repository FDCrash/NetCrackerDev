package com.netcracker.denisik.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "faculties")
public class Faculty extends BaseEntity{

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "faculty",cascade = CascadeType.ALL)
    private List<Speciality> specialities;

    @Builder
    public Faculty(long id, String name, List<Speciality> specialities) {
        super(id);
        this.name = name;
        this.specialities = specialities;
    }
}
