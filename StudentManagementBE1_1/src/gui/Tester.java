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
                
                //login
                Menu.add("Login");
                Menu.add("Register");
                
                //subject
                Menu.add("Create Subject");
                Menu.add("Search subject by ID");
                Menu.add("Update Subject By ID");
                Menu.add("Delete Subject By ID");
                Menu.add("Show subject list");

                //student
                Menu.add("Create a student");
                Menu.add("Search student by ID");
                Menu.add("Update student information");
                Menu.add("Delete student");
                Menu.add("Show student list");
                Menu.add("Let student learn subject");

                //transcript by student
                Menu.add("Show transcript by student");
                Menu.add("Enter grade of ungraded subject by student ID");
                Menu.add("Update grade by student ID");
                
                //transcript by subject

                Menu.add("Exit");

                Integer choice;
                do {
                        System.out.println("==================================");
                        for (int i = 0; i < Menu.size(); i++) {
                                System.out.println(i + 1 + ". " + Menu.get(i));
                        }
                        System.out.println("==================================");
                        choice = MyUtil.inputInteger("Please enter your choice: ", 1, 17);

                        switch (choice) {
                                case 1:
                                        User loginUser = userList.login();
                                        if (loginUser == null) {
                                                System.out.println("Wrong username or password");
                                        } else {
                                                System.out.println("Login success");
                                                System.out.println("Your info: ");
                                                System.out.println(loginUser.getUsername() + ", " + loginUser.getPassword() + ", " + loginUser.getFullName() + ", " + loginUser.getEmail());
                                        }
                                        break;
                                case 2:
                                        userList.register();
                                        break;
                                case 3:
                                        subjectList.createSubject();
                                        subjectList.writeToFile();
                                        break;
                                case 4:

                                        Subject x = subjectList.searchSubjectByID();
                                        if (x == null) {
                                                System.out.println("Wrong subject ID");
                                        } else {

                                                System.out.println(x.toString());
                                        }
                                        break;

                                case 5:
                                        if (!subjectList.updateSubject()) {
                                                System.out.println("Subject not found!");
                                        }
                                        break;
                                case 6:
                                        if (!subjectList.deleteSubject()) {
                                                System.out.println("Subject not found!");
                                        }
                                        subjectList.writeToFile();
                                        break;
                                case 7: {
                                        subjectList.displaySubjectList();
                                        break;
                                }
                                case 8:
                                        studentList.createStudent();
                                        studentList.writeToFile();
                                        break;
                                case 9: {
                                        Student readInfor = studentList.readStudentInfor();
                                        if (readInfor == null) {
                                                System.out.println("Wrong student ID");
                                        } else {
                                                System.out.println("Student info: ");
                                                System.out.println(readInfor.toString());
                                        }
                                        break;
                                }

                                case 10: {
                                        studentList.updateStudentInfor();
                                        studentList.writeToFile();
                                        break;
                                }

                                case 11: {
                                        if (!studentList.deleteStudent()) {
                                                System.out.println("Student not found!");
                                        }
                                        studentList.writeToFile();
                                        break;
                                }

                                case 12: {
                                        studentList.showStudentList();
                                        break;
                                }

                                // cho mot hs theo id join mot mon hoc theo id
                                case 13: {
                                        studentList.studentJoinSubject(subjectList);
                                        studentList.writeToFile();
                                        studentList.writeSubjectByStudentToFile();
                                        break;
                                }

                                case 14: {
                                        String id = MyUtil.inputString("Enter student ID: ");
                                        transcript.displayGradeList(id);
                                        break;
                                }
                                case 15: {
                                        // tao transcript moi cho mot mon da hoc nhung chua co diem                                        
                                        transcript.createGrade(studentList);
                                        transcript.writeToFile();
                                        break;
                                }
                                case 16: {
                                        // thay doi diem so
                                        transcript.updateGrade(studentList);
                                        transcript.writeToFile();
                                        break;
                                }
                                case 17: {
                                        System.exit(0);
                                }
                                default:
                                        System.out.println("Invalid choice. Please try again.");
                                        break;
                        }
                } while (choice != 17);
                userList.writeToFile();
                subjectList.writeToFile();
        }
}
