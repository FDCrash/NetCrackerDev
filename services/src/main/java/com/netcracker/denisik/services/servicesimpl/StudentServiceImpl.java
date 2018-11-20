package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.StudentConverter;
import com.netcracker.denisik.services.CRUDService;
import com.netcracker.denisik.dao.daoImpl.StudentDAOImpl;
import com.netcracker.denisik.dto.StudentDTO;

import java.util.List;

public class StudentServiceImpl implements CRUDService<StudentDTO> {
    private StudentDAOImpl studentDAO;
    private StudentConverter studentConverter;

    public StudentServiceImpl(){

    }

    public List<Integer> getMarksOfControl(int id){return null;}

    public void setMarksOfControl(List<Integer> marks){}

    @Override
    public void addNew(StudentDTO studentDTO) {

    }

    @Override
    public void deleteInfo(int id) {

    }

    @Override
    public void updateInfo(StudentDTO studentDTO) {

    }


    @Override
    public List<StudentDTO> getAll() {
        return null;
    }

    @Override
    public StudentDTO get(int id) {
        return null;
    }
}
