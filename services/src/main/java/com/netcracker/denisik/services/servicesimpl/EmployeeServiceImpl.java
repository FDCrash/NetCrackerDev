package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.EmployeeConverter;
import com.netcracker.denisik.dao.EmployeeRepository;
import com.netcracker.denisik.dto.EmployeeDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class EmployeeServiceImpl implements CRUDService<EmployeeDTO> {
    private EmployeeConverter employeeConverter;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        employeeConverter = new EmployeeConverter();
        this.employeeRepository= employeeRepository;
    }

    @Override
    public void add(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeConverter.convert(employeeDTO));
    }

    @Override
    public void delete(int id) {
        employeeRepository.delete(id);
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeConverter.convert(employeeDTO));
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(),false)
                .map(employee -> employeeConverter.convert(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO get(int id) {
        return employeeConverter.convert(employeeRepository.findOne(id));
    }
}
