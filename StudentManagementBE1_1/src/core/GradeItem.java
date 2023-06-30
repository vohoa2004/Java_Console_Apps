package core;

public class GradeItem {

        private String studentID;
        private String subjectID;
        private double labGrade;
        private double progressTestGrade;
        private double finalTestGrade;
        private double averageGrade;

        public GradeItem(String studentID, String subjectID, double labGrade, double progressTestGrade, double finalTestGrade, double averageGrade) {
                this.studentID = studentID;
                this.subjectID = subjectID;
                this.labGrade = labGrade;
                this.progressTestGrade = progressTestGrade;
                this.finalTestGrade = finalTestGrade;
                this.averageGrade = averageGrade;
        }

        public String getStudentID() {
                return studentID;
        }

        public void setStudentID(String studentID) {
                this.studentID = studentID;
        }

        public String getSubjectID() {
                return subjectID;
        }

        public void setSubjectID(String subjectID) {
                this.subjectID = subjectID;
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
                return averageGrade;
        }

        public void setAverageGrade(double averageGrade) {
                this.averageGrade = averageGrade;
        }

        @Override
        public String toString() {
                return "Grade:" + "studentID=" + studentID + ", subjectID=" + subjectID + ", labGrade=" + labGrade + ", progressTestGrade=" + progressTestGrade + ", finalTestGrade=" + finalTestGrade + ", averageGrade=" + averageGrade ;
        }

}
