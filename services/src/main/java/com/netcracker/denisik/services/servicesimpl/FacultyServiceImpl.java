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
    private FacultyConverter facultyConverter;
    private static FacultyServiceImpl instance;

    private FacultyServiceImpl(){
        facultyConverter = new FacultyConverter();
    }

    public static FacultyServiceImpl getInstance(){
        if(instance == null){
            instance = new FacultyServiceImpl();
        }
        return instance;
    }

    @Override
    public void addNew(FacultyDTO facultyDTO) {
        FacultyDAOImpl.getInstance().add(facultyConverter.convert(facultyDTO));
    }

    @Override
    public void deleteInfo(int id) {
        FacultyDAOImpl.getInstance().delete(id);
    }

    @Override
    public void updateInfo(FacultyDTO facultyDTO) {
        FacultyDAOImpl.getInstance().update(facultyConverter.convert(facultyDTO));
    }

    @Override
    public List<FacultyDTO> getAll() {
        return FacultyDAOImpl.getInstance().getAll().stream().map(faculty -> facultyConverter.convert(faculty)).
                collect(Collectors.toList());
    }

    @Override
    public FacultyDTO get(int id) {
        return facultyConverter.convert(FacultyDAOImpl.getInstance().get(id));
    }

    public int generateId(int bound) {
        SplittableRandom splittableRandom = new SplittableRandom();
        int k;
        do {
            k = splittableRandom.nextInt(1, bound);
        } while (FacultyDAOImpl.getInstance().get(k) != null);
        return k;
    }
}
