package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyUtil;

public class Transcript extends ArrayList<GradeItem> {

        private static final String FILEGRADE = "src/data/transcript.txt";

        public void readFromFile() {
                try {
                        File file = new File(FILEGRADE);
                        if (!file.exists()) {
                                System.out.println("Grade data file does not exist");
                                System.exit(0);
                        }

                        HashMap<String, HashMap<String, HashMap<String, Double>>> hashTranscript = new HashMap<>();

                        File transcript = new File(FILEGRADE);
                        if (transcript.exists()) {
                                try (BufferedReader myInput = new BufferedReader(new FileReader(transcript))) {
                                        String thisLineTranscript;
                                        while ((thisLineTranscript = myInput.readLine()) != null) {
                                                if (!thisLineTranscript.trim().isEmpty()) {
                                                        String[] split = thisLineTranscript.split(", "); // split when encountering a comma
                                                        String studentID = split[0].trim();
                                                        String subjectID = split[1].trim();
                                                        HashMap<String, HashMap<String, Double>> studentGrades = hashTranscript.getOrDefault(studentID, new HashMap<>());
                                                        HashMap<String, Double> subjectGrades = studentGrades.getOrDefault(subjectID, new HashMap<>());

                                                        for (int i = 2; i < split.length; i++) {
                                                                if (split[i].contains(":")) { // Check if the element contains a colon
                                                                        String[] gradePair = split[i].split(":");
                                                                        String gradeType = gradePair[0].trim();
                                                                        double grade = Double.parseDouble(gradePair[1].trim());
                                                                        subjectGrades.put(gradeType, grade);
                                                                }
                                                        }

                                                        studentGrades.put(subjectID, subjectGrades);
                                                        hashTranscript.put(studentID, studentGrades);
                                                }
                                        }
                                        myInput.close();
                                }
                        }

                        // Convert the hashTranscript to GradeItem objects and add them to the Transcript
                        for (String studentID : hashTranscript.keySet()) {
                                HashMap<String, HashMap<String, Double>> studentGrades = hashTranscript.get(studentID);
                                for (String subjectID : studentGrades.keySet()) {
                                        HashMap<String, Double> subjectGrades = studentGrades.get(subjectID);
                                        double labGrade = subjectGrades.getOrDefault("labGrade", 0.0);
                                        double progressTestGrade = subjectGrades.getOrDefault("progressTestGrade", 0.0);
                                        double finalTestGrade = subjectGrades.getOrDefault("finalTestGrade", 0.0);
                                        double averageGrade = (labGrade + progressTestGrade + finalTestGrade) / 3;

                                        GradeItem gradeItem = new GradeItem(studentID, subjectID, labGrade, progressTestGrade, finalTestGrade, averageGrade);
                                        this.add(gradeItem);
                                }
                        }
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

        public void writeToFile() {
                try {
                        PrintWriter out = new PrintWriter(FILEGRADE);

                        for (GradeItem grade : this) {
                                out.print(grade.getStudentID() + ", ");
                                out.print(grade.getSubjectID() + ", ");
                                out.print("labGrade:" + grade.getLabGrade() + ", ");
                                out.print("progressTestGrade:" + grade.getProgressTestGrade() + ", ");
                                out.print("finalTestGrade:" + grade.getFinalTestGrade());
                                out.println();
                        }

                        out.close();
                } catch (FileNotFoundException ex) {
                        Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

        public ArrayList<String> printUngradedSubjects(String studentID, StudentList studentList) {

                boolean found = false;
                HashSet<String> gradedSubjects = new HashSet<>();
                ArrayList<String> subjectIds = new ArrayList<>();
                try {
                        for (GradeItem grade : this) {
                                if (grade.getStudentID().equals(studentID)) {
                                        gradedSubjects.add(grade.getSubjectID());
                                }
                        }
                        for (String subjectID : studentList.searchStudent(studentID).getSubjectIDs()) {
                                if (!gradedSubjects.contains(subjectID)) {

                                        found = true;
                                        subjectIds.add(subjectID);
                                }
                        }

                } catch (NullPointerException e) {
                        System.out.println("This student hasn't enrolled in any subjects.");

                }
                return subjectIds;
        }

        // them exception nullpointer 
        public void createGrade(StudentList studentList) {
                boolean isFound = false;
                String studentID = MyUtil.inputString("Enter student ID: ");
                ArrayList<String> ungradedSubjects = printUngradedSubjects(studentID, studentList);

                if (ungradedSubjects.isEmpty()) {
                        System.out.println("No subject to create a transcript!");
                } else {
                        System.out.println("Available subjects for creating a transcript:");
                        for (String subjectID : ungradedSubjects) {
                                System.out.print(subjectID + ", ");
                        }

                        String subjectID = MyUtil.inputString("\nEnter subject ID: ");

                        for (Student student : studentList) {
                                if (student.getStudentID().equals(studentID) && student.getSubjectIDs().contains(subjectID)) {
                                        double labGrade = MyUtil.inputDouble("Enter lab grade: ", 0, 10);
                                        double progressTestGrade = MyUtil.inputDouble("Enter progress test grade: ", 0, 10);
                                        double finalTestGrade = MyUtil.inputDouble("Enter final test grade: ", 0, 10);
                                        double averageGrade = (labGrade + progressTestGrade + finalTestGrade) / 3;

                                        GradeItem gradeItem = new GradeItem(studentID, subjectID, labGrade, progressTestGrade, finalTestGrade, averageGrade);
                                        this.add(gradeItem);
                                        System.out.println("Grade created successfully");
                                        isFound = true;
                                        break; // Exit the loop after finding a matching student
                                }
                        }
                        if (!isFound) {
                                System.out.println("This student or subject does not exist or this student is not studying this subject!");
                        }
                }

        }

        public void updateGrade(StudentList studentList) {
                boolean isFound = false;
                String studentID = MyUtil.inputString("Enter student ID: ");
                String subjectID = MyUtil.inputString("Enter subject ID: ");

                for (GradeItem x : this) {
                        if (x.getStudentID().equals(studentID) && x.getSubjectID().equals(subjectID)) {
                                double labGrade = MyUtil.inputDouble("Enter new lab grade: ", 0, 10);
                                double progressTestGrade = MyUtil.inputDouble("Enter new progress test grade: ", 0, 10);
                                double finalTestGrade = MyUtil.inputDouble("Enter new final test grade: ", 0, 10);
                                double averageGrade = (labGrade + progressTestGrade + finalTestGrade) / 3;
                                x.setProgressTestGrade(progressTestGrade);
                                x.setLabGrade(labGrade);
                                x.setFinalTestGrade(finalTestGrade);
                                x.setAverageGrade(averageGrade);
                                System.out.println("Grade updated successfully");
                                isFound = true;
                                break; // Exit the loop after finding a matching student
                        }
                }

                if (!isFound) {
                        System.out.println("This student is not studying this subject or the transcript hasn't been created!");
                }
        }

        public void displayGradeList(String id) {
                System.out.println("Grade List:");
                if (this.isEmpty()) {
                        System.out.println("No grades available");
                } else {
                        boolean isFound = false;
                        for (GradeItem gradeItem : this) {
                                if (gradeItem.getStudentID().equals(id)) {
                                        System.out.printf("Subject: %-10s", gradeItem.getSubjectID());
                                        System.out.printf("Lab Grade: %-8s", gradeItem.getLabGrade());
                                        System.out.printf("Progress Test Grade: %-8s", gradeItem.getProgressTestGrade());
                                        System.out.printf("Final Test Grade: %-8s", gradeItem.getFinalTestGrade());
                                        System.out.printf("Average Grade: %.2f\n", gradeItem.getAverageGrade());
                                        isFound = true;
                                }
                        }
                        if (isFound == false) {
                                System.out.println("No grades for this student!");
                        }
                }
        }

}
