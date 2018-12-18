package com.netcracker.denisik.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FacultyDTO extends BaseDTO{
    private String name;
    private List<String> specialities;

    @Builder
    public FacultyDTO(long id, String name, List<String> specialities) {
        super(id);
        this.name = name;
        this.specialities = specialities;
    }
}
