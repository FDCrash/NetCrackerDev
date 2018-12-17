package com.netcracker.denisik.sql;

public class SQLConstant {
    public static final String UPDATE_LOGIN_PASSWORD =
            "UPDATE users SET login = ?1, password=?2 WHERE (`id` = ?3)";
}
