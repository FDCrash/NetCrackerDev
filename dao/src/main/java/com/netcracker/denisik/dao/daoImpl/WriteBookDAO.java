package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.entities.Semester;
import com.netcracker.denisik.entities.WriteBook;
import com.netcracker.denisik.sql.ClosingUtil;
import com.netcracker.denisik.sql.DatabaseConnector;
import com.netcracker.denisik.sql.SqlRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WriteBookDAO {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    private static WriteBookDAO instance;

    private WriteBookDAO() {
    }

    public static WriteBookDAO getInstance() {
        if (instance == null) {
            instance = new WriteBookDAO();
        }
        return instance;
    }

    public WriteBook get(int id) {
        List<Semester> list = new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_WRITEBOOK_BY_STUD_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            int semester;
            List<String> subjects = new ArrayList<>();
            List<Integer> marks = new ArrayList<>();
            result.next();
            semester = result.getInt(1);
            result.previous();
            while (result.next()) {
                if (semester == result.getInt(1)) {
                    subjects.add(result.getString(2));
                    marks.add(result.getInt(3));
                } else {
                    list.add(new Semester(semester, subjects, marks));
                    subjects.clear();
                    marks.clear();
                    semester = result.getInt(1);
                    subjects.add(result.getString(2));
                    marks.add(result.getInt(3));
                }
                if (result.isLast()) {
                    list.add(new Semester(semester, subjects, marks));
                }
            }
        } catch (SQLException e) {
            System.out.println("Не найдено зачетки с бд(зачетки)");
        } finally {
            ClosingUtil.close(statement);
            ClosingUtil.close(result);
        }
        return new WriteBook(list);
    }

    public void add(Semester semester, int id) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.ADD_WRITEBOOK);
            for (int i = 0; i < semester.getMarks().size(); i++) {
                statement.setInt(1, id);
                statement.setInt(2, semester.getSem());
                statement.setString(3, semester.getSubjects().get(i));
                statement.setInt(4, semester.getMarks().get(i));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(зачетка)");
        } finally {
            ClosingUtil.close(statement);
        }
    }

    public void delete(int id) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_WRITEBOOK_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с удалением из бд(зачетка)");
        } finally {
            ClosingUtil.close(statement);
        }
    }
}
