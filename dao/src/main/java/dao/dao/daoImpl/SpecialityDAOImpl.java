package dao.dao.daoImpl;

import dao.dao.DAO;
import dao.entities.SpecialityEntity;
import dao.storage.SpecialityList;

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
