package backend.dao;

import backend.db.DBConnection;
import backend.models.Enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public void create(Enrollment enrollment) {
        String sql = """
                INSERT INTO Enrollment (
                    enrollment_date,
                    school_year,
                    semester,
                    student_id,
                    course_id
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    enrollment.getEnrollmentDate()
            );

            pstmt.setString(
                    2,
                    enrollment.getSchoolYear()
            );

            pstmt.setString(
                    3,
                    enrollment.getSemester()
            );

            pstmt.setInt(
                    4,
                    enrollment.getStudentId()
            );

            pstmt.setInt(
                    5,
                    enrollment.getCourseId()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Enrollment> getAll() {
        List<Enrollment> enrollments = new ArrayList<>();

        String sql = "SELECT * FROM Enrollment";

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                Enrollment enrollment = new Enrollment();

                enrollment.setEnrollmentId(
                        rs.getInt("enrollment_id")
                );

                enrollment.setEnrollmentDate(
                        rs.getString("enrollment_date")
                );

                enrollment.setSchoolYear(
                        rs.getString("school_year")
                );

                enrollment.setSemester(
                        rs.getString("semester")
                );

                enrollment.setStudentId(
                        rs.getInt("student_id")
                );

                enrollment.setCourseId(
                        rs.getInt("course_id")
                );

                enrollments.add(enrollment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return enrollments;
    }

    public Enrollment getById(int enrollmentId) {

        String sql = """
                SELECT *
                FROM Enrollment
                WHERE enrollment_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, enrollmentId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Enrollment enrollment = new Enrollment();

                enrollment.setEnrollmentId(
                        rs.getInt("enrollment_id")
                );

                enrollment.setEnrollmentDate(
                        rs.getString("enrollment_date")
                );

                enrollment.setSchoolYear(
                        rs.getString("school_year")
                );

                enrollment.setSemester(
                        rs.getString("semester")
                );

                enrollment.setStudentId(
                        rs.getInt("student_id")
                );

                enrollment.setCourseId(
                        rs.getInt("course_id")
                );

                return enrollment;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Enrollment enrollment) {

        String sql = """
                UPDATE Enrollment
                SET
                    enrollment_date = ?,
                    school_year = ?,
                    semester = ?,
                    student_id = ?,
                    course_id = ?
                WHERE enrollment_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    enrollment.getEnrollmentDate()
            );

            pstmt.setString(
                    2,
                    enrollment.getSchoolYear()
            );

            pstmt.setString(
                    3,
                    enrollment.getSemester()
            );

            pstmt.setInt(
                    4,
                    enrollment.getStudentId()
            );

            pstmt.setInt(
                    5,
                    enrollment.getCourseId()
            );

            pstmt.setInt(
                    6,
                    enrollment.getEnrollmentId()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int enrollmentId) {

        String sql = """
                DELETE FROM Enrollment
                WHERE enrollment_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, enrollmentId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int count() {

        String sql = "SELECT COUNT(*) FROM Enrollment";

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {

                if (rs.next()) {
                return rs.getInt(1);
                }

        } catch (Exception e) {
                e.printStackTrace();
        }

        return 0;
        }
}