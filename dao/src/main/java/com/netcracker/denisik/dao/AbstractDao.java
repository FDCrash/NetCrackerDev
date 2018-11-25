package com.netcracker.denisik.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AbstractDao<T> implements IDao<T> {
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet result;

}
