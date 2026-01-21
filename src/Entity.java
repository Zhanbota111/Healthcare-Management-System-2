public class Patient {
    private int id;
    private String name;
    private int age;
    private String illness;

    public Patient(String name, int age, String illness) {
        this.name = name;
        this.age = age;
        this.illness = illness;
    }

    @Override
    public String toString() {
        return name + " | " + age + " | " + illness;
    }
}
