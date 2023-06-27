package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyUtil;

public class Transcript extends ArrayList<GradeItem> {

    private static final String FILEGRADE = "src\\data\\transcript.txt";

    public void readFromFile() {
        BufferedReader reader;
        String line;
        File file = new File(FILEGRADE);
        if (!file.exists()) {
            System.out.println("Grade data file does not exist");
            System.exit(0);
        }
        try {
            reader = new BufferedReader(new FileReader(file));

            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(", ");
                if (row.length == 4) {
                    double labGrade = Double.parseDouble(row[0].trim());
                    double progressTestGrade = Double.parseDouble(row[1].trim());
                    double finalTestGrade = Double.parseDouble(row[2].trim());
                    double averageGrade = Double.parseDouble(row[3].trim());

                    GradeItem gradeItem = new GradeItem(labGrade, progressTestGrade, finalTestGrade, averageGrade);
                    this.add(gradeItem);
                }
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile() {
        try {
            PrintWriter out = new PrintWriter(FILEGRADE);
            out.println("labGrade, progressTestGrade,finalTestGrade,averaGrade");
            for (GradeItem grade : this) {
                out.println(grade.getLabGrade() + ", " + grade.getProgressTestGrade() + ", "
                        + grade.getFinalTestGrade() + ", " + grade.getAverageGrade());
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createGrade() {

        double labGrade = MyUtil.inputDouble("Enter lab grade:  ", 0, 10);
        double progressTestGrade = MyUtil.inputDouble("Enter progress test grade: ", 0, 10);
        double finalTestGrade = MyUtil.inputDouble("Enter final test grade: ", 0, 10);
        double averageGrade = (labGrade + progressTestGrade + finalTestGrade) / 3;
        GradeItem gradeItem = new GradeItem(labGrade, progressTestGrade, finalTestGrade, averageGrade);
        this.add(gradeItem);
        System.out.println("Grade created successfully");
    }

    public void displayGradeList() {
        System.out.println("Grade List:");
        if (isEmpty()) {
            System.out.println("No grades available");
        } else {
            for (GradeItem gradeItem : this) {
                System.out.println("Lab Grade: " + gradeItem.getLabGrade());
                System.out.println("Progress Test Grade: " + gradeItem.getProgressTestGrade());
                System.out.println("Final Test Grade: " + gradeItem.getFinalTestGrade());
                System.out.println("Average Grade: " + gradeItem.getAverageGrade());
            }
        }
    }

}
