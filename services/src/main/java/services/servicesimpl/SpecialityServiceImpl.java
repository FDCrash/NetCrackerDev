package services.servicesimpl;

import converters.SpecialityConverter;
import dao.dao.daoImpl.SpecialityDAOImpl;

import dto.SpecialityDTO;
import services.CRUDService;

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
