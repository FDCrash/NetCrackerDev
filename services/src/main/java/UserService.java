import daomodule.dao.daoImpl.UserDAOImpl;
import daomodule.entities.UserEntity;

public class UserService implements Service<UserEntity>{
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

    public void authentication(String login,String pass){

    }

    public void registration(int id,String login,String pass){

    }

    public boolean checker(int id){
        return false;
    }
}
