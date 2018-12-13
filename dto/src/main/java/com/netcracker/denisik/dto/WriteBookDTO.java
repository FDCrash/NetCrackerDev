package com.netcracker.denisik.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class WriteBookDTO extends  BaseDTO{
    private List<SemesterDTO> semester;
    private boolean budjet;

    @Builder
    public WriteBookDTO(long id, List<SemesterDTO> semester, boolean budjet) {
        super(id);
        this.semester = semester;
        this.budjet = budjet;
    }
}
