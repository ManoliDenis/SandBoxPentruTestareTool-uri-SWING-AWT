package PaooGame.DataBase.DataManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HallofFame {
    private Connection conn;
    private PreparedStatement insertPStmt;///Statement preparat pentru insert
    private PreparedStatement deleteLowestPStmt;///Statement preparat pentru delete
    private PreparedStatement getMinScorePStmt;///

    public HallofFame() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:src/PaooGame/DataBase/DataManagement/DataBaseFiles/HALLOFFAME.db");

            Statement stmt = conn.createStatement();///facem conexiune cu tablea
            /// Update -> tabela not exist create
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS HALL_OF_FAME (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "player TEXT NOT NULL," +
                            "score INTEGER NOT NULL" +
                            ");"
            );
            stmt.close();

            insertPStmt = conn.prepareStatement(/// Preparam insert
                    "INSERT INTO HALL_OF_FAME (player, score) VALUES (?, ?)"
            );
            getMinScorePStmt = conn.prepareStatement(/// Preparam minscore
                    "SELECT MIN(score) AS min_score FROM HALL_OF_FAME"
            );
            deleteLowestPStmt = conn.prepareStatement(/// Preparam delete
                    "DELETE FROM HALL_OF_FAME WHERE id = (" +
                            "SELECT id FROM HALL_OF_FAME ORDER BY score ASC, id DESC LIMIT 1)"
            );

            System.out.println("Hall of Fame initialized");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void addPlayer(String player, int score) {
        try {
            Statement countStmt = conn.createStatement();
            ResultSet rsCount = countStmt.executeQuery("SELECT COUNT(*) FROM HALL_OF_FAME");///Numarul de valori
            rsCount.next();
            int count = rsCount.getInt(1);
            rsCount.close();
            countStmt.close();

            if (count < 5) {
                insert(player, score);///Daca-s mai putini de 5 insereaza
            } else {/// Daca nu
                ResultSet rsMin = getMinScorePStmt.executeQuery();///Ia scoru min
                int minScore = rsMin.getInt("min_score");///Salveaza val minima
                rsMin.close();

                if (score > minScore) {/// Daca scorul e mai mare cca min
                    deleteLowestPStmt.executeUpdate();///randul cu scorul mic e sters
                    insert(player, score);///bagam player-ul
                } else {
                    System.out.println("Score too low for Hall of Fame.");///Se explica
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ///Adaug player-ul cu numele si score
    private void insert(String player, int score) throws SQLException {
        insertPStmt.setString(1, player);
        insertPStmt.setInt(2, score);
        insertPStmt.executeUpdate();
    }
    /// Debug
    public void printTopPlayers() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT player, score FROM HALL_OF_FAME ORDER BY score DESC, id ASC")) {

            int rank = 1;
            while (rs.next()) {
                String player = rs.getString("player");
                int score = rs.getInt("score");
                System.out.printf("%d. %s - %d%n", rank++, player, score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /// get top
    public ArrayList<HallofFameDataObject> getTopPlayers() {
        ArrayList<HallofFameDataObject> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement();/// Ordoneaza dupa scor descendent si dupa id asc ( daca doua scoruri sunt asemenea)
             ResultSet rs = stmt.executeQuery("SELECT player, score FROM HALL_OF_FAME ORDER BY score DESC, id ASC")) {
            while (rs.next()) {/// Per fiecare rand
                String player = rs.getString("player");
                int score = rs.getInt("score");
                list.add(new HallofFameDataObject(player, score));///adaugam in lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;///returnam lista
    }

    public void clearHallOfFame() {/// Sterg totul
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM HALL_OF_FAME");
            System.out.println("Hall of Fame cleared.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /// Inchid conexiunle
    public void Close() {
        try {
            if (insertPStmt != null) insertPStmt.close();
            if (deleteLowestPStmt != null) deleteLowestPStmt.close();
            if (getMinScorePStmt != null) getMinScorePStmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
