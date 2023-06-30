/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyUtil;

/**
 *
 * @author admin
 */
public class UserList extends ArrayList<User> {

        private static final String FILENAME = "src\\data\\user.txt";

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
                                String username = row[0];
                                String password = row[1];
                                String fullName = row[2];
                                String email = row[3];
                                User user = new User(username, password, fullName, email);
                                this.add(user);
                        }
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

        public void writeToFile() {
                try {
                        PrintWriter out = new PrintWriter(FILENAME);
                        out.println("Username, Password, Full Name, Email");
                        for (User user : this) {
                                out.println(user.getUsername() + ", " + user.getPassword() + ", " + user.getFullName() + ", " + user.getEmail());
                        }
                        // remember to close the file
                        out.close();
                } catch (FileNotFoundException ex) {
                        Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
                }

        }

        public User login() {
                String username = MyUtil.inputString("Enter your username: ");
                String password = MyUtil.inputString("Enter your password: ");
                for (User user : this) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                                return user;
                        }
                }
                return null;
        }

        public void register() {
                String fullName = MyUtil.inputString("Enter your full name: ");

                // use regex for email    
                String email;
                do {
                        email = MyUtil.inputString("Enter your email: ");
                } while (MyUtil.validateEmail(email) == false);

                String username;
                // check username is unique
                boolean isUnique;
                do {
                        isUnique = true;
                        username = MyUtil.inputString("Enter your username: ");
                        for (User user : this) {
                                if (user.getUsername().equals(username)) {
                                        isUnique = false;
                                }
                        }
                        if (isUnique == false) {
                                System.out.println("This username is already existed!");
                        }
                } while (isUnique == false);

                // regex validate password 
                String password;
                 do {
                        password = MyUtil.inputString("Enter your password: ");
                } while (MyUtil.validatePass(password) == false);

                User user = new User(username, password, fullName, email);

                this.add(user);
                System.out.println("Register successfully!");
        }

}
