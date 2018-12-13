package com.netcracker.denisik.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class SemesterDTO extends BaseDTO {
    private int mark;
    private String subject;

    @Builder
    public SemesterDTO(long id, int mark, String subject) {
        super(id);
        this.mark = mark;
        this.subject = subject;
    }
}