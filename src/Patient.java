public class Patient extends Entity {
    private String name;
    private int age;
    private String condition;

    public Patient(int id, String name, int age, String condition) {
        super(id);
        this.name = name;
        this.age = age;
        this.condition = condition;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCondition() {
        return condition;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Condition: " + condition);
    }
}