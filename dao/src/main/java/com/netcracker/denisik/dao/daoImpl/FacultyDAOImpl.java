package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.dao.AbstractDao;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.sql.Database;
import com.netcracker.denisik.sql.SqlRequest;
import com.netcracker.denisik.storage.FacultyList;
import com.netcracker.denisik.storage.SpecialityList;
import com.netcracker.denisik.storage.StudentList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            connection = Database.getInstance().getConnection();
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
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием(факультет)");
            }
        }
        return facultyEntity;
    }

    @Override
    public List<FacultyEntity> getAll() {
        List<FacultyEntity> list = new ArrayList<>();
        try {
            connection = Database.getInstance().getConnection();
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
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием(факультеты)");
            }
        }
        return list;
    }

    @Override
    public FacultyEntity add(FacultyEntity facultyEntity) {
        FacultyList.getInstance().add(facultyEntity);
        SpecialityList.getInstance().addAll(facultyEntity.getSpecialities());
        return get(facultyEntity.getId());
    }

    @Override
    public FacultyEntity update(FacultyEntity faculty) {
        get(faculty.getId()).setName(faculty.getName());
        return get(faculty.getId());
    }

    @Override
    public void delete(int id) {
        StudentList.getInstance().get().stream()
                .filter(student -> student.getSpecialityEntity().getFaculty().getId() == id)
                .collect(Collectors.toList())
                .forEach(speciality -> speciality.setSpecialityEntity(new SpecialityEntity(0, "Переводится", 0)));
        SpecialityList.getInstance().get().removeAll(get(id).getSpecialities());
        getAll().remove(get(id));
    }

    public FacultyEntity getFacultyBySpeciality(int id){
        FacultyEntity facultyEntity = null;
        try {
            connection = Database.getInstance().getConnection();
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
            try {
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием(факультет)");
            }
        }
        return facultyEntity;
    }
}
