package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.StudentConverter;
import com.netcracker.denisik.dao.daoImpl.StudentDAOImpl;
import com.netcracker.denisik.dao.daoImpl.UserDAOImpl;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements CRUDService<StudentDTO> {
    private StudentDAOImpl studentDAO;
    private StudentConverter studentConverter;
    private UserDAOImpl userDAO;

    public StudentServiceImpl() {
        studentConverter = new StudentConverter();
        studentDAO = new StudentDAOImpl();
        userDAO = new UserDAOImpl();
    }


    @Override
    public void addNew(StudentDTO studentDTO) {
        studentDAO.add(studentConverter.convert(studentDTO));
    }

    @Override
    public void deleteInfo(int id) {
        studentDAO.delete(id);
    }

    public StudentDTO getByLogin(String login) {
        return studentConverter.convert(studentDAO.getByLogin(login));
    }

    public List<StudentDTO> getAllByGroup(int number) {
        return studentDAO.getAllByGroup(number)
                .stream().map(student -> studentConverter.convert(student)).collect(Collectors.toList());
    }

    public List<StudentDTO> getAllBySpeciality(String speciality) {
        return studentDAO.getAllBySpeciality(speciality).
                stream().map(student -> studentConverter.convert(student)).collect(Collectors.toList());
    }

    @Override
    public void updateInfo(StudentDTO studentDTO) {
        studentDAO.update(studentConverter.convert(studentDTO));
    }


    @Override
    public List<StudentDTO> getAll() {
        return studentDAO.getAll().stream().map(student -> studentConverter.convert(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDTO get(int id) {
        return studentConverter.convert(studentDAO.get(id));
    }
}
