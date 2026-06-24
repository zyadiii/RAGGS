package backend.dao;

import backend.db.DBConnection;
import backend.models.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    public void create(Grade grade) {
        String sql = """
                INSERT INTO Grade (
                    final_grade,
                    remarks,
                    enrollment_id
                )
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setDouble(
                    1,
                    grade.getFinalGrade()
            );

            pstmt.setString(
                    2,
                    grade.getRemarks()
            );

            pstmt.setInt(
                    3,
                    grade.getEnrollmentId()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Grade> getAll() {
        List<Grade> grades = new ArrayList<>();

        String sql = "SELECT * FROM Grade";

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                Grade grade = new Grade();

                grade.setGradeId(
                        rs.getInt("grade_id")
                );

                grade.setFinalGrade(
                        rs.getDouble("final_grade")
                );

                grade.setRemarks(
                        rs.getString("remarks")
                );

                grade.setEnrollmentId(
                        rs.getInt("enrollment_id")
                );

                grades.add(grade);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return grades;
    }

    public Grade getById(int gradeId) {

        String sql = """
                SELECT *
                FROM Grade
                WHERE grade_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, gradeId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Grade grade = new Grade();

                grade.setGradeId(
                        rs.getInt("grade_id")
                );

                grade.setFinalGrade(
                        rs.getDouble("final_grade")
                );

                grade.setRemarks(
                        rs.getString("remarks")
                );

                grade.setEnrollmentId(
                        rs.getInt("enrollment_id")
                );

                return grade;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Grade grade) {

        String sql = """
                UPDATE Grade
                SET
                    final_grade = ?,
                    remarks = ?,
                    enrollment_id = ?
                WHERE grade_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setDouble(
                    1,
                    grade.getFinalGrade()
            );

            pstmt.setString(
                    2,
                    grade.getRemarks()
            );

            pstmt.setInt(
                    3,
                    grade.getEnrollmentId()
            );

            pstmt.setInt(
                    4,
                    grade.getGradeId()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int gradeId) {

        String sql = """
                DELETE FROM Grade
                WHERE grade_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, gradeId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}