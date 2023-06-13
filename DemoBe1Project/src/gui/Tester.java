/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import core.User;
import core.UserList;
import java.util.ArrayList;
import utils.MyUtil;

/**
 *
 * @author admin
 */
public class Tester {
    public static void main(String[] args) {
        UserList userList = new UserList();
        userList.readFromFile();
        ArrayList<String> Menu = new ArrayList<>();
        Menu.add("Login");
        Menu.add("Register");
        for(int i = 0; i < Menu.size(); i++) {
            System.out.println(i+1 + ". " + Menu.get(i));
        }
        Integer choice = MyUtil.inputInteger("Please enter your choice:", 1, 2);
        if(choice == 1) {
            User loginUser = userList.login();
            if(loginUser == null) {
                System.out.println("Wrong username or password");
            } else {
                System.out.println("Login success");
                System.out.println("Your info: ");
                System.out.println(loginUser.getUsername() + ", " + loginUser.getPassword() + ", " + loginUser.getFullName() + ", " + loginUser.getEmail());
            }
        }
        if(choice == 2) {
            userList.register();
            userList.writeToFile();
        }
    }
}
