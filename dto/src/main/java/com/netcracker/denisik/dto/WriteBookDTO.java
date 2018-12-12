package com.netcracker.denisik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteBookDTO {
    private long id;
    private List<SemesterDTO> semesterEntity;
}
