package daomodule.entities;

public class EmployeeEntity extends UserEntity {
    private FacultyEntity faculty;
    private String name;

    public EmployeeEntity(int id,Role role,String login,String pass,String name){
        this.setId(id);
        this.setRole(role);
        this.setLogin(login);
        this.setPassword(pass);
        this.name=name;
    }

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }
}
