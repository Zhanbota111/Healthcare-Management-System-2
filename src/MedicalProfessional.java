public class MedicalProfessional extends Entity {
    private String name;
    private String specialization;

    public MedicalProfessional(int id, String name, String specialization) {
        super(id);
        this.name = name;
        this.specialization = specialization;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public void displayInfo() {
        System.out.println("Medical Professional ID: " + id + ", Name: " + name + ", Specialization: " + specialization);
    }
}