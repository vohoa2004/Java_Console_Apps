/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author user
 */
public class Subject {

        private String subjectID;
        private String subjectName;
        private int credit;

        public Subject(String subjectID, String subjectName, int credit) {
                this.subjectID = subjectID;
                this.subjectName = subjectName;
                this.credit = credit;
        }

        public String getSubjectID() {
                return subjectID;
        }

        public void setSubjectID(String subjectID) {
                this.subjectID = subjectID;
        }

        public String getSubjectName() {
                return subjectName;
        }

        public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
        }

        public int getCredit() {
                return credit;
        }

        public void setCredit(int credit) {
                this.credit = credit;
        }

        @Override
        public String toString() {
                return  "Subject ID: " + subjectID + ", Subject Name: " + subjectName + ", Credit: " + credit;
        }

}
