package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.SpecialityConverter;
import com.netcracker.denisik.dao.SpecialityRepository;
import com.netcracker.denisik.dto.SpecialityDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SpecialityServiceImpl implements CRUDService<SpecialityDTO> {
    private SpecialityConverter specialityConverter;
    private final SpecialityRepository specialityRepository;

    public SpecialityServiceImpl(SpecialityRepository specialityRepository, SpecialityConverter specialityConverter) {
        this.specialityConverter = specialityConverter;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public void add(SpecialityDTO specialityDTO) {
        specialityRepository.save(specialityConverter.convert(specialityDTO));
    }

    @Override
    public void delete(int id) {
        specialityRepository.delete(id);
    }

    @Override
    public void update(SpecialityDTO specialityDTO) {
        specialityRepository.save(specialityConverter.convert(specialityDTO));
    }

    @Override
    public List<SpecialityDTO> getAll() {
        return StreamSupport.stream(specialityRepository.findAll().spliterator(), false)
                .map(speciality -> specialityConverter.convert(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public SpecialityDTO get(int id) {
        return specialityConverter.convert(specialityRepository.findOne(id));
    }
}
