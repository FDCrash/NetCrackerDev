package com.netcracker.denisik.dao.daoImpl;

import com.netcracker.denisik.entities.WriteBook;
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

    private WriteBookDAO(){}

    public static WriteBookDAO getInstance(){
        if(instance==null){
            instance = new WriteBookDAO();
        }
        return instance;
    }

    public List<WriteBook> get(int id){
        List<WriteBook> list=new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_WRITEBOOK_BY_STUD_ID);
            statement.setInt(1,id);
            result = statement.executeQuery();
            int semester;
            List<String> subjects=new ArrayList<>();
            List<Integer> marks=new ArrayList<>();
            result.next();
            semester=result.getInt(1);
            result.previous();
            while(result.next()){
                if(semester==result.getInt(1)){
                    subjects.add(result.getString(2));
                    marks.add(result.getInt(3));
                }else {
                    list.add(new WriteBook(semester, subjects, marks));
                    subjects.clear();
                    marks.clear();
                    semester=result.getInt(1);
                    subjects.add(result.getString(2));
                    marks.add(result.getInt(3));
                }
                if(result.isLast()){
                    list.add(new WriteBook(semester, subjects, marks));
                }
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(студенты)");
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(студенты)");
            }
        }
        return list;
    }

    public void add(WriteBook writeBook,int id){
        try {
            statement = connection.prepareStatement(SqlRequest.ADD_WRITEBOOK);
            for(int i=0;i<writeBook.getMarks().size();i++){
                statement.setInt(1,id);
                statement.setInt(2,writeBook.getSem());
                statement.setString(3,writeBook.getSubjects().get(i));
                statement.setInt(4,writeBook.getMarks().get(i));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд(сотрудник)");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием записи в бд(сотрудник)");
            }
        }
    }
}
