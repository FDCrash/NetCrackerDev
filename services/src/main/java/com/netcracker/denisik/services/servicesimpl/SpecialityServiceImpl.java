package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.SpecialityConverter;
import com.netcracker.denisik.dao.daoImpl.SpecialityDAOImpl;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

public class SpecialityServiceImpl implements CRUDService<SpecialityDTO> {
    private SpecialityDAOImpl specialityDAO;
    private SpecialityConverter specialityConverter;

    public SpecialityServiceImpl() {
        specialityDAO = new SpecialityDAOImpl();
        specialityConverter = new SpecialityConverter();
    }

    @Override
    public void addNew(SpecialityDTO specialityDTO) {
        specialityDAO.add(specialityConverter.convert(specialityDTO));

    }

    @Override
    public void deleteInfo(int id) {
        specialityDAO.delete(id);
    }

    @Override
    public void updateInfo(SpecialityDTO specialityDTO) {
        specialityDAO.update(specialityConverter.convert(specialityDTO));
    }

    @Override
    public List<SpecialityDTO> getAll() {
        return specialityDAO.getAll().stream()
                .map(speciality -> specialityConverter.convert(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public SpecialityDTO get(int id) {
        return specialityConverter.convert(specialityDAO.get(id));
    }

    public int generateId(int bound) {
        SplittableRandom splittableRandom = new SplittableRandom();
        int k;
        do {
            k = splittableRandom.nextInt(1, bound);

        } while (specialityDAO.get(k) != null);
        return k;
    }
}
