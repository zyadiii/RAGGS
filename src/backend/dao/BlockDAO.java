package backend.dao;

import backend.db.DBConnection;
import backend.models.Block;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BlockDAO {

    public void create(Block block) {
        String sql = """
                INSERT INTO Block (
                    block_name,
                    program_id
                )
                VALUES (?, ?)
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    block.getBlockName()
            );

            pstmt.setInt(
                    2,
                    block.getProgramId()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Block> getAll() {
        List<Block> blocks = new ArrayList<>();

        String sql = "SELECT * FROM Block";

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                Block block = new Block();

                block.setBlockId(
                        rs.getInt("block_id")
                );

                block.setBlockName(
                        rs.getString("block_name")
                );

                block.setProgramId(
                        rs.getInt("program_id")
                );

                blocks.add(block);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return blocks;
    }

    public Block getById(int blockId) {

        String sql = """
                SELECT *
                FROM Block
                WHERE block_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, blockId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Block block = new Block();

                block.setBlockId(
                        rs.getInt("block_id")
                );

                block.setBlockName(
                        rs.getString("block_name")
                );

                block.setProgramId(
                        rs.getInt("program_id")
                );

                return block;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Block block) {

        String sql = """
                UPDATE Block
                SET
                    block_name = ?,
                    program_id = ?
                WHERE block_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    block.getBlockName()
            );

            pstmt.setInt(
                    2,
                    block.getProgramId()
            );

            pstmt.setInt(
                    3,
                    block.getBlockId()
            );

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int blockId) {

        String sql = """
                DELETE FROM Block
                WHERE block_id = ?
                """;

        try (
                Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, blockId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}