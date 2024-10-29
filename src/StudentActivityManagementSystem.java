import java.util.*;
import java.io.*;

public class StudentActivityManagementSystem {
    // Maximum number of students allowed in the system
    private static final int MAX_STUDENTS = 100;

    // Array to store student IDs
    private static final String[] studentIDs = new String[MAX_STUDENTS];

    // Array to store student names
    private static final String[] studentNames = new String[MAX_STUDENTS];

    // Variable to keep track of the current number of students in the system
    private static int currentStudentCount = 0;


    // This array will store student information, up to 100 students
    private static final Student[] student = new Student[100];

    // Arrays to store module marks
    private static final int[] module1Marks = new int[MAX_STUDENTS];
    private static final int[] module2Marks = new int[MAX_STUDENTS];
    private static final int[] module3Marks = new int[MAX_STUDENTS];


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // Initialize Scanner for user input

        // Display the main menu
        while (true) {
            System.out.println("\n====================Student Activity Management System====================");
            System.out.println("1. Check available seats");
            System.out.println("2. Register student");
            System.out.println("3. Delete student");
            System.out.println("4. Find student");
            System.out.println("5. Store student details into a file");
            System.out.println("6. Load student details from the file");
            System.out.println("7. View the list of students");
            System.out.println("8. Get Student module marks");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();  // Read user choice
                scanner.nextLine();  // Consume newline
                switch (choice) {
                    case 1:
                        checkAvailableSeats();
                        break;
                    case 2:
                        registerStudent(scanner);
                        break;
                    case 3:
                        deleteStudents(scanner);
                        break;
                    case 4:
                        findStudent(scanner);
                        break;
                    case 5:
                        storeStudentDetails();
                        break;
                    case 6:
                        loadToFile();
                        break;
                    case 7:
                        viewStudents();
                        break;
                    case 8:
                        getStudentModuleMark(scanner);
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Your choice is invalid. Try again.");
                }
            }
            // Handle the invalid input
            catch (InputMismatchException e) {
                System.out.println("The input provided is invalid. Please enter a valid one.");
                scanner.next();
            }
        }
    }

    private static void checkAvailableSeats() {
        System.out.println("\n___________________Available seats___________________ ");

        // Check if the student count is less than or equal to the maximum allowed
        if (MAX_STUDENTS >= currentStudentCount) {
            // Show the number of seats that are available
            System.out.println("Available seats: " + (MAX_STUDENTS - currentStudentCount));
        } else {
            // Print a message if there are no available seats
            System.out.println("No Seats available. ");
        }
    }

    private static void registerStudent(Scanner scanner) {
        System.out.println("\n___________Register Students___________");

        // Check if the total number of students has reached the maximum allowed
        if (currentStudentCount >= MAX_STUDENTS) {
            System.out.println("Seats are currently unavailable.");
            return;
        }

        while (true) {
            System.out.print("Enter student ID (w1234567): ");
            String id = scanner.nextLine();

            // Check if the student ID is in the correct format
            if (ValidID(id)) {
                // Check if the ID already exists
                boolean idExists = false;
                for (int i = 0; i < currentStudentCount; i++) {
                    if (studentIDs[i].equals(id)) {
                        idExists = true;
                        break;
                    }
                }
                if (idExists) {
                    System.out.println("That student ID is already in use. Please choose a unique ID.");
                } else {
                    // Register the student
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    studentIDs[currentStudentCount] = id;
                    studentNames[currentStudentCount] = name;
                    currentStudentCount++;
                    System.out.println(name + " is registered.");

                    // Prompt asking whether another student should register or go to the main menu
                    while (true) {
                        System.out.println("1. Register another student");
                        System.out.println("2. Go to main menu");
                        System.out.print("Enter your option: ");
                        String choice = scanner.nextLine();
                        if (choice.equals("1")) {
                            break; // Break inner loop to register another student
                        } else if (choice.equals("2")) {
                            System.out.println("Returning to the main menu...");
                            return; // Exit method to return to main menu
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    }
                }
            } else {
                System.out.println("Invalid ID format. Please enter an ID in the format w1234567.");
            }
        }
    }

    private static boolean ValidID(String id) {
        if (id == null || id.length() != 8) {
            return false;
        }
        if (id.charAt(0) != 'w') {
            return false;
        }
        for (int i = 1; i < 8; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }



    private static void deleteStudents(Scanner scanner) {
        System.out.println("\n_____________________Delete Students________________________________");

        while (true) {
            System.out.print("Enter student ID to delete: ");
            String id = scanner.nextLine();

            boolean foundStudent = false;
            for (int i = 0; i < currentStudentCount; i++) {
                if (studentIDs[i].equals(id)) {
                    foundStudent = true;
                    studentIDs[i] = studentIDs[currentStudentCount - 1];
                    studentNames[i] = studentNames[currentStudentCount - 1];
                    studentIDs[currentStudentCount - 1] = null;
                    studentNames[currentStudentCount - 1] = null;
                    currentStudentCount--;
                    System.out.println("Student is deleted.");
                    return; // Exit after successful deletion
                }
            }

            if (!foundStudent) {
                System.out.println("Student not found.");
                while (true) {
                    System.out.println("1. Again enter the student Id");
                    System.out.println("2. Go to main menu");
                    System.out.print("Enter your option: ");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("Please provide student ID again...");
                            break; // Break inner loop to re-enter ID
                        case "2":
                            System.out.println("Going back to the main menu...");
                            return; // Exit method to return to main menu
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            continue; // Prompt for choice again
                    }
                    break; // Break inner loop to re-enter ID
                }
            }
        }
    }


    private static void findStudent(Scanner scanner) {
        System.out.println("\n_____________________________________Find Students______________________________________");
        System.out.print("Please input student ID to find: ");
        String inputStudentID = scanner.nextLine();
        for (int i = 0; i < currentStudentCount; i++) {
            if (studentIDs[i].equals(inputStudentID)) {
                System.out.println("Student found: " + studentNames[i]);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void storeStudentDetails() {
        System.out.println("\n__________________________________Storing Student Details_____________________________________");
        try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt"))) {
            // Print the header line
            writer.printf("%-20s %-25s %-20s %-20s %-20s%n",
                    "ID", "Name", "Module 1", "Module 2", "Module 3");
            for (int i = 0; i < currentStudentCount; i++) {
                if (studentIDs[i] != null && studentNames[i] != null) {
                    writer.printf("%-20s %-25s %-20d %-20d %-20d%n",
                            studentIDs[i],
                            studentNames[i],
                            module1Marks[i],
                            module2Marks[i],
                            module3Marks[i]);
                }
            }
            System.out.println("Student details stored.");
        } catch (IOException e) {
            System.out.println("An error occurred while storing student details.");
            e.printStackTrace();
        }
    }

    private static void loadToFile() {
        System.out.println("\n__________________________________Loading Student Details_____________________________________");
        try (Scanner scanner = new Scanner(new File("students.txt"))) {
            currentStudentCount = 0;

            // Skip the header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Extract data using substring based on fixed widths

                String id = line.substring(0, 20).trim();
                String name = line.substring(20, 45).trim();
                int module1 = Integer.parseInt(line.substring(45, 65).trim());
                int module2 = Integer.parseInt(line.substring(65, 85).trim());
                int module3 = Integer.parseInt(line.substring(85).trim());

                // Assign extracted data to arrays
                studentIDs[currentStudentCount] = id;
                studentNames[currentStudentCount] = name;
                module1Marks[currentStudentCount] = module1;
                module2Marks[currentStudentCount] = module2;
                module3Marks[currentStudentCount] = module3;
                currentStudentCount++;
            }
            System.out.println("Student details loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Student details file not found.");
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("Error parsing module marks.");
            e.printStackTrace();
        }
    }

    private static void viewStudents() {
        System.out.println("\n______________________________________View Students__________________________________");
        // Create a copy of studentNames array up to currentStudentCount
        String[] sortedNames = Arrays.copyOf(studentNames, currentStudentCount);

        // Sort the array of names
        Arrays.sort(sortedNames, String::compareTo);

        // Print the sorted list of students
        System.out.println("List of students:");
        for (String name : sortedNames) {
            for (int i = 0; i < currentStudentCount; i++) {
                if (studentNames[i].equals(name)) {
                    System.out.println("ID: " + studentIDs[i] + ", Name: " + name);
                    break;  // Stop searching once the name is found and printed
                }
            }
        }
    }

    private static void getStudentModuleMark(Scanner scanner) {
        System.out.println("\n________________________________Manage Student Modules___________________________________");

        while (true) {
            System.out.println("B. Enter student module marks");
            System.out.println("C. Generate summary report");
            System.out.println("D. Generate complete report");
            System.out.println("E. Exit to main menu");
            System.out.print("Enter your option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "B":
                    while (true) {
                        System.out.print("Enter student name: ");
                        String name = scanner.nextLine();

                        boolean foundStudent = false;
                        for (int i = 0; i < currentStudentCount; i++) {
                            if (studentNames[i].equalsIgnoreCase(name)) {
                                foundStudent = true;

                                try {
                                    // Prompt for module marks
                                    int mark1 = getValidMark(scanner, "Input the module 1 mark: ");
                                    int mark2 = getValidMark(scanner, "Input the module 2 mark: ");
                                    int mark3 = getValidMark(scanner, "Input the module 3 mark: ");

                                    // Store module marks
                                    module1Marks[i] = mark1;
                                    module2Marks[i] = mark2;
                                    module3Marks[i] = mark3;

                                    // Calculate average mark
                                    double average = (mark1 + mark2 + mark3) / 3.0;

                                    // Assign grade based on average
                                    String grade;
                                    if (average >= 80) {
                                        grade = "Distinction";
                                    } else if (average >= 70) {
                                        grade = "Merit";
                                    } else if (average >= 40) {
                                        grade = "Pass";
                                    } else {
                                        grade = "Fail";
                                    }

                                    // Output module marks and grade
                                    System.out.println("Module marks for student " + studentNames[i] + ":");
                                    System.out.println("Module 1: " + mark1);
                                    System.out.println("Module 2: " + mark2);
                                    System.out.println("Module 3: " + mark3);
                                    System.out.println("Average mark: " + average);
                                    System.out.println("Grade: " + grade);

                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter numeric values for module marks.");
                                    scanner.nextLine();  // Clear the input buffer
                                }
                                break; // Exit the inner loop after processing the valid student name
                            }
                        }

                        if (!foundStudent) {
                            System.out.println("Student not found.");
                            while (true) {
                                System.out.println("1. Again enter the student name");
                                System.out.println("2. Go to main menu");
                                System.out.print("Enter your option: ");
                                String retryChoice = scanner.nextLine();
                                switch (retryChoice) {
                                    case "1":
                                        System.out.println("Re-entering student name...");
                                        break; // Re-enter the loop to prompt for name again
                                    case "2":
                                        System.out.println("Returning to the main menu...");
                                        return; // Exit the method to return to the main menu
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                        continue; // Prompt for the choice again
                                }
                                break; // Break the while loop if the user chooses to re-enter the name
                            }
                        } else {
                            break; // Exit the outer loop if the student was found and processed
                        }
                    }
                    break; // Exit the main switch-case after handling option "B"
                case "C":
                    generateSummaryOfSystem();
                    break;
                case "D":
                    generateCompleteReport();
                    break;
                case "E":
                    System.out.println("Returning to the main menu...");
                    return; // Exit the method to return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static int getValidMark(Scanner scanner, String prompt) {
        int mark;
        while (true) {
            System.out.print(prompt);
            try {
                mark = scanner.nextInt();
                // Check if the mark is within the valid range
                if (mark >= 0 && mark <= 100) {
                    break;
                } else {
                    System.out.println("Please enter a mark between 0 and 100.");
                }
            }
            catch (InputMismatchException e) {
                // Handle invalid (non-numeric) input
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        scanner.nextLine(); // Consume newline
        return mark;
    }

    private static void generateSummaryOfSystem() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Summary Of The System~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Total number of student registrations: " + currentStudentCount);

        // Initialize counters for students passing each module
        int Module1passCount = 0, Module2passCount = 0, Module3passCount = 0;

        // Iterate through each student to count passing marks for each module
        for (int stIndex = 0; stIndex < currentStudentCount; stIndex++) {
            if (module1Marks[stIndex] > 40) Module1passCount++;
            if (module2Marks[stIndex] > 40) Module2passCount++;
            if (module3Marks[stIndex] > 40) Module3passCount++;
        }

        // Output the summary of information
        System.out.println("Count of students who scored above 40 in Module 1: " + Module1passCount);
        System.out.println("Count of students who scored above 40 in Module 2: " + Module2passCount);
        System.out.println("Count of students who scored above 40 in Module 3: " + Module3passCount);
    }

    private static void generateCompleteReport() {
        System.out.println("\nReport : ");

        // Array to store data for each student
        String[][] Data = new String[currentStudentCount][8];

        // Populate data for each student
        for (int i = 0; i < currentStudentCount; i++) {

            // Calculate total marks and average of marks
            int total = module1Marks[i] + module2Marks[i] + module3Marks[i];
            double averageOfMarks = total / 3.0;

            // Determine grade based on average marks
            String grade;
            if (averageOfMarks >= 80) {
                grade = "Distinction";
            } else if (averageOfMarks >= 70) {
                grade = "Merit";
            } else if (averageOfMarks >= 40) {
                grade = "Pass";
            } else {
                grade = "Fail";
            }

            // Populate the Data array with student information
            Data[i][0] = studentIDs[i];
            Data[i][1] = studentNames[i];
            Data[i][2] = String.valueOf(module1Marks[i]);
            Data[i][3] = String.valueOf(module2Marks[i]);
            Data[i][4] = String.valueOf(module3Marks[i]);
            Data[i][5] = String.valueOf(total);
            Data[i][6] = String.valueOf(averageOfMarks);
            Data[i][7] = grade;
        }

        // Sort Data array using bubble sort, descending by average marks (index 6)
        for (int ftIndex = 0; ftIndex < Data.length - 1; ftIndex++) {
            for (int sndIndex = 0; sndIndex < Data.length - 1 - ftIndex; sndIndex++) {
                // Compare the average marks of the current row with the next row
                if (Double.parseDouble(Data[sndIndex][6]) < Double.parseDouble(Data[sndIndex + 1][6])) {
                    // If current row's average mark is lower, swap rows with the next row
                    String[] currentRow = Data[sndIndex];
                    Data[sndIndex] = Data[sndIndex + 1];
                    Data[sndIndex + 1] = currentRow;
                }
            }
        }

        // Print table header
        System.out.println("+--------------------+-------------------------+--------------------+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("| %-18s | %-23s | %-18s | %-18s | %-18s | %-18s | %-18s | %-18s |\n",
                "     ID     ", "      Name      ", "     Module 1     ", "     Module 2     ", "     Module 3     ", "     Total     ", "     Average     ", "     Grade     ");
        System.out.println("+--------------------+-------------------------+--------------------+--------------------+--------------------+--------------------+--------------------+--------------------+");

        // Print table rows
        for (String[] student : Data) {
            System.out.printf("| %-18s | %-23s | %-18s | %-18s | %-18s | %-18s | %-18s | %-18s |\n", student[0], student[1], student[2], student[3], student[4], student[5], student[6], student[7]);
        }
        System.out.println("+--------------------+-------------------------+--------------------+--------------------+--------------------+--------------------+--------------------+--------------------+");

    }

}
