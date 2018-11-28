package com.netcracker.denisik.services;

import java.sql.Connection;

public abstract class AbstractService<T> implements CRUDService<T> {
    protected Connection connection;
}
