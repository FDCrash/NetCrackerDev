package dto;

public class SpecialityDTO extends BaseDTO {
    private String name;
    private String faculty;

    public SpecialityDTO(){}

    public SpecialityDTO(int id, String name, String faculty){
        super(id);
        this.name=name;
        this.faculty=faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return "Название специальности " + getName()
                + "\nФакультет: " + getFaculty() + "\n";
    }
}
