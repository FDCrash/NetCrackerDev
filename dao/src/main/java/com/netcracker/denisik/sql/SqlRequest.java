package com.netcracker.denisik.sql;

public class SqlRequest {
    public static final String GET_ALL_ADMINS = "SELECT users.id,users.role,users.login,users.password,admins.status FROM admins inner join users on admins.id=users.id";
    public static final String GET_ADMIN_BY_ID = "SELECT users.id,users.role,users.login,users.password,admins.status FROM admins inner join users on admins.id=users.id WHERE admins.id=?";
    public static final String GET_ALL_EMPLOYEES = "SELECT users.id,users.role,users.login,users.password,employees.name FROM employees inner join users on employees.id=users.id";
    public static final String GET_EMPLOYEE_BY_ID = "SELECT users.id,users.role,users.login,users.password,employees.name FROM employees inner join users on employees.id=users.id WHERE employees.id=?";
    public static final String GET_ALL_FACULTIES = "SELECT * FROM faculties";
    public static final String GET_FACULTY_BY_ID = "SELECT * FROM faculties WHERE id=?";
    public static final String GET_ALL_SPECIALITIES = "SELECT * FROM specialities";
    public static final String GET_SPECIALITY_BY_ID = "SELECT * FROM specialities WHERE id=?";
    public static final String GET_ALL_SPECIALITIES_BY_FACULTY_ID = "SELECT * FROM specialities WHERE facultyId=?";
    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    public static final String GET_ALL_STUDENTS = "SELECT users.id,users.role,users.login,users.password,students.name,students.studentId,students.groupId,students.specialityId FROM students inner join users on students.id=users.id";
    public static final String GET_WRITEBOOK_BY_STUD_ID = "SELECT semester,subject,mark FROM writebook WHERE id=?";
    public static final String GET_STUDENT_BY_ID = "SELECT users.id,users.role,users.login,users.password,students.name,students.studentId,students.groupId,students.specialityId FROM students inner join users on students.id=users.id WHERE students.id=?";
    public static final String ADD_EMPLOYEE = "INSERT INTO employees (id, name) VALUES (?, ?)";
    public static final String ADD_USER = "INSERT INTO users (id, role,login,password) VALUES (?, ?, ?, ?)";
    public static final String ADD_ADMIN = "INSERT INTO admins (id, status) VALUES (?, ?)";
    public static final String ADD_FACULTY = "INSERT INTO faculties (id, name) VALUES (?, ?)";
    public static final String ADD_SPECIALITY = "INSERT INTO specialities (id, name,facultyId) VALUES (?, ?, ?)";
    public static final String ADD_STUDENT = "INSERT INTO students (id, name,studentId,groupId,specialityId) VALUES (?, ?, ?, ?, ?)";
    public static final String ADD_WRITEBOOK = "INSERT INTO writebook (id, semester,subject,mark) VALUES (?, ?, ?, ?)";

    private SqlRequest() {
    }
}
