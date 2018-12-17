package com.netcracker.denisik.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class StudentFormDTO extends UserFormDTO {
    private long groupId;
    private long specialityId;
    private WriteBookDTO writeBook;

    public StudentFormDTO(long groupId, long specialityId, WriteBookDTO writeBook) {
        this.groupId = groupId;
        this.specialityId = specialityId;
        this.writeBook = writeBook;
    }
}
