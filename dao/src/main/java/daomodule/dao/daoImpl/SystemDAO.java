package daomodule.dao.daoImpl;

import daomodule.entities.AdminEntity;
import daomodule.entities.StudentEntity;
import daomodule.entities.UserEntity;
import daomodule.storage.AdminList;
import daomodule.storage.StudentList;
import daomodule.storage.UserList;

public class SystemDAO {
    public boolean checkStudId(int id){
        for (StudentEntity studentEntity: StudentList.getInstance().getStudents()) {
            if(studentEntity.getStudentId()==id){
                return true;
            }
        }
        return false;
    }

    public boolean checkLogin(String login){
        for(UserEntity userEntity: UserList.getInstance().getUsers()){
            if(userEntity.getLogin().equals(login)){
                return false;
            }
        }
        return true;
    }

    public void addNewLoginPass(int id,String login, String pass){
        for (StudentEntity studentEntity: StudentList.getInstance().getStudents()) {
            if(studentEntity.getStudentId()==id){
                studentEntity.setLogin(login);
                studentEntity.setPassword(pass);
            }
        }
    }

    public Enum checkLoginPass(String login,String pass){
        for(UserEntity userEntity: UserList.getInstance().getUsers()){
            if(userEntity.getLogin().equals(login) && userEntity.getPassword().equals(pass)){
                return userEntity.getRole();
            }
        }
        return null;
    }

    public void changeStatus(String login){
        for(AdminEntity adminEntity: AdminList.getInstance().getAdmins()){
            if(adminEntity.getLogin().equals(login)){
                adminEntity.setStatus(true);
            }
        }
    }
}
