package gui;

import core.Student;
import core.StudentList;
import core.Subject;
import core.SubjectList;
import core.Transcript;
import core.User;
import core.UserList;
import java.util.ArrayList;
import utils.MyUtil;

public class Tester {

        public static Integer getChoice() {
                ArrayList<String> Menu = new ArrayList<>();

                //login
                Menu.add("Login");
                Menu.add("Register");
                Menu.add("Exit");
                System.out.println("================= HOME PAGE =================");
                for (int i = 0; i < 3; i++) {
                        System.out.println(i + 1 + ". " + Menu.get(i));
                }
                System.out.println("=============================================");
                Integer choice = MyUtil.inputInteger("Please enter your choice: ", 1, 3);
                return choice;
        }

        public static void main(String[] args) {
                UserList userList = new UserList();
                SubjectList subjectList = new SubjectList();
                StudentList studentList = new StudentList();
                Transcript transcript = new Transcript();

                userList.readFromFile();
                subjectList.readFromFile();
                studentList.readFromFile();
                transcript.readFromFile();

                ArrayList<String> Menu = new ArrayList<>();
                Menu.add("Login");
                Menu.add("Register");
                Menu.add("Exit");

                //student
                Menu.add("Create a student");
                Menu.add("Search student by ID");
                Menu.add("Update student information");
                Menu.add("Delete student");
                Menu.add("Show student list");

                //subject
                Menu.add("Create Subject");
                Menu.add("Search subject by ID");
                Menu.add("Update Subject By ID");
                Menu.add("Delete Subject By ID");
                Menu.add("Show subject list");

                Menu.add("Let student enrol a subject");

                //transcript by student
                Menu.add("Show transcript by student");
                Menu.add("Enter grade of ungraded subject by student ID");
                Menu.add("Update grade by student ID");
                Menu.add("Exit");
                Integer choice;
                do {
                        choice = getChoice();
                        switch (choice) {
                                case 1:
                                        User loginUser = userList.login();
                                        if (loginUser == null) {
                                                System.out.println("Wrong username or password");
                                        } else {
                                                Integer select;
                                                System.out.println("Login success");
                                                System.out.println("Your info: ");
                                                System.out.println(loginUser.getUsername() + ", " + loginUser.getPassword() + ", " + loginUser.getFullName() + ", " + loginUser.getEmail());
                                                do {
                                                        System.out.println("================= Student Management Menu =================");
                                                        System.out.println("A. STUDENT: ");
                                                        for (int i = 3; i < 8; i++) {
                                                                System.out.println(i - 2 + ". " + Menu.get(i));
                                                        }
                                                        System.out.println();
                                                        System.out.println("B. SUBJECT: ");
                                                        for (int i = 8; i < 13; i++) {
                                                                System.out.println(i - 2 + ". " + Menu.get(i));
                                                        }
                                                        System.out.println();
                                                        System.out.println(11 + ". " + Menu.get(13));
                                                        System.out.println();

                                                        System.out.println("C. TRANSCRIPT: ");
                                                        for (int i = 14; i < 17; i++) {
                                                                System.out.println(i - 2 + ". " + Menu.get(i));
                                                        }
                                                        System.out.println("");
                                                        System.out.println("15. " + Menu.get(17));
                                                        System.out.println("===========================================================");
                                                        select = MyUtil.inputInteger("Please enter your choice: ", 1, 15);
                                                        switch (select) {
                                                                // student
                                                                case 1:
                                                                        studentList.createStudent();
                                                                        studentList.writeToFile();
                                                                        break;
                                                                case 2: {
                                                                        Student readInfor = studentList.readStudentInfor();
                                                                        if (readInfor == null) {
                                                                                System.out.println("Wrong student ID");
                                                                        } else {
                                                                                System.out.println("Student info: ");
                                                                                System.out.println(readInfor.toString());
                                                                        }
                                                                        break;
                                                                }

                                                                case 3: {
                                                                        studentList.updateStudentInfor();
                                                                        studentList.writeToFile();
                                                                        break;
                                                                }

                                                                case 4: {
                                                                        if (!studentList.deleteStudent()) {
                                                                                System.out.println("Student not found!");
                                                                        }
                                                                        studentList.writeToFile();
                                                                        break;
                                                                }

                                                                case 5: {
                                                                        studentList.showStudentList();
                                                                        break;
                                                                }

                                                                case 6:
                                                                        subjectList.createSubject();
                                                                        subjectList.writeToFile();
                                                                        break;
                                                                case 7:

                                                                        Subject x = subjectList.searchSubjectByID();
                                                                        if (x == null) {
                                                                                System.out.println("Wrong subject ID");
                                                                        } else {

                                                                                System.out.println(x.toString());
                                                                        }
                                                                        break;

                                                                case 8:
                                                                        if (!subjectList.updateSubject()) {
                                                                                System.out.println("Subject not found!");
                                                                        }
                                                                        subjectList.writeToFile();
                                                                        break;
                                                                case 9:
                                                                        if (!subjectList.deleteSubject()) {
                                                                                System.out.println("Subject not found!");
                                                                        }
                                                                        subjectList.writeToFile();
                                                                        break;
                                                                case 10: {
                                                                        subjectList.displaySubjectList();
                                                                        break;
                                                                }

                                                                // cho mot hs theo id join mot mon hoc theo id
                                                                case 11: {
                                                                        studentList.studentJoinSubject(subjectList);
                                                                        studentList.writeToFile();
                                                                        studentList.writeSubjectByStudentToFile();
                                                                        break;
                                                                }

                                                                case 12: {
                                                                        String id = MyUtil.inputString("Enter student ID: ");
                                                                        transcript.displayGradeList(id);
                                                                        break;
                                                                }
                                                                case 13: {
                                                                        // tao transcript moi cho mot mon da hoc nhung chua co diem                                        
                                                                        transcript.createGrade(studentList);
                                                                        transcript.writeToFile();
                                                                        break;
                                                                }
                                                                case 14: {
                                                                        // thay doi diem so
                                                                        transcript.updateGrade(studentList);
                                                                        transcript.writeToFile();
                                                                        break;
                                                                }
                                                                case 15: {
                                                                        System.exit(0);
                                                                        break;
                                                                }
                                                        }
                                                } while (true);

                                        }
                                        break;
                                case 2:
                                        userList.register();
                                        userList.writeToFile();
                                        break;

                                case 3: {
                                        System.exit(0);
                                }

                        }
                } while (choice != 3);

        }
}
