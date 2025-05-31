package PaooGame.DataBase.DataManagement;

import java.sql.*;

public class PlayerSaves {
    private Connection conn;
    private PreparedStatement getDataPStmt;
    private PreparedStatement updateDataPStmt;
    private PreparedStatement insertDataPStmt;
    private PreparedStatement deleteDataPStmt;

    public PlayerSaves() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:src/PaooGame/DataBase/DataManagement/DataBaseFiles/SAVES.db");

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS SAVES (" +
                            "id INTEGER PRIMARY KEY," +
                            "player TEXT," +
                            "posx INTEGER," +
                            "posy INTEGER," +
                            "current_level INTEGER," +
                            "hp INTEGER," +
                            "score INTEGER" +
                            ");"
            );
            stmt.close();

            getDataPStmt = conn.prepareStatement("SELECT * FROM SAVES WHERE id = ?");
            updateDataPStmt = conn.prepareStatement(
                    "UPDATE SAVES SET player = ?, posx = ?, posy = ?, current_level = ?, hp = ?, score = ? WHERE id = ?"
            );
            insertDataPStmt = conn.prepareStatement(
                    "INSERT INTO SAVES (id, player, posx, posy, current_level, hp, score) " +
                            "SELECT ?, ?, ?, ?, ?, ?, ? " +
                            "WHERE NOT EXISTS (SELECT 1 FROM SAVES WHERE id = ?)"
            );
            deleteDataPStmt = conn.prepareStatement("DELETE FROM SAVES WHERE id = ?");

            System.out.println("Opened database successfully");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void Save(int saveSlot, String player, int posx, int posy, int level, int hp, int score) {
        if (saveSlot < 1 || saveSlot > 3) {
            System.out.println("Invalid save slot. Only slots 1, 2, and 3 are allowed.");
            return;
        }

        try {
            updateDataPStmt.setString(1, player);
            updateDataPStmt.setInt(2, posx);
            updateDataPStmt.setInt(3, posy);
            updateDataPStmt.setInt(4, level);
            updateDataPStmt.setInt(5, hp);
            updateDataPStmt.setInt(6, score);
            updateDataPStmt.setInt(7, saveSlot);
            int updated = updateDataPStmt.executeUpdate();

            if (updated == 0) {
                insertDataPStmt.setInt(1, saveSlot);
                insertDataPStmt.setString(2, player);
                insertDataPStmt.setInt(3, posx);
                insertDataPStmt.setInt(4, posy);
                insertDataPStmt.setInt(5, level);
                insertDataPStmt.setInt(6, hp);
                insertDataPStmt.setInt(7, score);
                insertDataPStmt.setInt(8, saveSlot);
                insertDataPStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlayerSaveDataObject Load(int saveSlot) {
        if (saveSlot < 1 || saveSlot > 3) {
            System.out.println("Invalid save slot.");
            return null;
        }
        PlayerSaveDataObject save = null;
        try {
            getDataPStmt.setInt(1, saveSlot);
            ResultSet rs = getDataPStmt.executeQuery();

            if (rs.next()) {
                String player = rs.getString("player");
                int posx = rs.getInt("posx");
                int posy = rs.getInt("posy");
                int level = rs.getInt("current_level");
                int hp = rs.getInt("hp");
                int score = rs.getInt("score");

                save = new PlayerSaveDataObject(posx, posy, level, score, hp);
                System.out.printf("Loaded slot %d: player=%s pos=(%d,%d) level=%d hp=%d score=%d%n",
                        saveSlot, player, posx, posy, level, hp, score);
            } else {
                System.out.println("Save slot " + saveSlot + " is empty.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return save;
    }

    public void removeSlot(int saveSlot) {
        if (saveSlot < 1 || saveSlot > 3) {
            System.out.println("Invalid save slot.");
            return;
        }

        try {
            deleteDataPStmt.setInt(1, saveSlot);
            int deleted = deleteDataPStmt.executeUpdate();
            if (deleted > 0) {
                System.out.println("Deleted save slot " + saveSlot + ".");
            } else {
                System.out.println("No data found in slot " + saveSlot + " to delete.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Close() {
        try {
            if (getDataPStmt != null) getDataPStmt.close();
            if (updateDataPStmt != null) updateDataPStmt.close();
            if (insertDataPStmt != null) insertDataPStmt.close();
            if (deleteDataPStmt != null) deleteDataPStmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void printAllSaves() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM SAVES ORDER BY id ASC")) {

            boolean[] seen = new boolean[4];

            while (rs.next()) {
                int id = rs.getInt("id");
                seen[id] = true;

                String player = rs.getString("player");
                int posx = rs.getInt("posx");
                int posy = rs.getInt("posy");
                int level = rs.getInt("current_level");
                int hp = rs.getInt("hp");
                int score = rs.getInt("score");

                System.out.printf("Slot %d: player=%s, pos=(%d,%d), level=%d, hp=%d, score=%d%n",
                        id, player, posx, posy, level, hp, score);
            }

            for (int i = 1; i <= 3; i++) {
                if (!seen[i]) {
                    System.out.println("Slot " + i + ": [empty]");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean[] getUsedSlots() {
        boolean[] usedSlots = new boolean[3]; // index 0 -> slot 1, index 1 -> slot 2, etc.

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id FROM SAVES WHERE id IN (1, 2, 3)")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                if (id >= 1 && id <= 3) {
                    usedSlots[id - 1] = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usedSlots;
    }

}
