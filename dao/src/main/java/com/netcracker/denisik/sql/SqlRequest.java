package com.netcracker.denisik.sql;

public class SqlRequest {
    public static final String GET_ALL_ADMINS="SELECT users.id,users.role,users.login,users.password,admins.status FROM admins inner join users on admins.id=users.id";


    private SqlRequest(){}
}
