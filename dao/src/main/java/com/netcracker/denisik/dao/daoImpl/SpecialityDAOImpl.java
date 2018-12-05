package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.sql.ClosingUtil;
import com.netcracker.denisik.sql.DatabaseConnector;
import com.netcracker.denisik.sql.SqlRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDAOImpl extends AbstractDao<SpecialityEntity> {
    private static SpecialityDAOImpl instance;

    private SpecialityDAOImpl() {
    }

    public static SpecialityDAOImpl getInstance() {
        if (instance == null) {
            instance = new SpecialityDAOImpl();
        }
        return instance;
    }

    @Override
    public SpecialityEntity get(int id) {
        SpecialityEntity specialityEntity = new SpecialityEntity();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_SPECIALITY_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                specialityEntity.setId(result.getInt(1));
                specialityEntity.setName(result.getString(2));
                specialityEntity.setFaculty(FacultyDAOImpl.getInstance().
                        getFacultyBySpeciality(result.getInt(3)));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(специальность)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return specialityEntity;
    }

    @Override
    public List<SpecialityEntity> getAll() {
        List<SpecialityEntity> list = new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_SPECIALITIES);
            result = statement.executeQuery();
            while (result.next()) {
                SpecialityEntity specialityEntity=new SpecialityEntity();
                specialityEntity.setId(result.getInt(1));
                specialityEntity.setName(result.getString(2));
                specialityEntity.setFaculty(FacultyDAOImpl.getInstance().
                        getFacultyBySpeciality(result.getInt(3)));
                list.add(specialityEntity);
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(специальности)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return list;
    }

    @Override
    public SpecialityEntity add(SpecialityEntity specialityEntity) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.ADD_SPECIALITY);
            statement.setInt(1, specialityEntity.getId());
            statement.setString(2, specialityEntity.getName());
            statement.setInt(3, specialityEntity.getFaculty().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(специальность)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return get(specialityEntity.getId());
    }

    @Override
    public SpecialityEntity update(SpecialityEntity speciality) {
        delete(speciality.getId());
        add(speciality);
        return get(speciality.getId());
    }

    @Override
    public void delete(int id) {
        try {
            StudentDAOImpl.getInstance().setDefaultSpeciality(id);
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_SPECIALITY_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с удалением из бд(специальность)");
        } finally {
            ClosingUtil.close(statement);
        }
    }

    public List<SpecialityEntity> getAllByFaculty(int id) {
        List<SpecialityEntity> list = new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_SPECIALITIES_BY_FACULTY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                SpecialityEntity specialityEntity=new SpecialityEntity();
                specialityEntity.setId(result.getInt(1));
                specialityEntity.setName(result.getString(2));
                FacultyEntity facultyEntity=new FacultyEntity();
                facultyEntity.setId(result.getInt(3));
                facultyEntity.setName("");
                specialityEntity.setFaculty(facultyEntity);
                list.add(specialityEntity);
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(специальности по факультету)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return list;
    }
}
