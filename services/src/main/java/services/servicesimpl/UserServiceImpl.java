package services.servicesimpl;

import converters.AdminConverter;
import converters.UserConverter;
import daomodule.dao.daoImpl.StudentDAOImpl;
import daomodule.dao.daoImpl.UserDAOImpl;
import daomodule.entities.UserEntity;
import dto.UserDTO;
import services.CRUDService;

import java.util.List;
import java.util.SplittableRandom;

public class UserServiceImpl implements CRUDService<UserDTO> {
    private UserDAOImpl userDAO;
    private AdminConverter adminConverter;
    private UserConverter userConverter;
    private StudentDAOImpl studentDAO;

    public UserServiceImpl(){
        userDAO= new UserDAOImpl();
        userConverter = new UserConverter();
        studentDAO = new StudentDAOImpl();
    }

    @Override
    public void addNew(UserDTO userDTO) {
        UserEntity userEntity= userConverter.convert(userDTO);
    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(UserDTO userDTO) {

    }

    @Override
    public List<UserDTO> getAll() {
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

    public int generateId(int bound){
            SplittableRandom splittableRandom=new SplittableRandom();
            int k;
            do{
                k=splittableRandom.nextInt(1,bound);

            }while (!userDAO.checkId(k));
            return k;
    }

    public String registration(int id,String login,String pass){
        if(studentDAO.checkStudId(id)){
            if(userDAO.checkLogin(login)){
                studentDAO.addNewLoginPass(id,login,pass);
                return "Вы успешно зарегистрированы!";
            }
            return "Логин занят!";
        }
        return "Неверный номер стеденченского билета!";
    }

    public Enum authentication(String login, String pass){
        return userDAO.checkLoginPass(login,pass);
    }


}
