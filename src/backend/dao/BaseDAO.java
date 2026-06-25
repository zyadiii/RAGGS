package backend.dao;

import backend.db.DBConnection;

import java.sql.Connection;

public abstract class BaseDAO {

    protected Connection getConnection() throws Exception {
        return DBConnection.connect();
    }
}