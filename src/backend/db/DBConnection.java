package backend.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection connect() throws Exception {
        return DriverManager.getConnection("jdbc:sqlite:student.db");
    }
}