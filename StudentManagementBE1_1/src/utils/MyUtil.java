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
                                System.out.print(message);
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
                        System.out.print(message);
                        String value = sc.nextLine();
                        if (!value.isEmpty()) {
                                return value;
                        }
                        System.out.println("Value can not be empty!");
                } while (true);
        }

        public static Double inputDouble(String message, int min, int max) {
                Scanner sc = new Scanner(System.in);
                do {
                        try {
                                System.out.print(message);
                                Double number = Double.parseDouble(sc.nextLine());
                                if (number >= min && number <= max) {
                                        return number;
                                }
                                System.out.println("Value is only in [" + min + ", " + max + "]");
                        } catch (Exception e) {
                                System.out.println("Required number");
                        }
                } while (true);
        }

        //regex for username
        public static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[\\S]{3,20}$");

        public static boolean validateUsername(String username) {
                Matcher matcher = VALID_USERNAME_REGEX.matcher(username);
                return matcher.matches();
        }

        // regex for email
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        public static boolean validateEmail(String emailStr) {
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
                return matcher.matches();
        }

        // regex for password
        public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");

        public static boolean validatePass(String password) {
                Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
                return matcher.matches();
        }

        public static final Pattern VALID_STUDENTID_REGEX = Pattern.compile("SE\\d{6}");

        public static boolean validateID(String ID) {
                Matcher id = VALID_STUDENTID_REGEX.matcher(ID);
                return id.matches();
        }

        public static final Pattern VALID_DOB_REGEX = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

        public static boolean validateDob(String dob) {
                Matcher matcher = VALID_DOB_REGEX.matcher(dob);
                if (!matcher.matches()) {
                        return false;
                }

                String[] parts = dob.split("/");
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                // Validate month
                if (month < 1 || month > 12) {
                        return false;
                }

                // Validate day based on month and year
                int maxDays = getMaxDays(month, year);
                if (day < 1 || day > maxDays) {
                        return false;
                }
                return true;
        }

        private static boolean isLeapYear(int year) {
                return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
        }

        private static int getMaxDays(int month, int year) {
                if (month == 2) {
                        return isLeapYear(year) ? 29 : 28;
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                        return 30;
                } else {
                        return 31;
                }
        }

        public static final Pattern VALID_PHONE_REGEX = Pattern.compile("\\d{10}");

        public static boolean validatePhone(String phone) {
                Matcher p = VALID_PHONE_REGEX.matcher(phone);
                return p.matches();
        }

}
