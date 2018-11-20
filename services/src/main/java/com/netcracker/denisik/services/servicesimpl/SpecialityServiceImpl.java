package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.converters.SpecialityConverter;
import com.netcracker.denisik.dao.daoImpl.SpecialityDAOImpl;

import com.netcracker.denisik.dto.SpecialityDTO;

import java.util.List;

public class SpecialityServiceImpl implements CRUDService<SpecialityDTO> {
    private SpecialityDAOImpl specialityDAO;
    private SpecialityConverter specialityConverter;

    public SpecialityServiceImpl(){

    }

    @Override
    public void addNew(SpecialityDTO specialityDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(SpecialityDTO specialityDTO) {

    }

    @Override
    public List<SpecialityDTO> getAll() {
        return null;
    }

    @Override
    public SpecialityDTO get(int id) {
        return null;
    }
}
