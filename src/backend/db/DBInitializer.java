package backend.db;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {
    public static void initialize() {
        try (
            Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute("PRAGMA foreign_keys = ON");

            // Department
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Department (
                    department_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    department_name TEXT NOT NULL
                )
            """);

            // Program
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Program (
                    program_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    program_name TEXT NOT NULL,
                    department_id INTEGER NOT NULL,
                    FOREIGN KEY (department_id)
                        REFERENCES Department(department_id)
                )
            """);

            // Course
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Course (
                    course_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    course_code TEXT NOT NULL UNIQUE,
                    course_name TEXT NOT NULL,
                    units INTEGER NOT NULL
                )
            """);

            // Block
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Block (
                    block_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    block_name TEXT NOT NULL,
                    program_id INTEGER NOT NULL,
                    FOREIGN KEY (program_id)
                        REFERENCES Program(program_id)
                )
            """);

            // Student
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Student (
                    student_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    first_name TEXT NOT NULL,
                    middle_name TEXT,
                    last_name TEXT NOT NULL,
                    birth_date DATE,
                    address TEXT,
                    contact_no TEXT,
                    citizenship TEXT,
                    status TEXT,
                    block_id INTEGER,
                    FOREIGN KEY (block_id)
                        REFERENCES Block(block_id)
                )
            """);

            // Instructor
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Instructor (
                    instructor_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    first_name TEXT NOT NULL,
                    middle_name TEXT,
                    last_name TEXT NOT NULL,
                    department_id INTEGER,
                    FOREIGN KEY (department_id)
                        REFERENCES Department(department_id)
                )
            """);

            // ProgramCourse 
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS ProgramCourse (
                    program_id INTEGER NOT NULL,
                    course_id INTEGER NOT NULL,
                    PRIMARY KEY (program_id, course_id),
                    FOREIGN KEY (program_id)
                        REFERENCES Program(program_id),
                    FOREIGN KEY (course_id)
                        REFERENCES Course(course_id)
                )
            """);

            // InstructorCourse 
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS InstructorCourse (
                    instructor_id INTEGER NOT NULL,
                    course_id INTEGER NOT NULL,
                    PRIMARY KEY (instructor_id, course_id),
                    FOREIGN KEY (instructor_id)
                        REFERENCES Instructor(instructor_id),
                    FOREIGN KEY (course_id)
                        REFERENCES Course(course_id)
                )
            """);

            // Enrollment
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Enrollment (
                    enrollment_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    enrollment_date DATE NOT NULL,
                    school_year TEXT NOT NULL,
                    semester INTEGER NOT NULL,
                    student_id INTEGER NOT NULL,
                    course_id INTEGER NOT NULL,
                    FOREIGN KEY (student_id)
                        REFERENCES Student(student_id),
                    FOREIGN KEY (course_id)
                        REFERENCES Course(course_id)
                )
            """);

            // Grade
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Grade (
                    grade_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    final_grade REAL,
                    remarks TEXT,
                    enrollment_id INTEGER NOT NULL UNIQUE,
                    FOREIGN KEY (enrollment_id)
                        REFERENCES Enrollment(enrollment_id)
                )
            """);

            // User
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS User (
                    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL
                )
            """);

            // Default User
            stmt.execute("""
                INSERT OR IGNORE INTO User (
                    username,
                    password
                )
                VALUES (
                    'admin',
                    'admin123'
                )
            """);

            System.out.println("----- Database initialized successfully. -----");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

