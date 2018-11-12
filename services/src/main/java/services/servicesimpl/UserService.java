package services.servicesimpl;

import daomodule.dao.daoImpl.SystemDAO;
import daomodule.dao.daoImpl.UserDAOImpl;
import daomodule.entities.UserEntity;
import dto.AdminDTO;
import dto.UserDTO;
import services.CRUDService;

import java.util.List;

public class UserService implements CRUDService<UserDTO> {
    private UserDAOImpl userDAO;
    private SystemDAO systemDAO;

    public UserService(){
        systemDAO=new SystemDAO();
        userDAO= new UserDAOImpl();
    }

    @Override
    public void addNew(UserDTO userDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(UserDTO userDTO, int id) {

    }

    @Override
    public List<UserDTO> getAll() {
        return null;
    }


    public List<AdminDTO> getAllAdmins(){
        userDAO.getAdmins();
        return null;
    }

    @Override
    public UserDTO get(int id) {
        return null;
    }

    public UserDAOImpl getInfoByName(String name){
        return userDAO;
    }

    public UserDAOImpl getInfoByGroup(int groupId){
        return userDAO;
    }

    public UserDAOImpl getInfoByFaculty(String faculty){
        return userDAO;
    }

    public UserDAOImpl getInfoBySpeciality(String speciality){
        return userDAO;
    }

    public UserEntity readWriteBook(int id) {
        return null;
    }

    public String registration(int id,String login,String pass){
        if(systemDAO.checkStudId(id)){
            if(systemDAO.checkLogin(login)){
                systemDAO.addNewLoginPass(id,login,pass);
                return "Вы успешно зарегистрированы!";
            }
            return "Логин занят!";
        }
        return "Неверный номер стеденченского билета!";
    }

    public Enum authentication(String login, String pass){
        return systemDAO.checkLoginPass(login,pass);
    }

    public void changeStatusAdmin(String login){
        systemDAO.changeStatus(login);
    }
}
