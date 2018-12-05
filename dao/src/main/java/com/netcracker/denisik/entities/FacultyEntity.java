package com.netcracker.denisik.entities;

import java.util.ArrayList;
import java.util.List;

public class FacultyEntity extends BaseEntity {
    private String name;
    private List<SpecialityEntity> specialities;

    public FacultyEntity() {
    }

    public FacultyEntity(int id, String name) {
        super(id);
        this.name = name;
        specialities = new ArrayList<>();
    }

    public FacultyEntity(int id, String name, List<SpecialityEntity> specialities) {
        super(id);
        this.name = name;
        this.specialities = new ArrayList<>(specialities);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpecialities(List<SpecialityEntity> specialities) {
        this.specialities = specialities;
    }

    public void setSpeciality(SpecialityEntity speciality) {
        this.specialities.add(speciality);
    }

    public List<SpecialityEntity> getSpecialities() {
        return specialities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyEntity)) return false;
        if (!super.equals(o)) return false;

        FacultyEntity that = (FacultyEntity) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getSpecialities() != null ? getSpecialities().equals(that.getSpecialities()) : that.getSpecialities() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSpecialities() != null ? getSpecialities().hashCode() : 0);
        return result;
    }
}
