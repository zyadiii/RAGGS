package backend.db;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {
    public static void initialize() {
        try (
            Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement()
        ) {
            /*
                ### SAMPLE CODE ###
                stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL
                )
            """);
            */
            
            
            /* TODO: 
            
            Subjects
            
            Courses
            
            Departments
            
                suggest pa kau iba teewoi mwhehe
            */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

