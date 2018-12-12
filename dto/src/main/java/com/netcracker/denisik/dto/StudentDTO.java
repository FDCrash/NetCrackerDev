package com.netcracker.denisik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private long id;
    private int studentId;
    private int groupId;
    private SpecialityDTO speciality;
    private WriteBookDTO writeBook;
}
