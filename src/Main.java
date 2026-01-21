import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {

            String insertPatient = "INSERT INTO patient (name, age, illness) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertPatient);
            ps.setString(1, "Aruzhan");
            ps.setInt(2, 21);
            ps.setString(3, "Flu");
            ps.executeUpdate();

            String selectSQL = "SELECT * FROM patient";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL);

            System.out.println("Patients:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("illness")
                );
            }

            String updateSQL = "UPDATE patient SET illness = ? WHERE name = ?";
            PreparedStatement psUpdate = conn.prepareStatement(updateSQL);
            psUpdate.setString(1, "Cold");
            psUpdate.setString(2, "Aruzhan");
            psUpdate.executeUpdate();

            String deleteSQL = "DELETE FROM patient WHERE name = ?";
            PreparedStatement psDelete = conn.prepareStatement(deleteSQL);
            psDelete.setString(1, "Aruzhan");
            psDelete.executeUpdate();

            System.out.println("CRUD operations completed.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
