package backend.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
        "jdbc:sqlite:student.db";

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL);
    }
}