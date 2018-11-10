package services.servicesimpl;

import daomodule.dao.daoImpl.UserDAOImpl;
import daomodule.entities.UserEntity;
import services.CRUDService;

import java.util.List;

public class UserService implements CRUDService<UserEntity> {
    private UserDAOImpl userDAO;
    private UserEntity userEntity;

    public UserService(){}

    @Override
    public void addNew(UserEntity userEntity) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(UserEntity userEntity, int id) {

    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    @Override
    public UserEntity get(int id) {
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

    public UserEntity readWriteBook(int id){
        return userEntity;
    }

    public UserDAOImpl getAdmins(){
        return userDAO;
    }

    public UserDAOImpl getEmployees(){
        return userDAO;
    }

    public void authentication(String login,String pass){

    }

    public void registration(int id,String login,String pass){

    }

    public boolean checker(int id){
        return false;
    }

    public Enum getRoleForMenu(){return userEntity.getRole();}

}
