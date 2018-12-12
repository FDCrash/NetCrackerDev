package com.netcracker.denisik.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "writebook")
public class WriteBook {

    @Id
    @OneToOne(mappedBy = "writeBook")
    private long id;

    @OneToMany(mappedBy = "id",cascade =CascadeType.ALL)
    private List<Semester> semester;
}
