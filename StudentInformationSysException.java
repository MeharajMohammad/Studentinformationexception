import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int studentId;
    private String course;

    public Student(String name, int studentId, String course) {
        this.name = name;
        this.studentId = studentId;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    
    public String toString() {  // Corrected the method name to 'toString'
        return "Name: " + name + ", Student ID: " + studentId + ", Course: " + course;
    }
}

class StudentManage {
    ArrayList<Student> s = new ArrayList<>(); // Initialize the ArrayList
    int maxCapacity;
    int count = 0;
    String courseName;

    public StudentManage(int maxCapacity, String courseName) {
        this.maxCapacity = maxCapacity;
        this.courseName = courseName;
    }

    public void addStudent(String name, int id, String course)
            throws CourseFullException, DuplicateEnrollmentException {
        Student a = new Student(name, id, course);
        if (count == maxCapacity) {
            throw new CourseFullException("This Course is full!");
        } else {
            if (s.isEmpty()) {
                s.add(a);
            } else {
                for (int i = 0; i < s.size(); i++) {
                    if (s.get(i).getName().equalsIgnoreCase(name)) {
                        throw new DuplicateEnrollmentException("The Student already Exist!");
                    }
                }
                s.add(a);
                count += 1;
            }
        }
    }

    public void view() {
        for (Student i : s) {
            System.out.println(i);
        }
    }

    public void search(int id) {
        boolean found = false; // Track if the student is found
        for (Student a : s) {
            if (a.getStudentId() == id) {
                System.out.println("Student found: " + a);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not Found!");
        }
    }

    public void delete(String name) throws InvalidStudent {
        boolean found = false; // Track if the student is found
        for (Student d : s) {
            if (d.getName().equalsIgnoreCase(name)) {
                System.out.println(d + " is deleted successfully");
                s.remove(d);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidStudent("Student not Found!");
        }
    }
}

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class DuplicateEnrollmentException extends Exception {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}

class InvalidStudent extends Exception {
    public InvalidStudent(String message) {
        super(message);
    }
}

public class StudentInformationSysException {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Course and its capacity to Create");
        String s = scanner.nextLine();
        int maxCapacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        StudentManage c = new StudentManage(maxCapacity, s);

        while (true) {
            System.out.println("Student Information System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter course: ");
                    String course = scanner.nextLine();
                    try {
                        c.addStudent(name, studentId, course);
                    } catch (CourseFullException ae) {
                        System.err.println("Error: " + ae.getMessage());
                    } catch (DuplicateEnrollmentException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    c.view();
                    break;

                case 3:
                    System.out.print("Enter student ID to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    c.search(searchId);
                    break;

                case 4:
                    System.out.print("Enter student name to remove: ");
                    String n = scanner.nextLine();
                    try {
                        c.delete(n);
                    } catch (InvalidStudent i) {
                        System.err.println("Error: " + i.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Exiting Student Information System.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
