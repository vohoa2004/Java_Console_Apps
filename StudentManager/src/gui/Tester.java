package gui;

import core.Student;
import core.StudentList;
import utils.MyUtils;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        StudentList studentList = new StudentList();
        studentList.readFromFile();

        System.out.println("Welcome to Student Management System");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create a student");
            System.out.println("2. Read student information");
            System.out.println("3. Update student information");
            System.out.println("4. Delete student");
            System.out.println("5. Show student list");
            System.out.println("0. Exit");

            int choice = MyUtils.inputInteger("Please enter your choice: ", 0, 5);

            switch (choice) {
                case 1:
                    studentList.createStudent();
                    studentList.writeToFile();
                    break;
                case 2:
                    Student student = studentList.readStudentInfor(MyUtils.inputString("Enter student ID: "));
                    if (student != null) {
                        System.out.println("Student Information:");
                        System.out.println(student.toString());
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                case 3:
                    studentList.updateStudentInformation();
                    studentList.writeToFile();
                    break;
                case 4:
                    studentList.deleteStudent();
                    studentList.writeToFile();
                    break;
                case 5:
                    studentList.showStudentList();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}
