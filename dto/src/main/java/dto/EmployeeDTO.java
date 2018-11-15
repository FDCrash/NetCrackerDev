package dto;

public class EmployeeDTO extends UserDTO {
    private String name;

    public EmployeeDTO(){}

    public EmployeeDTO(int id, RoleDTO roleDTO, String login, String pass, String name){
        super(id, roleDTO,login,pass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "Имя: " + getName() + "\nЛогин: " +getLogin() + "\nРоль: "
                + getRoleDTO().name() + "\n";
    }
}
