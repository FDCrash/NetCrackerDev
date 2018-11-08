package services.servicesimpl;

import daomodule.dao.daoImpl.FacultyDAOImpl;
import daomodule.dao.daoImpl.SpecialityDAOImpl;
import daomodule.dao.daoImpl.UserDAOImpl;
import daomodule.entities.FacultyEntity;
import daomodule.entities.SpecialityEntity;
import daomodule.entities.UserEntity;


public class SearchInfoService {
    private UserDAOImpl userDAO;
    private SpecialityDAOImpl specialityDAO;
    private FacultyDAOImpl facultyDAO;
    private UserEntity userEntity;
    private SpecialityEntity specialityEntity;
    private FacultyEntity facultyEntity;

    public UserDAOImpl readFullStudentInfo(int id){
        return userDAO;
    }

    public UserDAOImpl readFullEmployeeInfo(int id){
        return userDAO;
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

    public FacultyDAOImpl getInfoFaculty(){
        return facultyDAO;
    }

    public SpecialityDAOImpl getInfoSpeciality(){
        return specialityDAO;
    }

    public String readModule(){
        return "";
    }
}
