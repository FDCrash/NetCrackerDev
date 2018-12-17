package com.netcracker.denisik.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class StudentDTO extends UserDTO{
    private long groupId;
    private String speciality;
    private long specialityId;
    private WriteBookDTO writeBook;

    @Builder(builderMethodName = "builderStudent")
    public StudentDTO(UserDTO userDTO, long groupId, String speciality,long specialityId, WriteBookDTO writeBook) {
        super(userDTO.getId(),userDTO.getRoleDTO(),userDTO.getPassword(),userDTO.getLogin(),userDTO.getName());
        this.groupId = groupId;
        this.speciality = speciality;
        this.writeBook = writeBook;
        this.specialityId=specialityId;
    }
}
