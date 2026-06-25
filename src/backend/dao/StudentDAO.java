package backend.dao;

import backend.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends BaseDAO {
    public void create(Student student) {

        String sql = """
                INSERT INTO Student (
                    first_name,
                    middle_name,
                    last_name,
                    birth_date,
                    address,
                    contact_no,
                    citizenship,
                    status,
                    block_id
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getMiddleName());
            pstmt.setString(3, student.getLastName());
            pstmt.setString(4, student.getBirthDate());
            pstmt.setString(5, student.getAddress());
            pstmt.setString(6, student.getContactNo());
            pstmt.setString(7, student.getCitizenship());
            pstmt.setString(8, student.getStatus());
            pstmt.setInt(9, student.getBlockId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAll() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM Student";

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                Student student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setMiddleName(rs.getString("middle_name"));
                student.setLastName(rs.getString("last_name"));
                student.setBirthDate(rs.getString("birth_date"));
                student.setAddress(rs.getString("address"));
                student.setContactNo(rs.getString("contact_no"));
                student.setCitizenship(rs.getString("citizenship"));
                student.setStatus(rs.getString("status"));
                student.setBlockId(rs.getInt("block_id"));

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public Student getById(int studentId) {

        String sql = """
                SELECT *
                FROM Student
                WHERE student_id = ?
                """;

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, studentId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Student student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setMiddleName(rs.getString("middle_name"));
                student.setLastName(rs.getString("last_name"));
                student.setBirthDate(rs.getString("birth_date"));
                student.setAddress(rs.getString("address"));
                student.setContactNo(rs.getString("contact_no"));
                student.setCitizenship(rs.getString("citizenship"));
                student.setStatus(rs.getString("status"));
                student.setBlockId(rs.getInt("block_id"));

                return student;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Student student) {

        String sql = """
                UPDATE Student
                SET
                    first_name = ?,
                    middle_name = ?,
                    last_name = ?,
                    birth_date = ?,
                    address = ?,
                    contact_no = ?,
                    citizenship = ?,
                    status = ?,
                    block_id = ?
                WHERE student_id = ?
                """;

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getMiddleName());
            pstmt.setString(3, student.getLastName());
            pstmt.setString(4, student.getBirthDate());
            pstmt.setString(5, student.getAddress());
            pstmt.setString(6, student.getContactNo());
            pstmt.setString(7, student.getCitizenship());
            pstmt.setString(8, student.getStatus());
            pstmt.setInt(9, student.getBlockId());
            pstmt.setInt(10, student.getStudentId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int studentId) {

        String sql = """
                DELETE FROM Student
                WHERE student_id = ?
                """;

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, studentId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int count() {

        String sql = "SELECT COUNT(*) FROM Student";

        try (
            Connection conn = getConnection();
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