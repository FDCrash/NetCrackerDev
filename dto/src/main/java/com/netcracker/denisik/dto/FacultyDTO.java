package com.netcracker.denisik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDTO {
    private long id;
    private String name;
    private List<SpecialityDTO> specialities;
}
