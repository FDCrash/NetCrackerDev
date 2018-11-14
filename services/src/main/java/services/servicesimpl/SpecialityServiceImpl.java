package services.servicesimpl;

import converters.SpecialityConverter;
import daomodule.dao.daoImpl.SpecialityDAOImpl;
import daomodule.entities.SpecialityEntity;
import dto.SpecialityDTO;
import services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class SpecialityServiceImpl implements CRUDService<SpecialityDTO> {
    private SpecialityDAOImpl specialityDAO;
    private SpecialityConverter specialityConverter;

    public SpecialityServiceImpl(){
        specialityDAO = new SpecialityDAOImpl();
        specialityConverter = new SpecialityConverter();
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
        return specialityDAO.getAll().stream().map(speciality->specialityConverter.convert(speciality)).collect(Collectors.toList());
    }

    @Override
    public SpecialityDTO get(int id) {
        return null;
    }
}
