package com.netcracker.denisik.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class FacultyDTO extends BaseDTO{
    private String name;
    private List<SpecialityDTO> specialities;

    @Builder
    public FacultyDTO(long id, String name, List<SpecialityDTO> specialities) {
        super(id);
        this.name = name;
        this.specialities = specialities;
    }
}
