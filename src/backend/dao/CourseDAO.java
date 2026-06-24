package backend.dao;

import backend.db.DBConnection;
import backend.models.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public void create(Course course) {
        String sql = """
                INSERT INTO Course (
                    course_code,
                    course_name,
                    units
                )
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    course.getCourseCode()
            );

            pstmt.setString(
                    2,
                    course.getCourseName()
            );

            pstmt.setInt(
                    3,
                    course.getUnits()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();

        String sql = "SELECT * FROM Course";

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                Course course = new Course();

                course.setCourseId(
                        rs.getInt("course_id")
                );

                course.setCourseCode(
                        rs.getString("course_code")
                );

                course.setCourseName(
                        rs.getString("course_name")
                );

                course.setUnits(
                        rs.getInt("units")
                );

                courses.add(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public Course getById(int courseId) {

        String sql = """
                SELECT *
                FROM Course
                WHERE course_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, courseId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Course course = new Course();

                course.setCourseId(
                        rs.getInt("course_id")
                );

                course.setCourseCode(
                        rs.getString("course_code")
                );

                course.setCourseName(
                        rs.getString("course_name")
                );

                course.setUnits(
                        rs.getInt("units")
                );

                return course;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Course course) {

        String sql = """
                UPDATE Course
                SET
                    course_code = ?,
                    course_name = ?,
                    units = ?
                WHERE course_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    course.getCourseCode()
            );

            pstmt.setString(
                    2,
                    course.getCourseName()
            );

            pstmt.setInt(
                    3,
                    course.getUnits()
            );

            pstmt.setInt(
                    4,
                    course.getCourseId()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int courseId) {

        String sql = """
                DELETE FROM Course
                WHERE course_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, courseId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}