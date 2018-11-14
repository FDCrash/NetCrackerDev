package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.SpecialityEntity;
import daomodule.storage.SpecialityList;

import java.util.List;

public class SpecialityDAOImpl implements DAO<SpecialityEntity> {

    @Override
    public SpecialityEntity get(int id) {
        return SpecialityList.getInstance().get().get(id);
    }

    @Override
    public List<SpecialityEntity> getAll() {
        return SpecialityList.getInstance().get();
    }

    @Override
    public void add(SpecialityEntity speciality) {
        SpecialityList.getInstance().add(speciality);
    }

    @Override
    public void update(SpecialityEntity speciality) {
        for(SpecialityEntity specialityEntity: getAll()){
            if(specialityEntity.getId()==speciality.getId()){
                specialityEntity.setName(speciality.getName());
                specialityEntity.setFaculty(speciality.getFaculty());
            }
        }
    }

    @Override
    public void delete(int id) {
        SpecialityList.getInstance().get().remove(id);
    }
}
