package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.MyUtils;

public class StudentList {
    private static final String STUDENT_DATA_FILE = "src\\data\\student.txt";
    private List<Student> students;

    public StudentList() {
        students = new ArrayList<>();
    }

    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_DATA_FILE))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(", ");
                String studentID = row[0];
                String studentName = row[1];
                String dob = row[2];
                String email = row[3];
                String phoneNumber = row[4];
                Student student = new Student(studentID, studentName, dob, email, phoneNumber);
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the student data file: " + e.getMessage());
        }
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_DATA_FILE))) {
            writer.write("id, name, dob, email, phone");
            writer.newLine();
            for (Student student : students) {
                writer.write(student.getStudentID() + ", " + student.getStudentName() + ", "
                        + student.getDob() + ", " + student.getEmail() + ", " + student.getPhoneNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the student data file: " + e.getMessage());
        }
    }

    public void createStudent() {
        String studentID;
        do {
            studentID = MyUtils.inputString("Enter a student ID: ");
        } while (!MyUtils.validateID(studentID));

        String studentName = MyUtils.inputString("Enter your full name: ");

        String dob;
        do {
            dob = MyUtils.inputString("Enter your date of birth (dd/mm/yy): ");
        } while (!MyUtils.validateDob(dob));

        String email;
        do {
            email = MyUtils.inputString("Enter your email: ");
        } while (!MyUtils.validateEmail(email));

        String phoneNumber;
        do {
            phoneNumber = MyUtils.inputString("Enter your phone number: ");
        } while (!MyUtils.validatePhone(phoneNumber));

        Student newStudent = new Student(studentID, studentName, dob, email, phoneNumber);
        students.add(newStudent);
        System.out.println("Student created successfully");
    }

    public Student readStudentInfor(String studentID) {
        
        studentID = MyUtils.inputString("Enter student ID: ");
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudentInformation() {
        String studentID = MyUtils.inputString("Enter student ID: ");
        Student student = readStudentInfor(studentID);
        if (student != null) {
            String newName = MyUtils.inputString("Enter new name: ");
            student.setStudentName(newName);

            String newDob = MyUtils.inputString("Enter new date of birth (dd/mm/yy): ");
            student.setDob(newDob);

            String newEmail = MyUtils.inputString("Enter new email: ");
            student.setEmail(newEmail);

            String newPhone = MyUtils.inputString("Enter new phone number: ");
            student.setPhoneNumber(newPhone);

            System.out.println("Student information updated successfully");
        } else {
            System.out.println("Student not found");
        }
    }

    public void deleteStudent() {
        String studentID = MyUtils.inputString("Enter student ID: ");
        Student student = readStudentInfor(studentID);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }
    }

    public void showStudentList() {
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}
