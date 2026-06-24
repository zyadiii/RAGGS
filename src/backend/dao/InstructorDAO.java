package backend.dao;

import backend.db.DBConnection;
import backend.models.Instructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {

    public void create(Instructor instructor) {

        String sql = """
                INSERT INTO Instructor (
                    first_name,
                    middle_name,
                    last_name,
                    department_id
                )
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    instructor.getFirstName()
            );

            pstmt.setString(
                    2,
                    instructor.getMiddleName()
            );

            pstmt.setString(
                    3,
                    instructor.getLastName()
            );

            pstmt.setInt(
                    4,
                    instructor.getDepartmentId()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Instructor> getAll() {

        List<Instructor> instructors =
                new ArrayList<>();

        String sql = "SELECT * FROM Instructor";

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql);
                ResultSet rs =
                        pstmt.executeQuery()
        ) {

            while (rs.next()) {

                Instructor instructor =
                        new Instructor();

                instructor.setInstructorId(
                        rs.getInt("instructor_id")
                );

                instructor.setFirstName(
                        rs.getString("first_name")
                );

                instructor.setMiddleName(
                        rs.getString("middle_name")
                );

                instructor.setLastName(
                        rs.getString("last_name")
                );

                instructor.setDepartmentId(
                        rs.getInt("department_id")
                );

                instructors.add(instructor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return instructors;
    }

    public Instructor getById(
            int instructorId
    ) {

        String sql = """
                SELECT *
                FROM Instructor
                WHERE instructor_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            pstmt.setInt(
                    1,
                    instructorId
            );

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                Instructor instructor =
                        new Instructor();

                instructor.setInstructorId(
                        rs.getInt("instructor_id")
                );

                instructor.setFirstName(
                        rs.getString("first_name")
                );

                instructor.setMiddleName(
                        rs.getString("middle_name")
                );

                instructor.setLastName(
                        rs.getString("last_name")
                );

                instructor.setDepartmentId(
                        rs.getInt("department_id")
                );

                return instructor;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(
            Instructor instructor
    ) {

        String sql = """
                UPDATE Instructor
                SET
                    first_name = ?,
                    middle_name = ?,
                    last_name = ?,
                    department_id = ?
                WHERE instructor_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    instructor.getFirstName()
            );

            pstmt.setString(
                    2,
                    instructor.getMiddleName()
            );

            pstmt.setString(
                    3,
                    instructor.getLastName()
            );

            pstmt.setInt(
                    4,
                    instructor.getDepartmentId()
            );

            pstmt.setInt(
                    5,
                    instructor.getInstructorId()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(
            int instructorId
    ) {

        String sql = """
                DELETE FROM Instructor
                WHERE instructor_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            pstmt.setInt(
                    1,
                    instructorId
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}