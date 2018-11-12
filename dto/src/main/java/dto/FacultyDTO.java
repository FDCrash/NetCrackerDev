package dto;

import java.util.ArrayList;
import java.util.List;

public class FacultyDTO extends BaseDTO {
    private String name;
    private List<String> specialities;

    public FacultyDTO(){}

    public FacultyDTO(int id,String name){
        super(id);
        this.name=name;
        this.specialities=new ArrayList<>(specialities);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialities(List<String> specialities) {
        this.specialities = specialities;
    }

    public List<String> getSpecialities() {
        return specialities;
    }
}
