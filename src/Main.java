import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Hospital hospital = new Hospital("City Hospital");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        connectToDatabase();

        while (true) {
            System.out.println("\n--- Healthcare Management System ---");
            System.out.println("1. Show all Patients");
            System.out.println("2. Add Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Remove Patient");
            System.out.println("5. Add Medical Professional");
            System.out.println("6. Remove Medical Professional");
            System.out.println("7. Display Hospital Info");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAllPatients();
                    break;
                case 2:
                    addPatient();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    removePatient();
                    break;
                case 5:
                    addMedicalProfessional();
                    break;
                case 6:
                    removeMedicalProfessional();
                    break;
                case 7:
                    hospital.displayInfo();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void showAllPatients() {
        System.out.println("\n--- Patients ---");
        hospital.displayInfo();
    }

    private static void addPatient() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Patient Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(id, name, age, condition);
        hospital.addPatient(patient);
        System.out.println("Patient added successfully.");
    }

    private static void updatePatient() {
        System.out.print("Enter Patient ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Patient Condition: ");
        String condition = scanner.nextLine();

        hospital.updatePatient(id, name, age, condition);
        System.out.println("Patient updated successfully.");
    }

    private static void removePatient() {
        System.out.print("Enter Patient ID to remove: ");
        int id = scanner.nextInt();
        hospital.removePatient(id);
        System.out.println("Patient removed successfully.");
    }

    private static void addMedicalProfessional() {
        System.out.print("Enter Medical Professional ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Medical Professional Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();

        MedicalProfessional professional = new MedicalProfessional(id, name, specialization);
        hospital.addMedicalProfessional(professional);
        System.out.println("Medical Professional added successfully.");
    }

    private static void removeMedicalProfessional() {
        System.out.print("Enter Medical Professional ID to remove: ");
        int id = scanner.nextInt();
        hospital.removeProfessional(id);
        System.out.println("Medical Professional removed successfully.");
    }

    private static void connectToDatabase() {
        String url = "jdbc:postgresql://localhost:5432/your_database_name";
        String user = "your_username";
        String password = "your_password";

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            String query = "SELECT * FROM patients";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String condition = resultSet.getString("condition");
                System.out.println(id + " | " + name + " | " + age + " | " + condition);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
