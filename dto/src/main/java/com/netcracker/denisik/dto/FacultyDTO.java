package com.netcracker.denisik.dto;

import java.util.ArrayList;
import java.util.List;

public class FacultyDTO extends BaseDTO {
    private String name;
    private List<SpecialityDTO> specialities;

    public FacultyDTO() {
    }

    public FacultyDTO(long id, String name) {
        super(id);
        this.name = name;
        this.specialities = new ArrayList<>();
    }

    public FacultyDTO(long id, String name, List<SpecialityDTO> specialities) {
        super(id);
        this.name = name;
        this.specialities = new ArrayList<>(specialities);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialities(List<SpecialityDTO> specialities) {
        this.specialities = specialities;
    }

    public List<SpecialityDTO> getSpecialities() {
        return specialities;
    }

    public void setSpeciality(SpecialityDTO speciality) {
        this.specialities.add(speciality);
    }

    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        for (int i = 1; i <= getSpecialities().size(); i++) {
            s.append(i).append(") ").append(specialities.get(i - 1).getName()).append("\n");
        }
        return "Факультет: " + getName() + s;
    }
}
