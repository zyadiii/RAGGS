package backend.dao;

import backend.models.Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAO extends BaseDAO {
    public void create(Program program) {

        String sql = """
                INSERT INTO Program (
                    program_name,
                    department_id
                )
                VALUES (?, ?)
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, program.getProgramName());
            pstmt.setInt(2, program.getDepartmentId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Program> getAll() {

        List<Program> programs = new ArrayList<>();

        String sql = "SELECT * FROM Program";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                Program program = new Program();

                program.setProgramId(rs.getInt("program_id"));
                program.setProgramName(rs.getString("program_name"));
                program.setDepartmentId(rs.getInt("department_id"));

                programs.add(program);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return programs;
    }

    public Program getById(int programId) {

        String sql = """
                SELECT *
                FROM Program
                WHERE program_id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, programId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Program program = new Program();

                program.setProgramId(rs.getInt("program_id"));
                program.setProgramName(rs.getString("program_name"));
                program.setDepartmentId(rs.getInt("department_id"));

                return program;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Program program) {

        String sql = """
                UPDATE Program
                SET
                    program_name = ?,
                    department_id = ?
                WHERE program_id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, program.getProgramName());
            pstmt.setInt(2, program.getDepartmentId());
            pstmt.setInt(3, program.getProgramId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int programId) {

        String sql = """
                DELETE FROM Program
                WHERE program_id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, programId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int count() {

        String sql = "SELECT COUNT(*) FROM Program";

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