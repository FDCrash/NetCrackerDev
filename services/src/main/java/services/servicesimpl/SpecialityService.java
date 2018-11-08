package services.servicesimpl;

import daomodule.dao.daoImpl.SpecialityDAOImpl;
import daomodule.entities.SpecialityEntity;
import services.CRUDService;

import java.util.List;

public class SpecialityService implements CRUDService<SpecialityEntity> {
    private SpecialityDAOImpl specialityDAO;
    private SpecialityEntity specialityEntity;


    @Override
    public void addNew(SpecialityEntity specialityEntity) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(SpecialityEntity specialityEntity, int id) {

    }

    @Override
    public List<SpecialityEntity> getAll() {
        return null;
    }

    @Override
    public SpecialityEntity get(int id) {
        return null;
    }
}
