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
    private double averageGrade;
    
    public GradeItem(double labGrade1, double progressTestGrade1, double finalTestGrade1, double averageGrade) {
        this.labGrade = labGrade1;
        this.progressTestGrade = progressTestGrade1;
        this.finalTestGrade = finalTestGrade1;
        this.averageGrade =(labGrade1+ progressTestGrade1+ finalTestGrade1)/3;
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

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public String toString() {
        return "labGrade=" + labGrade + ", progressTestGrade=" + progressTestGrade + ", finalTestGrade=" + finalTestGrade ;
    }
    
}
