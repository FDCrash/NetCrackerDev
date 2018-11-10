package daomodule.entities;

public class SpecialityEntity extends BaseEntity {
    private String name;
    private FacultyEntity faculty;

    public SpecialityEntity(int id,String name,int facultyId){
        this.setId(id);
        this.name=name;
        faculty=new FacultyEntity(facultyId,"");
    }

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

}

