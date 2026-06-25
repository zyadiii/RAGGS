package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProgramCourseDAO extends BaseDAO {
    public void assignCourse(
            int programId,
            int courseId
    ) {

        String sql = """
                INSERT INTO ProgramCourse (
                    program_id,
                    course_id
                )
                VALUES (?, ?)
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, programId);
            pstmt.setInt(2, courseId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCourse(
            int programId,
            int courseId
    ) {

        String sql = """
                DELETE FROM ProgramCourse
                WHERE program_id = ?
                AND course_id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, programId);
            pstmt.setInt(2, courseId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAll() {

        String sql = """
                SELECT *
                FROM ProgramCourse
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                System.out.println(
                        "Program ID: "
                                + rs.getInt("program_id")
                                + " | Course ID: "
                                + rs.getInt("course_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}