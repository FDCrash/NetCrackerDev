package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.dao.IDao;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.sql.Database;
import com.netcracker.denisik.sql.SqlRequest;
import com.netcracker.denisik.storage.SpecialityList;
import com.netcracker.denisik.storage.StudentList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDAOImpl extends AbstractDao<SpecialityEntity> {
    private static SpecialityDAOImpl instance;

    private SpecialityDAOImpl(){}

    public static SpecialityDAOImpl getInstance(){
        if(instance==null){
            instance = new SpecialityDAOImpl();
        }
        return instance;
    }

    @Override
    public SpecialityEntity get(int id) {
        SpecialityEntity specialityEntity = null;
        try {
            connection = Database.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_SPECIALITY_BY_ID);
            statement.setInt(1,id);
            result = statement.executeQuery();
            while (result.next()) {
                specialityEntity= new SpecialityEntity(result.getInt(1),
                        result.getString(2),
                        FacultyDAOImpl.getInstance().
                                getFacultyBySpeciality(result.getInt(3)));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(специальность)");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием(специальность)");
            }
        }
        return specialityEntity;
    }

    @Override
    public List<SpecialityEntity> getAll() {
        List<SpecialityEntity> list = new ArrayList<>();
        try {
            connection = Database.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_SPECIALITIES);
            result = statement.executeQuery();
            while (result.next()) {
                list.add(new SpecialityEntity(result.getInt(1),
                        result.getString(2),
                        FacultyDAOImpl.getInstance().
                                getFacultyBySpeciality(result.getInt(3))));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(специальности)");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием(специальности)");
            }
        }
        return list;
    }

    @Override
    public SpecialityEntity add(SpecialityEntity speciality) {
        SpecialityList.getInstance().add(speciality);
        FacultyDAOImpl.getInstance().get(speciality.getFaculty().getId()).setSpeciality(speciality);
        return get(speciality.getId());
    }

    @Override
    public SpecialityEntity update(SpecialityEntity speciality) {
        delete(speciality.getId());
        add(speciality);
        return get(speciality.getId());
    }

    @Override
    public void delete(int id) {
        StudentList.getInstance().get().stream()
                .filter(student -> student.getSpecialityEntity().getId()==id)
                .forEach(student-> student.setSpecialityEntity(new SpecialityEntity(0,"Переводится", 0)));
        FacultyDAOImpl.getInstance()
                .get(get(id).getFaculty().getId())
                .getSpecialities()
                .remove(get(id));
        getAll().remove(get(id));
    }

    public List<SpecialityEntity> getAllByFaculty(int id){
        List<SpecialityEntity> list = new ArrayList<>();
        try {
            connection = Database.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_SPECIALITIES_BY_FACULTY_ID);
            statement.setInt(1,id);
            result = statement.executeQuery();
            while (result.next()) {
                list.add(new SpecialityEntity(result.getInt(1),
                        result.getString(2),result.getInt(3)));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(специальности по факультету)");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием(специальности по факультету)");
            }
        }
        return list;
    }
}
