/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author MSI PC
 */
public class GradeItem {
     private double labGrade;
    private double progressTestGrade;
    private double finalTestGrade;

    public GradeItem() {
        this.labGrade = 0.0;
        this.progressTestGrade = 0.0;
        this.finalTestGrade = 0.0;
    }

    public GradeItem(double labGrade, double progressTestGrade, double finalTestGrade) {
        this.labGrade = labGrade;
        this.progressTestGrade = progressTestGrade;
        this.finalTestGrade = finalTestGrade;
    }

    public double getLabGrade() {
        return labGrade;
    }

    public void setLabGrade(double labGrade) {
        this.labGrade = labGrade;
    }

    public double getProgressTestGrade() {
        return progressTestGrade;
    }

    public void setProgressTestGrade(double progressTestGrade) {
        this.progressTestGrade = progressTestGrade;
    }

    public double getFinalTestGrade() {
        return finalTestGrade;
    }

    public void setFinalTestGrade(double finalTestGrade) {
        this.finalTestGrade = finalTestGrade;
    }

    public double getAverageGrade() {
        return (labGrade + progressTestGrade + finalTestGrade) / 3.0;
    }
}
