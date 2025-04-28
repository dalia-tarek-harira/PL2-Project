/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

/**
 *
 * @author roaam
 */
public class Validation {

    //VALIDATION
    public static boolean isValidString(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetterOrDigit(s.charAt(i)) && s.charAt(i) != '@'
                    && s.charAt(i) != ' ' && s.charAt(i) != '_' && s.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }

    public static boolean isOnlyOneCharacter(String s) {
        if (s.length() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOnlyNumbers(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(String s) {
        return s.trim().equals("");
    }

    public static boolean isValidScreenshot(String screenshot) {
        for (int i = 0; i < screenshot.length(); i++) {
            if (!Character.isLetterOrDigit(screenshot.charAt(i)) && screenshot.charAt(i) != '\\'
                    && screenshot.charAt(i) != '_' && screenshot.charAt(i) != ':' && screenshot.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }
}
