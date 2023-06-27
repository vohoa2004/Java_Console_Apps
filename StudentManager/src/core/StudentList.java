package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyUtil;

public class StudentList extends ArrayList<Student> {

    private static final String STUDENT_DATA_FILE = "src\\data\\student.txt";

    public void readFromFile() {
        BufferedReader reader;
        String line;
        File file = new File(STUDENT_DATA_FILE);
        if (!file.exists()) {
            System.out.println("Student data file does not exist");
            System.exit(0);
        }
        try {
            reader = new BufferedReader(new FileReader(file));

            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(", ");
                String studentID = row[0];
                String studentName = row[1];
                String dob = row[2];
                String email = row[3];
                String phoneNumber = row[4];
                Student st = new Student(studentID, studentName, dob, email, phoneNumber);
                this.add(st);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile() {
        try {
            PrintWriter out = new PrintWriter(STUDENT_DATA_FILE);
            out.println("id, name, dob, email, phone");
            for (Student student : this) {
                out.println(student.getStudentID() + ", " + student.getStudentName() + ", "
                        + student.getDob() + ", " + student.getEmail() + ", " + student.getPhoneNumber());
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createStudent() {

        String studentID;
        do {
            studentID = MyUtil.inputString("Enter a studentID: ");
        } while (MyUtil.validateID(studentID) == false);

        String studentName = MyUtil.inputString("Enter your full name: ");

        String dob;
        do {
            dob = MyUtil.inputString("Enter your day of birth: ");
        } while (MyUtil.validateDob(dob) == false);

        String email;
        do {
            email = MyUtil.inputString("Enter your email: ");
        } while (MyUtil.validateEmail(email) == false);

        String phoneNumber;
        do {
            phoneNumber = MyUtil.inputString("Enter your phone number: ");
        } while (MyUtil.validatePhone(phoneNumber) == false);

        Student newStudent = new Student(studentID, studentName, dob, email, phoneNumber);
        this.add(newStudent);
        System.out.println("Create successfully");
    }

    public Student readStudentInfor() {
        String studentID;
        studentID = MyUtil.inputString("Enter student ID: ");
        for (Student student : this) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudentInfor() {
        String studentID;
        studentID = MyUtil.inputString("Enter student ID: ");
        for (Student student : this) {
            if (student.getStudentID().equals(studentID)) {
                String newName = MyUtil.inputString("Enter new name: ");
                student.setStudentName(newName);

                String newDob = MyUtil.inputString("Enter new date of birth(dd/mm/yy: ");
                student.setDob(newDob);

                String newEmail = MyUtil.inputString("Enter new email: ");
                student.setEmail(newEmail);

                String newPhone = MyUtil.inputString("Enter new phone: ");
                student.setPhoneNumber(newPhone);

                System.out.println("Student information update successfully");
            }
            System.out.println("Student not found");
        }
    }

    public void deleteStudent() {
        String studentID;
        studentID = MyUtil.inputString("Enter student ID: ");
        for (Student student : this) {
            if (student.getStudentID().equals(studentID)) {
                student.remove();
                System.out.println("Student deleted successfully");
            }
            System.out.println("Student not found");
        }
    }

    public void showStudentList() {
        for (Student x : this) {
            System.out.println(x);
        }

    }
}
