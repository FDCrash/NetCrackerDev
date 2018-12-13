package com.netcracker.denisik.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class StudentDTO extends UserDTO{
    private int groupId;
    private SpecialityDTO speciality;
    private WriteBookDTO writeBook;

    @Builder(builderMethodName = "builderStudent")
    public StudentDTO(UserDTO userDTO, int groupId, SpecialityDTO speciality, WriteBookDTO writeBook) {
        super(userDTO.getId(),userDTO.getRoleDTO(),userDTO.getPassword(),userDTO.getLogin(),userDTO.getName());
        this.groupId = groupId;
        this.speciality = speciality;
        this.writeBook = writeBook;
    }
}
