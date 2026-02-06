import java.sql.Connection;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {
        // Создаем объект госпиталя
        Hospital hospital = new Hospital("City Hospital");

        // Устанавливаем соединение с базой данных
        try {
            System.out.println("Connecting to healthcare_db...");
            try (Connection connection = DBConnection.getConnection("healthcare_db", "postgres", "0000")) {
                System.out.println("Connected to the database healthcare_db!");

                // Создаем таблицы
                DBConnection.createTables(connection);

                // Добавляем пациента
                Patient patient1 = new Patient(1, "John Doe", 45, "Flu");
                hospital.addPatient(patient1);

                // Добавляем медицинского профессионала
                MedicalProfessional doctor = new MedicalProfessional(1, "Dr. Smith", "General Practitioner");
                hospital.addMedicalProfessional(doctor);

                // Отображаем информацию о госпитале
                hospital.displayInfo();

                // Обновляем данные пациента
                hospital.updatePatient(1, "John Doe", 46, "Recovered from Flu");

                // Удаляем пациента
                hospital.removePatient(1);

                // Отображаем информацию после изменений
                hospital.displayInfo();
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            // Дополнительная диагностика: попробуем подключиться к 'postgres' и вывести список БД
            System.out.println("\n--- Diagnostic: Checking available databases ---");
            try (Connection conn = DBConnection.getConnection("postgres", "postgres", "0000")) {
                System.out.println("Connected to 'postgres' database successfully.");
                try (var rs = conn.createStatement().executeQuery("SELECT datname FROM pg_database WHERE datistemplate = false;")) {
                    System.out.println("Available databases:");
                    while (rs.next()) {
                        System.out.println("- " + rs.getString(1));
                    }
                }
            } catch (SQLException diagEx) {
                System.err.println("Could not connect to 'postgres' for diagnostics: " + diagEx.getMessage());
            }
        }
    }
}