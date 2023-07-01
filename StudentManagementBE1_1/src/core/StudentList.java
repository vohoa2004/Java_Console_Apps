package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyUtil;
import core.SubjectList;
import java.util.List;

public class StudentList extends ArrayList<Student> {

        private static final String STUDENT_DATA_FILE = "src\\data\\student.txt";
        private static final String SUBJECT_BY_STUDENT_FILE = "src\\data\\studentLearnSubjects.txt";
        // hoi lai anh Tho cach doc va ghi file

        public StudentList() {
        }

        public void readFromFile() {
                try {
                        File file = new File(STUDENT_DATA_FILE);
                        if (!file.exists()) {
                                System.out.println("Student data file does not exist");
                                System.exit(0);
                        }

                        HashMap<String, ArrayList<String>> hashSubject = new HashMap<>();

                        File studentSubject = new File(SUBJECT_BY_STUDENT_FILE);
                        if (studentSubject.exists()) {
                                try (BufferedReader myInput = new BufferedReader(new FileReader(studentSubject))) {
                                        String thisLineSubject;
                                        while ((thisLineSubject = myInput.readLine()) != null) {
                                                if (!thisLineSubject.trim().isEmpty()) {
                                                        String[] split = thisLineSubject.split(", "); // split when encountering a comma
                                                        String key = split[0].trim();
                                                        ArrayList<String> suInfo = new ArrayList<>();
                                                        for (int i = 1; i < split.length; i++) {
                                                                suInfo.add(split[i].trim());
                                                        }
                                                        hashSubject.put(key, suInfo);
                                                }
                                        }
                                        myInput.close();
                                }
                        }

                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                                String line = reader.readLine(); // skip the header line
                                while ((line = reader.readLine()) != null) {
                                        String[] row = line.split(", ");
                                        String studentID = row[0];
                                        String studentName = row[1];
                                        String dob = row[2];
                                        String email = row[3];
                                        String phoneNumber = row[4];
                                        ArrayList<String> subjectIDs = hashSubject.get(studentID);
                                        Student student = new Student(studentID, studentName, dob, email, phoneNumber, subjectIDs);
                                        this.add(student);
                                }
                                reader.close();
                        }
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

        public void writeToFile() {
                try {
                        PrintWriter out = new PrintWriter(STUDENT_DATA_FILE);
                        out.println("id, name, dob, email, phone, subject joined");
                        for (Student student : this) {
                                out.println(student.getStudentID() + ", " + student.getStudentName() + ", "
                                          + student.getDob() + ", " + student.getEmail() + ", " + student.getPhoneNumber() + ", " + student.getSubjectIDs());
                        }
                        out.close();
                } catch (FileNotFoundException ex) {
                        Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

        public void writeSubjectByStudentToFile() {
                try {
                        PrintWriter out = new PrintWriter(new FileWriter(SUBJECT_BY_STUDENT_FILE));
                        for (Student student : this) {
                                out.print(student.getStudentID() + ", ");
                                ArrayList<String> subjectIDs = student.getSubjectIDs();
                                if (subjectIDs != null && !subjectIDs.isEmpty()) {
                                        out.println(String.join(", ", subjectIDs));
                                } else {
                                        out.println();
                                }
                        }
                        out.close();
                } catch (IOException ex) {
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
                } while (isUnique == false || MyUtil.validateID(studentID) == false);
                // name
                String studentName = MyUtil.inputString("Enter your full name: ");

                // dob - them phan check ngay hop le
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
                                boolean isUnique;
                                // ID
                                String newStudentID;
                                do {
                                        isUnique = true;
                                        newStudentID = MyUtil.inputString("Input new student ID (must be unique): ");
                                        if (newStudentID.equals(studentID)) {
                                                isUnique = true;
                                        } else {
                                                for (Student i : this) {

                                                        if (i.getStudentID().equals(newStudentID)) {
                                                                //System.out.println(x.toString());
                                                                isUnique = false;

                                                        }
                                                }
                                                if (isUnique == false) {
                                                        System.out.println("This ID is existed");
                                                }
                                        }
                                } while (isUnique == false || MyUtil.validateID(studentID) == false);

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
                String id = MyUtil.inputString("Enter ID of the student you want to delete: ");
                for (Student x : this) {
                        if (x.getStudentID().equals(id)) {
                                List<String> subjectIDs = x.getSubjectIDs();
                                if (subjectIDs == null || subjectIDs.isEmpty()) {
                                        this.remove(x);
                                        System.out.println("Student deleted successfully");
                                } else {
                                        System.out.println("Cannot delete because this student has learned some subjects");
                                }
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

        public Student searchStudent(String id) {
                for (Student x : this) {
                        if (id.equals(x.getStudentID())) {
                                return x;
                        }
                }
                return null;
        }

        // bo sung check unique
        public void studentJoinSubject(SubjectList subjectList) {
                String studentId = MyUtil.inputString("Enter student ID: ");
                String subjectId = MyUtil.inputString("Enter subject ID: ");

                Student student = searchStudent(studentId);
                Subject subject = subjectList.searchSubject(subjectId);

                if (student != null && subject != null) {
                        if (student.getSubjectIDs() == null) {
                                student.setSubjectIDs(new ArrayList<>()); // Initialize an empty subject list if it's null
                        }
                        if (student.getSubjectIDs().contains(subjectId)) {
                                System.out.println("This student has already learned the subject!");
                        } else {
                                student.joinSubject(subjectId);
                                System.out.println("This student joined the subject!");
                        }
                } else {
                        System.out.println("Student ID or subject ID not found!");
                }
        }

}