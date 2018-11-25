package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.StudentConverter;
import com.netcracker.denisik.dao.daoImpl.StudentDAOImpl;
import com.netcracker.denisik.dao.daoImpl.UserDAOImpl;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements CRUDService<StudentDTO> {
    private StudentConverter studentConverter;
    private static StudentServiceImpl instance;

    private StudentServiceImpl(){
        studentConverter = new StudentConverter();
    }

    public static StudentServiceImpl getInstance(){
        if(instance == null){
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    @Override
    public void addNew(StudentDTO studentDTO) {
        StudentDAOImpl.getInstance().add(studentConverter.convert(studentDTO));
    }

    @Override
    public void deleteInfo(int id) {
        StudentDAOImpl.getInstance().delete(id);
    }

    public StudentDTO getByLogin(String login) {
        return studentConverter.convert(StudentDAOImpl.getInstance().getByLogin(login));
    }

    public List<StudentDTO> getAllByGroup(int number) {
        return StudentDAOImpl.getInstance().getAllByGroup(number)
                .stream().map(student -> studentConverter.convert(student)).collect(Collectors.toList());
    }

    public List<StudentDTO> getAllBySpeciality(String speciality) {
        return StudentDAOImpl.getInstance().getAllBySpeciality(speciality).
                stream().map(student -> studentConverter.convert(student)).collect(Collectors.toList());
    }

    @Override
    public void updateInfo(StudentDTO studentDTO) {
        StudentDAOImpl.getInstance().update(studentConverter.convert(studentDTO));
    }


    @Override
    public List<StudentDTO> getAll() {
        return StudentDAOImpl.getInstance().getAll().stream().map(student -> studentConverter.convert(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDTO get(int id) {
        return studentConverter.convert(StudentDAOImpl.getInstance().get(id));
    }
}
