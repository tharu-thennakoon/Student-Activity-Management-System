// Define a class named Module
public class Module {
    private int mark1;
    private int mark2;
    private int mark3;

    // Initializes the marks value in the constructor
    public Module(int mark1, int mark2, int mark3) {
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    // Method to get the value of mark1
    public int getMark1() {
        return mark1;
    }

    // Method to get the value of mark2
    public int getMark2() {
        return mark2;
    }

    // Method to get the value of mark3
    public int getMark3() {
        return mark3;
    }

    // Method to set the value of mark1
    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    // Method to set the value of mark2
    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    // Method to set the value of mark3
    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }
}
