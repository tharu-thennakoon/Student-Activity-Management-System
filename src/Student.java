// Define a class named Student
public class Student {
    private String id;
    private String name;
    private Module module;

    // Initializes student ID, name, and module in the constructor
    public Student(String id, String name, Module module) {
        this.id = id;
        this.name = name;
        this.module = module;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
