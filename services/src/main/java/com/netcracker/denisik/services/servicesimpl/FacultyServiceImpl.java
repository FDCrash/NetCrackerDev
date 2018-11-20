package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.FacultyConverter;
import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.dao.daoImpl.FacultyDAOImpl;

import com.netcracker.denisik.dto.FacultyDTO;

import java.util.List;


public class FacultyServiceImpl implements CRUDService<FacultyDTO> {
    private FacultyDAOImpl facultyDAO;
    private FacultyConverter facultyConverter;

    public FacultyServiceImpl(){

    }

    @Override
    public void addNew(FacultyDTO facultyDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(FacultyDTO facultyDTO) {

    }

    @Override
    public List<FacultyDTO> getAll() {
        return null;
    }

    @Override
    public FacultyDTO get(int id) {

        return null;
    }
}
