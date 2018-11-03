package daomodule.entities;

import java.util.List;

public class SpecialityEntity extends BaseEntity {
    private String name;
    private FacultyEntity faculty;
    private List<Integer> groupId;

    public SpecialityEntity(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    public List<Integer> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<Integer> groupId) {
        this.groupId = groupId;
    }
}

