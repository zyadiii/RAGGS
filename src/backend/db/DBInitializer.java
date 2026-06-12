package backend.db;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {
    public static void initialize() {
        try (
            Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement()
        ) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    studentNo TEXT UNIQUE NOT NULL,
                    name TEXT NOT NULL,
                    course TEXT NOT NULL,
                    yearLevel INTEGER NOT NULL
                )
            """);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

