import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    // Подключение к базе данных с актуальными данными
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/healthcare_db"; // Убедитесь, что имя вашей базы данных правильное
    private static final String DB_USERNAME = "postgres"; // Замените на ваш логин
    private static final String DB_PASSWORD = "0000"; // Замените на ваш пароль

    public static Connection getConnection(String dbName, String user, String password) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/" + dbName;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // Если база данных не найдена (код ошибки 3D000), попробуем создать её
            if ("3D000".equals(e.getSQLState())) {
                System.out.println("Database '" + dbName + "' not found. Attempting to create it...");
                createDatabase(dbName, user, password);
                return DriverManager.getConnection(url, user, password);
            }
            System.out.println("Connection failed!");
            throw e;
        }
    }

    private static void createDatabase(String dbName, String user, String password) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             var stmt = conn.createStatement()) {
            // В PostgreSQL нельзя выполнить CREATE DATABASE внутри транзакции или через PreparedStatement с параметрами для имени БД
            stmt.executeUpdate("CREATE DATABASE " + dbName);
            System.out.println("Database '" + dbName + "' created successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to create database: " + e.getMessage());
            throw e;
        }
    }

    public static void createTables(Connection connection) throws SQLException {
        String createHospitalTable = "CREATE TABLE IF NOT EXISTS hospital (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL" +
                ");";

        String createPatientTable = "CREATE TABLE IF NOT EXISTS patient (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "age INT, " +
                "condition VARCHAR(255), " +
                "hospital_id INT REFERENCES hospital(id)" +
                ");";

        String createMedicalProfessionalTable = "CREATE TABLE IF NOT EXISTS medical_professional (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "specialization VARCHAR(255), " +
                "hospital_id INT REFERENCES hospital(id)" +
                ");";

        try (var stmt = connection.createStatement()) {
            stmt.execute(createHospitalTable);
            stmt.execute(createPatientTable);
            stmt.execute(createMedicalProfessionalTable);
            System.out.println("Tables created or already exist.");
        } catch (SQLException e) {
            System.err.println("Failed to create tables: " + e.getMessage());
            throw e;
        }
    }
}