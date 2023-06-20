/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author user
 */
public class SubjectList extends ArrayList<Subject> {

        private static final String FILENAME = "src\\data\\subject.txt";

        public void createSubject() {
                String subjectID;
                boolean isUnique;
                do {
                        isUnique = true;
                        subjectID = MyUtil.inputString("Enter subject ID: ");
                        for (Subject x : this) {

                                if (x.getSubjectID().equals(subjectID)) {
                                        //System.out.println(x.toString());
                                        isUnique = false;

                                }
                        }
                        if (isUnique == false) {
                                System.out.println("This ID is existed");
                        }
                } while (isUnique == false);
                String subjectName = MyUtil.inputString("Input subject name: ");
                // check unique nua
                int credit = MyUtil.inputInteger("Input credit: ", 0, 100);
                Subject subject = new Subject(subjectID, subjectName, credit);
                this.add(subject);
                System.out.println("Added this subject!");
        }

        public void searchSubjectByID() {
                String id = MyUtil.inputString("Enter ID of the subject you want to search: ");
                boolean isFound = false;
                for (Subject x : this) {
                        if (x.getSubjectID().equals(id)) {
                                System.out.println(x.toString());
                                isFound = true;
                                break;
                        }
                }
                if (isFound == false) {
                        System.out.println("Not found!");
                }
        }

        public boolean updateSubject() {
                String id = MyUtil.inputString("Enter ID of the subject you want to update: ");

                for (Subject x : this) {
                        boolean isUnique;
                        if (x.getSubjectID().equals(id)) {
                                String newID;
                                do {
                                        isUnique = true;
                                        newID = MyUtil.inputString("Input new ID: ");
                                        for (Subject i : this) {

                                                if (i.getSubjectID().equals(newID)) {
                                                        //System.out.println(x.toString());
                                                        isUnique = false;

                                                }
                                        }
                                        if (isUnique == false) {
                                                System.out.println("This ID is existed");
                                        }
                                } while (isUnique == false);
                                String newName = MyUtil.inputString("Input new name: ");
                                int newCredit = MyUtil.inputInteger("Input new credit: ", 0, 100);
                                x.setCredit(newCredit);
                                x.setSubjectID(newID);
                                x.setSubjectName(newName);
                                return true;

                        }
                }
                return false;
        }

        public boolean deleteSubject() {
                String id = MyUtil.inputString("Enter ID of the subject you want to delete: ");
                for (Subject x : this) {
                        if (x.getSubjectID().equals(id)) {
                                this.remove(x);
                                System.out.println("Subject deleted");
                                return true;
                                
                        }
                }
                return false;
        }

        public void displaySubjectList() {
                for (Subject x : this) {
                        System.out.println(x.toString());
                }
        }

        public void readFromFile() {

                BufferedReader reader;
                String line;
                File file = new File(FILENAME);

                if (!file.exists()) {
                        System.out.println("File not exist");
                        System.exit(0);
                }

                try {
                        reader = new BufferedReader(new FileReader(file));
                        line = reader.readLine();
                        while ((line = reader.readLine()) != null) {
                                String[] row = line.split(", ");
                                String subjectID = row[0];
                                String subjectName = row[1];
                                int credit = Integer.parseInt(row[2]);

                                Subject subject = new Subject(subjectID, subjectName, credit);
                                this.add(subject);
                        }
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

        public void writeToFile() {
                try {
                        PrintWriter out = new PrintWriter(FILENAME);
                        out.println("subject ID, Subject Name, Credit");
                        for (Subject x : this) {
                                out.println(x.getSubjectID() + ", " + x.getSubjectName() + ", " + x.getCredit());
                        }
                        // remember to close the file
                        out.close();
                } catch (FileNotFoundException ex) {
                        Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
}
