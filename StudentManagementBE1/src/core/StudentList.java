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
                boolean isUnique;
                // ID
                String studentID;
                do {
                        isUnique = true;
                        studentID = MyUtil.inputString("Input student ID: ");
                        for (Student i : this) {

                                if (i.getStudentID().equals(studentID)) {
                                        //System.out.println(x.toString());
                                        isUnique = false;

                                }
                        }
                        if (isUnique == false) {
                                System.out.println("This ID is existed");
                        }
                } while (isUnique == false && MyUtil.validateID(studentID) == false);
                // name
                String studentName = MyUtil.inputString("Enter your full name: ");

                // dob
                String dob;
                do {
                        dob = MyUtil.inputString("Enter your day of birth: ");
                } while (MyUtil.validateDob(dob) == false);

                String email;
                do {
                        email = MyUtil.inputString("Enter your email: ");
                } while (MyUtil.validateEmail(email) == false);

                //phone number
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
                boolean isFound = false;
                studentID = MyUtil.inputString("Enter student ID you want to update: ");
                for (Student student : this) {
                        if (student.getStudentID().equals(studentID)) {
                                isFound = true;
                                // ID
                                String newStudentID;
                                do {
                                        newStudentID = MyUtil.inputString("Enter new studentID: ");
                                } while (MyUtil.validateID(newStudentID) == false);

                                // name
                                String studentName = MyUtil.inputString("Enter new name: ");

                                // dob
                                String dob;
                                do {
                                        dob = MyUtil.inputString("Enter new day of birth: ");
                                } while (MyUtil.validateDob(dob) == false);

                                String email;
                                do {
                                        email = MyUtil.inputString("Enter new email: ");
                                } while (MyUtil.validateEmail(email) == false);

                                //phone number
                                String phoneNumber;
                                do {
                                        phoneNumber = MyUtil.inputString("Enter new phone number: ");
                                } while (MyUtil.validatePhone(phoneNumber) == false);

                                student.setDob(dob);
                                student.setEmail(email);
                                student.setPhoneNumber(phoneNumber);
                                student.setStudentID(newStudentID);
                                student.setStudentName(studentName);

                                System.out.println("Student information update successfully");
                        }

                }
                if (isFound == false) {
                        System.out.println("Student not found");
                }
        }

        public boolean deleteStudent() {
                String id = MyUtil.inputString("Enter ID of the subject you want to delete: ");
                for (Student x : this) {
                        if (x.getStudentID().equals(id)) {
                                this.remove(x);
                                System.out.println("Student deleted successfully");
                                return true;
                                
                        }
                }
                return false;
        }
        
        public void showStudentList() {
                System.out.println("Student List: ");
                for (Student x : this) {
                        System.out.println(x);
                }
        }
}
