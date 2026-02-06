import java.util.ArrayList;

public class Hospital {
    private String name;
    private ArrayList<Patient> patients;
    private ArrayList<MedicalProfessional> medicalProfessionals;

    public Hospital(String name) {
        this.name = name;
        this.patients = new ArrayList<>();
        this.medicalProfessionals = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(int id) {
        patients.removeIf(patient -> patient.getId() == id);
    }

    public void updatePatient(int id, String name, int age, String condition) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                patient.setName(name);
                patient.setAge(age);
                patient.setCondition(condition);
                System.out.println("Patient updated successfully.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }
    public void addMedicalProfessional(MedicalProfessional professional) {
        medicalProfessionals.add(professional);
    }

    public void displayInfo() {
        System.out.println("Hospital: " + name);
        System.out.println("Patients:");
        for (Patient patient : patients) {
            patient.displayInfo();
        }
        System.out.println("Medical Professionals:");
        for (MedicalProfessional professional : medicalProfessionals) {
            professional.displayInfo();
        }
    }
}