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
    }

    @Override
    public void update(SpecialityEntity speciality) {
    }

    @Override
    public void delete(int id) {
    }
}
