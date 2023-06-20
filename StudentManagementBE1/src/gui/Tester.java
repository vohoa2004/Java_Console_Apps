///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package gui;

import core.Student;
import core.StudentList;
import core.SubjectList;
import core.User;
import core.UserList;
import java.util.ArrayList;
import utils.MyUtil;

///**
// *
// * @author admin
// */
public class Tester {

        public static void main(String[] args) {
                UserList userList = new UserList();
                SubjectList subjectList = new SubjectList();
                StudentList studentList = new StudentList();

                userList.readFromFile();
                subjectList.readFromFile();
                studentList.readFromFile();

                ArrayList<String> Menu = new ArrayList<>();
                Menu.add("Login");
                Menu.add("Register");
                Menu.add("Create Subject");
                Menu.add("Search subject by ID");
                Menu.add("Update Subject By ID");
                Menu.add("Delete Subject By ID");
                Menu.add("Show subject list");
                
                Menu.add("Create a student");
                Menu.add("Search student by ID");
                Menu.add("Update student information");
                Menu.add("Delete student");
                Menu.add("Show student list");
                
                Menu.add("Exit");

                Integer choice;
                do {
                        System.out.println("==================================");
                        for (int i = 0; i < Menu.size(); i++) {
                                System.out.println(i + 1 + ". " + Menu.get(i));
                        }
                        System.out.println("==================================");
                        choice = MyUtil.inputInteger("Please enter your choice: ", 1, 13);

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
                                        break;
                                case 4:
                                        subjectList.searchSubjectByID();
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
                                                System.out.println("Your info: ");
                                                System.out.println(readInfor.getStudentID() + ", "
                                                          + readInfor.getStudentName() + ", " + readInfor.getDob()
                                                          + ", " + readInfor.getEmail() + ", " + readInfor.getPhoneNumber());
                                        }
                                        break;
                                }

                                case 10: {
                                        studentList.updateStudentInfor();
                                        studentList.writeToFile();
                                        break;
                                }

                                case 11: {
                                        studentList.deleteStudent();
                                        studentList.writeToFile();
                                        break;
                                }

                                case 12: {
                                        studentList.showStudentList();
                                        break;
                                }
                                case 13: {
                                        System.exit(0);
                                }
                                default:
                                        System.out.println("Invalid choice. Please try again.");
                                        break;
                        }

                } while (choice != 13);

                userList.writeToFile();
                subjectList.writeToFile();
                
        }
}
