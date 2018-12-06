package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.SpecialityConverter;
import com.netcracker.denisik.dao.daoImpl.SpecialityDAOImpl;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

public class SpecialityServiceImpl implements CRUDService<SpecialityDTO> {
    private SpecialityConverter specialityConverter;
    private static SpecialityServiceImpl instance;

    private SpecialityServiceImpl(){
        specialityConverter = new SpecialityConverter();
    }

    public static SpecialityServiceImpl getInstance(){
        if(instance == null){
            instance = new SpecialityServiceImpl();
        }
        return instance;
    }

    @Override
    public void addNew(SpecialityDTO specialityDTO) {
        SpecialityDAOImpl.getInstance().add(specialityConverter.convert(specialityDTO));

    }

    @Override
    public void deleteInfo(int id) {
        SpecialityDAOImpl.getInstance().delete(id);
    }

    @Override
    public void updateInfo(SpecialityDTO specialityDTO) {
        SpecialityDAOImpl.getInstance().update(specialityConverter.convert(specialityDTO));
    }

    @Override
    public List<SpecialityDTO> getAll() {
        return SpecialityDAOImpl.getInstance().getAll().stream()
                .map(speciality -> specialityConverter.convert(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public SpecialityDTO get(int id) {
        return specialityConverter.convert(SpecialityDAOImpl.getInstance().get(id));
    }

    public int generateId(int bound) {
        SplittableRandom splittableRandom = new SplittableRandom();
        int k;
        do {
            k = splittableRandom.nextInt(1, bound);

        } while (SpecialityDAOImpl.getInstance().get(k) != null);
        return k;
    }
}
