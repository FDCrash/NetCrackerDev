package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.SpecialityConverter;
import com.netcracker.denisik.dao.SpecialityRepository;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SpecialityServiceImpl implements CRUDService<SpecialityDTO> {
    private SpecialityConverter specialityConverter;
    private SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityServiceImpl(SpecialityRepository specialityRepository, SpecialityConverter specialityConverter) {
        this.specialityConverter = specialityConverter;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public long add(SpecialityDTO specialityDTO) {
        return specialityRepository.save(specialityConverter.convert(specialityDTO)).getId();
    }

    @Override
    public void delete(long id) {
        specialityRepository.delete(id);
    }

    @Override
    public List<SpecialityDTO> getAll() {
        return StreamSupport.stream(specialityRepository.findAll().spliterator(), false)
                .map(speciality -> specialityConverter.convert(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public SpecialityDTO get(long id) {
        return specialityConverter.convert(specialityRepository.findOne(id));
    }

    public SpecialityDTO getByName(String name){
        return specialityConverter.convert(specialityRepository.getByName(name));
    }
}
