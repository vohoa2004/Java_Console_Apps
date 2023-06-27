package utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class MyUtil {

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

    public static String inputString(String message) {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println(message);
            String value = sc.nextLine();
            if(!value.isEmpty()) {
                return value;
            }
            System.out.println("Value can not be empty!");
        } while (true);
    }
    
    public static final Pattern studentIDPattern = Pattern.compile("SE\\d{6}");
    
    public static boolean validateID (String ID){
        Matcher id = studentIDPattern.matcher(ID);
        return id.matches();
    }
    
    public static final Pattern dobPattern = Pattern.compile("\\d{2}/\\d{2}/\\d{2}");
    
    public static boolean validateDob(String dob){
        Matcher d = dobPattern.matcher(dob);
        return d.matches();
    }
            
    public static final Pattern email = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", Pattern.CASE_INSENSITIVE );
    
    public static boolean validateEmail(String email){
         Matcher e = MyUtil.email.matcher(email);
         return e.matches();
    }
    public static final Pattern phoneNumber = Pattern.compile("\\d{10}");
     public static boolean validatePhone(String phone){
        Matcher p = phoneNumber.matcher(phone);
        return p.matches();
     }
}
