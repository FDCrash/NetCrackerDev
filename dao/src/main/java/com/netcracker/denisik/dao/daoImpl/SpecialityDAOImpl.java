package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.SpecialityEntity;
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
        SpecialityEntity specialityEntity = null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_SPECIALITY_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                specialityEntity = new SpecialityEntity(result.getInt(1),
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
            connection = DatabaseConnector.getInstance().getConnection();
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
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием записи(специальность)");
            }
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
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием удаления в бд(специальность)");
            }
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
                list.add(new SpecialityEntity(result.getInt(1),
                        result.getString(2), result.getInt(3)));
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
