/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.util.HashMap;
import java.util.Map;

public class Transcript {
    private StudentList studentList;
    private SubjectList subjectList;
    private Map<String, Map<String, Double>> grades;

    public Transcript(StudentList studentList, SubjectList subjectList) {
        this.studentList = studentList;
        this.subjectList = subjectList;
        this.grades = new HashMap<>();
    }

    public void addGrade(String studentID, String subjectID, double grade) {
        Map<String, Double> studentGrades = grades.getOrDefault(studentID, new HashMap<>());
        studentGrades.put(subjectID, grade);
        grades.put(studentID, studentGrades);
    }

    public double getGrade(String studentID, String subjectID) {
        Map<String, Double> studentGrades = grades.get(studentID);
        if (studentGrades != null) {
            return studentGrades.getOrDefault(subjectID, 0.0);
        }
        return 0.0;
    }

    public void displayGradesByStudentName(String studentName) {
        for (Student student : studentList) {
            if (student.getStudentName().equals(studentName)) {
                String studentID = student.getStudentID();
                Map<String, Double> studentGrades = grades.get(studentID);
                if (studentGrades != null) {
                    System.out.println("Grades for Student " + studentID + ":");
                    for (Map.Entry<String, Double> entry : studentGrades.entrySet()) {
                        String subjectID = entry.getKey();
                        double grade = entry.getValue();
                        System.out.println("Subject: " + subjectID + ", Grade: " + grade);
                    }
                } else {
                    System.out.println("No grades found for Student " + studentID);
                }
                return;
            }
        }
        System.out.println("Student not found");
    }

    public void displayGradesBySubjectName(String subjectName) {
        for (Subject subject : subjectList) {
            if (subject.getSubjectName().equals(subjectName)) {
                String subjectID = subject.getSubjectID();
                System.out.println("Grades for Subject " + subjectID + ":");
                for (Map.Entry<String, Map<String, Double>> entry : grades.entrySet()) {
                    String studentID = entry.getKey();
                    Map<String, Double> studentGrades = entry.getValue();
                    if (studentGrades.containsKey(subjectID)) {
                        double grade = studentGrades.get(subjectID);
                        System.out.println("Student: " + studentID + ", Grade: " + grade);
                    }
                }
                return;
            }
        }
        System.out.println("Subject not found");
    }
}