/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
// class MyUitl nay de validate viec nhap vao 
public class MyUtil {

        // integer can try-catch de tranh sai format
        public static Integer inputInteger(String message, int min, int max) {
                Scanner sc = new Scanner(System.in);
                do {
                        try {
                                System.out.println(message);
                                Integer number = Integer.parseInt(sc.nextLine());
                                if (number >= min && number <= max) {
                                        return number;
                                }
                                System.out.println("Value is only in [" + min + ", " + max + "]");
                        } catch (Exception e) {
                                System.out.println("Required number");
                        }
                } while (true);
        }

        // string ko can try-catch
        public static String inputString(String message) {
                Scanner sc = new Scanner(System.in);

                do {
                        System.out.println(message);
                        String value = sc.nextLine();
                        if (!value.isEmpty()) {
                                return value;
                        }
                        System.out.println("Value can not be empty!");
                } while (true);
        }

        // regex for email
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        public static boolean validate(String emailStr) {
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
                return matcher.matches();
        }

        // regex for password
        public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");

        public static boolean validatePass(String password) {
                Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
                return matcher.matches();
        }

}
