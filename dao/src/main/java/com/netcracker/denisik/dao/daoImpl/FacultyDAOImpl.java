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

public class FacultyDAOImpl extends AbstractDao<FacultyEntity> {
    private static FacultyDAOImpl instance;

    private FacultyDAOImpl() {
    }

    public static FacultyDAOImpl getInstance() {
        if (instance == null) {
            instance = new FacultyDAOImpl();
        }
        return instance;
    }


    @Override
    public FacultyEntity get(int id) {
        FacultyEntity facultyEntity = null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_FACULTY_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                facultyEntity = new FacultyEntity(result.getInt(1),
                        result.getString(2),
                        SpecialityDAOImpl.getInstance().getAllByFaculty(id));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(факультет)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return facultyEntity;
    }

    @Override
    public List<FacultyEntity> getAll() {
        List<FacultyEntity> list = new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_FACULTIES);
            result = statement.executeQuery();
            while (result.next()) {
                list.add(new FacultyEntity(result.getInt(1),
                        result.getString(2),
                        SpecialityDAOImpl.getInstance().
                                getAllByFaculty(result.getInt(1))));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(факультеты)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return list;
    }

    @Override
    public FacultyEntity add(FacultyEntity facultyEntity) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.ADD_FACULTY);
            statement.setInt(1, facultyEntity.getId());
            statement.setString(2, facultyEntity.getName());
            statement.executeUpdate();
            facultyEntity.getSpecialities()
                    .forEach(specialityEntity -> SpecialityDAOImpl.getInstance()
                            .add(new SpecialityEntity(specialityEntity.getId(), specialityEntity.getName()
                                    , facultyEntity.getId())));
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(факультет)");
        } finally {
            ClosingUtil.close(statement);
        }
        return get(facultyEntity.getId());
    }

    @Override
    public FacultyEntity update(FacultyEntity faculty) {
        get(faculty.getId()).setName(faculty.getName());
        return get(faculty.getId());
    }

    @Override
    public void delete(int id) {
        try {
            get(id).getSpecialities()
                    .forEach(specialityEntity -> SpecialityDAOImpl.getInstance().delete(specialityEntity.getId()));
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_FACULTY_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с удалением из бд(факультет)");
        } finally {
            ClosingUtil.close(statement);
        }
    }

    public FacultyEntity getFacultyBySpeciality(int id) {
        FacultyEntity facultyEntity = null;
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_FACULTY_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                facultyEntity = new FacultyEntity(result.getInt(1),
                        result.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с бд(факультет)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return facultyEntity;
    }
}
