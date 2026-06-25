package backend.dao;


import backend.models.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends BaseDAO {
    public void create(Department department) {
        String sql = """
                INSERT INTO Department (department_name)
                VALUES (?)
                """;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, department.getDepartmentName());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();

        String sql = "SELECT * FROM Department";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Department department = new Department();

                department.setDepartmentId(rs.getInt("department_id"));
                department.setDepartmentName(rs.getString("department_name"));

                departments.add(department);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return departments;
    }

    public Department getById(int departmentId) {
        String sql = """
                SELECT *
                FROM Department
                WHERE department_id = ?
                """;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Department department = new Department();

                department.setDepartmentId(rs.getInt("department_id"));
                department.setDepartmentName(rs.getString("department_name"));

                return department;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Department department) {
        String sql = """
                UPDATE Department
                SET department_name = ?
                WHERE department_id = ?
                """;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, department.getDepartmentName());
            pstmt.setInt(2, department.getDepartmentId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int departmentId) {
        String sql = """
                DELETE FROM Department
                WHERE department_id = ?
                """;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}