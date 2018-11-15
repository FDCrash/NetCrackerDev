package dto;

public class UserDTO extends BaseDTO{
    private RoleDTO roleDTO;
    private String password;
    private String login;

    public UserDTO(){}

    public UserDTO(int id, RoleDTO roleDTO, String login, String password){
        super(id);
        this.roleDTO = roleDTO;
        this.password=password;
        this.login=login;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public RoleDTO getRoleDTO(){
        return roleDTO;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return "Логин:" +getLogin() + "\nРоль:"
                + getRoleDTO().name() + "\n";
    }
}
