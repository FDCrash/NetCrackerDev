package daomodule.dao.daoImpl;

import daomodule.dao.DAO;
import daomodule.entities.SpecialityEntity;

import java.util.List;
import java.util.Optional;

public class SpecialityDAOImpl implements DAO<SpecialityEntity> {
    private List<SpecialityEntity> specialities;

    public SpecialityDAOImpl(){}

    @Override
    public Optional<SpecialityEntity> get(SpecialityEntity specialityEntity) {
        return Optional.empty();
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
