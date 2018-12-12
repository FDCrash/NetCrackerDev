package com.netcracker.denisik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDTO {
    private long id;
    private int sem;
    private int mark;
    private String subjtec;
}