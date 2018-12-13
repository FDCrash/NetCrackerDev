package com.netcracker.denisik.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class SpecialityDTO extends BaseDTO{
    private String name;
    private FacultyDTO faculty;

    @Builder
    public SpecialityDTO(long id, String name, FacultyDTO faculty) {
        super(id);
        this.name = name;
        this.faculty = faculty;
    }
}
