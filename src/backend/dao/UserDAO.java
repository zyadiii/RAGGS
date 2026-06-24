package backend.dao;

import backend.db.DBConnection;
import backend.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User login(
            String username,
            String password
    ) {

        String sql = """
                SELECT *
                FROM User
                WHERE username = ?
                AND password = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setUserId(
                        rs.getInt("user_id")
                );

                user.setUsername(
                        rs.getString("username")
                );

                user.setPassword(
                        rs.getString("password")
                );

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void create(User user) {

        String sql = """
                INSERT INTO User (
                        username,
                        password
                )
                VALUES (?, ?)
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

                pstmt.setString(
                        1,
                        user.getUsername()
                );

                pstmt.setString(
                        2,
                        user.getPassword()
                );

                pstmt.executeUpdate();

        } catch (Exception e) {
                e.printStackTrace();
        }
        }
}