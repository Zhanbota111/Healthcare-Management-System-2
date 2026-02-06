public abstract class Entity {
    // Уникальный идентификатор для каждой сущности
    protected int id;

    // Конструктор, принимающий id
    public Entity(int id) {
        this.id = id;
    }

    // Геттер для id
    public int getId() {
        return id;
    }

    // Сеттер для id
    public void setId(int id) {
        this.id = id;
    }

    // Абстрактный метод для вывода информации.
    // Каждый наследуемый класс должен предоставить свою реализацию этого метода.
    public abstract void displayInfo();
}