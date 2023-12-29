import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        // Load existing student data from the file at the start
        readStudentDataFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        // Update the file after adding a new student
        writeStudentDataToFile();
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
        // Update the file after removing a student
        writeStudentDataToFile();
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        System.out.println("List of Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void readStudentDataFromFile() {
        try (Scanner scanner = new Scanner(new File("students.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String name = data[0];
                int rollNumber = Integer.parseInt(data[1]);
                String grade = data[2];
                students.add(new Student(name, rollNumber, grade));
            }
        } catch (FileNotFoundException e) {
            // Ignore if the file does not exist initially
        }
    }

    private void writeStudentDataToFile() {
        try (PrintWriter writer = new PrintWriter("students.txt")) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class Task_3_StudentManagementSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewStudent(scanner, studentManagementSystem);
                    break;
                case 2:
                    removeStudent(scanner, studentManagementSystem);
                    break;
                case 3:
                    searchForStudent(scanner, studentManagementSystem);
                    break;
                case 4:
                    studentManagementSystem.displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting the Student Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void addNewStudent(Scanner scanner, StudentManagementSystem studentManagementSystem) {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();
        System.out.print("Enter student grade: ");
        String grade = scanner.next();

        Student newStudent = new Student(name, rollNumber, grade);
        studentManagementSystem.addStudent(newStudent);

        System.out.println("Student added successfully!");
    }

    private static void removeStudent(Scanner scanner, StudentManagementSystem studentManagementSystem) {
        System.out.print("Enter the roll number of the student to remove: ");
        int rollNumber = scanner.nextInt();

        Student studentToRemove = studentManagementSystem.searchStudent(rollNumber);
        if (studentToRemove != null) {
            studentManagementSystem.removeStudent(rollNumber);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void searchForStudent(Scanner scanner, StudentManagementSystem studentManagementSystem) {
        System.out.print("Enter the roll number of the student to search: ");
        int rollNumber = scanner.nextInt();

        Student foundStudent = studentManagementSystem.searchStudent(rollNumber);
        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }
}
