package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.DAO;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.storage.SpecialityList;

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
    public SpecialityEntity add(SpecialityEntity specialityEntity) {
        return null;
    }

    @Override
    public SpecialityEntity update(SpecialityEntity specialityEntity) {
        return null;
    }


    @Override
    public void delete(int id) {
    }
}
