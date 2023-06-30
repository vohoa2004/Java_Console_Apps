package core;

import java.util.ArrayList;

public class Student {

        private String studentID;
        private String studentName;
        private String dob;
        private String email;
        private String phoneNumber;
        private ArrayList<String> subjectIDs;

        public Student() {

        }

        public Student(String studentID, String studentName, String dob, String email, String phoneNumber) {
                this.studentID = studentID;
                this.studentName = studentName;
                this.dob = dob;
                this.email = email;
                this.phoneNumber = phoneNumber;

        }

        public Student(String studentID, String studentName, String dob, String email, String phoneNumber, ArrayList<String> subjectIDs) {
                this.studentID = studentID;
                this.studentName = studentName;
                this.dob = dob;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this.subjectIDs = subjectIDs;
        }

        public String getStudentID() {
                return studentID;
        }

        public void setStudentID(String studentID) {
                this.studentID = studentID;
        }

        public String getStudentName() {
                return studentName;
        }

        public void setStudentName(String studentName) {
                this.studentName = studentName;
        }

        public String getDob() {
                return dob;
        }

        public void setDob(String dob) {
                this.dob = dob;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public ArrayList<String> getSubjectIDs() {
                return subjectIDs;
        }

        public void setSubjectIDs(ArrayList<String> subjectIDs) {
                this.subjectIDs = subjectIDs;
        }

        @Override
        public String toString() {
                return String.format("Student ID: %-10s Student Name: %-20s Birth Date: %-15s Email: %-30s Phone Number: %-15s Enrolled Subject IDs: %s",
            studentID, studentName, dob, email, phoneNumber, subjectIDs);
        }

        public void joinSubject(String id) {
                this.subjectIDs.add(id);
        }

}
