package com.netcracker.denisik.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class SemesterDTO {
    private int mark;
    private SubjectDTO subject;

    @Builder
    public SemesterDTO( int mark, SubjectDTO subject) {
        this.mark = mark;
        this.subject = subject;
    }
}