package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.SpecialityEntity;

import java.util.List;

public class SpecialityDAOImpl implements DAO<SpecialityEntity> {
    private List<SpecialityEntity> specialities;

    public SpecialityDAOImpl(){}

    @Override
    public SpecialityEntity get(int id) {
        return specialities.get(id);
    }

    @Override
    public List<SpecialityEntity> getAll() {
        return specialities;
    }

    @Override
    public void add(SpecialityEntity speciality) {
    }

    @Override
    public void update(SpecialityEntity speciality) {
    }

    @Override
    public void delete(SpecialityEntity speciality) {
    }
}
