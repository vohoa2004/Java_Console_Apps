package gui;

import core.Student;
import core.StudentList;
import java.util.ArrayList;
import utils.MyUtil;

/**
 *
 * @author admin
 */
public class Tester {
    public static void main(String[] args) {
        StudentList studentList = new StudentList();
        studentList.readFromFile();
        ArrayList<String> Menu = new ArrayList<>();
        Menu.add("Create a student");
        Menu.add("Read student information");
        Menu.add("Update student information");
        Menu.add("Dlete student");
        Menu.add("Show student list");
        for(int i = 0; i < Menu.size(); i++) {
            System.out.println(i+1 + ". " + Menu.get(i));
        }
        Integer choice = MyUtil.inputInteger("Please enter your choice:", 1, 5);
        
         if(choice == 1) {
            studentList.createStudent();
            studentList.writeToFile();
        }
        if(choice == 2) {
            Student readInfor = studentList.readStudentInfor();
            if(readInfor == null) {
                System.out.println("Wrong studentID");
            } else {   
                System.out.println("Your info: ");
                System.out.println(readInfor.getStudentID() + ", " + 
                readInfor.getStudentName() + ", " + readInfor.getDob() + 
                        ", " + readInfor.getEmail()+  ", " + readInfor.getPhoneNumber());
            }
        }
        if(choice==3){
            studentList.updateStudentInfor();
            studentList.writeToFile();
        }
        if(choice==4){
            studentList.deleteStudent();
            studentList.writeToFile();
        }
        if(choice==5){
            studentList.showStudentList();
        }
    }
}
