package com.netcracker.denisik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialityDTO {
    private long id;
    private String name;
    private FacultyDTO faculty;
}
