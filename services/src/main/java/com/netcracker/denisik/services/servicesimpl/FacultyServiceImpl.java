package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.FacultyConverter;
import com.netcracker.denisik.dao.daoImpl.FacultyDAOImpl;
import com.netcracker.denisik.dao.daoImpl.SpecialityDAOImpl;
import com.netcracker.denisik.dto.FacultyDTO;
import com.netcracker.denisik.services.CRUDService;

import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

public class FacultyServiceImpl implements CRUDService<FacultyDTO> {
    private FacultyDAOImpl facultyDAO;
    private FacultyConverter facultyConverter;
    private SpecialityDAOImpl specialityDAO;
    private UserServiceImpl userService;

    public FacultyServiceImpl() {
        facultyDAO = new FacultyDAOImpl();
        facultyConverter = new FacultyConverter();
        specialityDAO = new SpecialityDAOImpl();
        userService = new UserServiceImpl();
    }

    @Override
    public void addNew(FacultyDTO facultyDTO) {
        facultyDAO.add(facultyConverter.convert(facultyDTO));
    }

    @Override
    public void deleteInfo(int id) {
        facultyDAO.delete(id);
    }

    @Override
    public void updateInfo(FacultyDTO facultyDTO) {
        facultyDAO.update(facultyConverter.convert(facultyDTO));
    }

    @Override
    public List<FacultyDTO> getAll() {
        return facultyDAO.getAll().stream().map(faculty -> facultyConverter.convert(faculty)).
                collect(Collectors.toList());
    }

    @Override
    public FacultyDTO get(int id) {
        return facultyConverter.convert(facultyDAO.get(id));
    }

    public int generateId(int bound) {
        SplittableRandom splittableRandom = new SplittableRandom();
        int k;
        do {
            k = splittableRandom.nextInt(1, bound);
        } while (facultyDAO.get(k) != null);
        return k;
    }
}
