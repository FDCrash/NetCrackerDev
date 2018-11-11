package daomodule.storage;

import daomodule.entities.FacultyEntity;

import java.util.ArrayList;
import java.util.List;

public class FacultyList {
    private static FacultyList instance;
    private List<FacultyEntity> faculties=new ArrayList<>();

    private FacultyList(){
    }

    public static FacultyList getInstance() {
        if(instance==null){
            instance=new FacultyList();
        }
        return instance;
    }

    public void setFaculties(List<FacultyEntity> faculties) {
        this.faculties = faculties;
    }

    public List<FacultyEntity> getFaculties() {
        return faculties;
    }
}
