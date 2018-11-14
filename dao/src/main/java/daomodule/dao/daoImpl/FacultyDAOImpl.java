package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.FacultyEntity;
import daomodule.storage.FacultyList;

import java.util.List;

public class FacultyDAOImpl implements DAO<FacultyEntity> {

    public FacultyDAOImpl(){}


    @Override
    public FacultyEntity get(int id) {
        return FacultyList.getInstance().get().get(id);
    }

    @Override
    public List<FacultyEntity> getAll() {
        return FacultyList.getInstance().get();
    }

    @Override
    public void add(FacultyEntity facultyEntity) {
        FacultyList.getInstance().add(facultyEntity);
    }

    @Override
    public void update(FacultyEntity faculty) {
        for(FacultyEntity facultyEntity:getAll()){
            if(faculty.getId()==facultyEntity.getId()){
                facultyEntity.setName(faculty.getName());
                facultyEntity.setSpecialities(faculty.getSpecialities());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        FacultyList.getInstance().get().remove(id);
    }
}
