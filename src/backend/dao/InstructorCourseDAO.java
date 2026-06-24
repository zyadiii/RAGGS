package backend.dao;

import backend.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InstructorCourseDAO {

    public void assignCourse(
            int instructorId,
            int courseId
    ) {

        String sql = """
                INSERT INTO InstructorCourse (
                    instructor_id,
                    course_id
                )
                VALUES (?, ?)
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, instructorId);
            pstmt.setInt(2, courseId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCourse(
            int instructorId,
            int courseId
    ) {

        String sql = """
                DELETE FROM InstructorCourse
                WHERE instructor_id = ?
                AND course_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, instructorId);
            pstmt.setInt(2, courseId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAll() {

        String sql = """
                SELECT *
                FROM InstructorCourse
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                System.out.println(
                        "Instructor ID: " +
                        rs.getInt("instructor_id") +
                        " | Course ID: " +
                        rs.getInt("course_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}