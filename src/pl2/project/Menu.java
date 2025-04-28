/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

import static java.lang.System.out;
import java.util.Scanner;

/**
 *
 * @author roaam
 */
public class Menu {

//    One function works for all the users but makes different actions depending on the instances of the classes
//   Login fn takes a small subtype `Admin` and sends it to a big supertype parameter type `User` POLYMORPHISM
    public static void printMenu(User user) {
        Scanner input = new Scanner(System.in);
//        If the Login function sent an object of type Admin - if user contains Admin object
        if (user instanceof Admin) {
            System.out.println("***Hello Admin " + user.getName() + "***");
            String tempString;
            char choice;
            boolean condition = true;
            while (condition) {
                System.out.println("a. Add a User \nb. Update a user's info \n"
                        + "c. Remove a user \nd. View Bugs \ne. Exit");
                System.out.print("Enter your choice (a/b/c/d/e): ");
                tempString = input.nextLine();
//                Making sure that the user didn't leave the field empty
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You Cannot leave this field Empty!");
                    continue;
                }
//                Making sure that the user entered only one letter
                if (Validation.isOnlyOneCharacter(tempString)) {
                    choice = tempString.charAt(0);
                } else {
                    System.out.println("You must enter ONLY ONE character");
                    continue;
                }

                switch (choice) {
//                    ADDING A USER
                    case 'a':
//                        Casting it to use the subclass features
                        ((Admin) user).addUser();
                        break;
//                     UPDATING USER'S INFO   
                    case 'b':
//                        Casting it to use the subclass functions
                        ((Admin) user).ShowUsers();
                        do {
                            do {
                                System.out.print("a.Update Username\nb.Update Password\n"
                                        + "c.Update Number\nd.Update Email\ne.Update Role\n");
                                System.out.print("Enter your choice: ");
                                tempString = input.nextLine();
                                if (Validation.isEmpty(tempString)) {
                                    System.out.println("You cannot leave this field Empty!");
                                } else if (!Validation.isOnlyOneCharacter(tempString)) {
                                    System.out.println("You must enter ONLY ONE character");
                                }
                            } while (!Validation.isOnlyOneCharacter(tempString) || Validation.isEmpty(tempString));
                            choice = tempString.charAt(0);
                            switch (choice) {
//                                Updating username
                                case 'a':
//                                    Casting it to use the subclass functions
                                    ((Admin) user).updateUsername();
                                    break;
//                                    Updating password
                                case 'b':
//                                    Casting it to use the subclass functions
                                    ((Admin) user).updateUserPassword();
                                    break;
//                                    Updating the number
                                case 'c':
//                                    Casting it to use the subclass functions
                                    ((Admin) user).updateUserNumber();
                                    break;
//                                    Updating the email
                                case 'd':
//                                    Casting it to use the subclass functions
                                    ((Admin) user).updateUserEmail();
                                    break;
//                                    Updating the role
                                case 'e':
                                    ((Admin) user).updateUserRole();
                                    break;
                                default:
                                    System.out.println("Invalid input");
                            }
                        } while (choice != 'a' && choice != 'b' && choice != 'c' && choice != 'd' && choice != 'e');
                        break;
//                        DELETING A USER
                    case 'c':
                        ((Admin) user).ShowUsers();
                        ((Admin) user).deleteUser();
                        break;
//                        SHOWING BUGS TABLE
                    case 'd':
                        ((Admin) user).showBugs();
                        break;
//                        EXITS THE PROGRAMM
                    case 'e':
                        condition = false;
                        break;
//                        IF THE USER ENTERED A WRONG VALUE
                    default:
                        System.out.println("Invalid Input");
                }
            }
        } //        Waiting for the code of the other instances
        else if (user instanceof Tester) {
            System.out.println("***Hello Tester " + user.getName() + "***");
            boolean condition = true;
            String tempString;
            char choice;
            while (condition) {
                System.out.println("a. Define a Bug & Assign it to a Developer \n"
                        + "b. View Bugs  \nc. Exit");
                System.out.print("Enter your choice (a/b/c): ");
                tempString = input.nextLine();
//                Making sure that the user didn't leave the field empty
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You Cannot leave this field Empty!");
                    continue;
                }
//                Making sure that the user entered only one letter
                if (Validation.isOnlyOneCharacter(tempString)) {
                    choice = tempString.charAt(0);
                } else {
                    System.out.println("You must enter ONLY ONE character");
                    continue;
                }
                switch (choice) {
                    case 'a':
                        ((Tester) user).defineBug();
                        do {
                            System.out.print("Do you have a screenshot of this bug (y/n)? ");
                            String answer = input.nextLine();
                            if (answer.equals("y")) {
                                ((Tester) user).addScreenshot();
                                break;
                            } else if (answer.equals("n")) {
                                break;
                            } else if (answer.trim().equals("")) {
                                System.out.println("You Cannot leave this field Empty!");
                            } else {
                                System.out.println("Invalid Input");
                            }
                        } while (true);
                        ((Tester) user).assignBug();
                        break;
                    case 'b':
                        ((Tester) user).showBugs();
                        break;
                    case 'c':
                        condition = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
        } else if (user instanceof Developer) {
            System.out.println("***Hello Developer " + user.getName() + "***");
            boolean condition = true;
            String tempString;
            char choice;
            while (condition) {
                System.out.println("a. View Assigned Bugs \n"
                        + "b. Finished a Bug  \nc. Exit");
                System.out.print("Enter your choice (a/b/c): ");
                tempString = input.nextLine();
//                Making sure that the user didn't leave the field empty
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You Cannot leave this field Empty!");
                    continue;
                }
//                Making sure that the user entered only one letter
                if (Validation.isOnlyOneCharacter(tempString)) {
                    choice = tempString.charAt(0);
                } else {
                    System.out.println("You must enter ONLY ONE character");
                    continue;
                }
                switch (choice) {
                    case 'a':
                        ((Developer) user).showBugs();
                        break;
                    case 'b':
                        ((Developer) user).finishedBug();
                        break;
                    case 'c':
                        condition = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
        } else if (user instanceof PM) {
            System.out.println("***Hello PM " + user.getName() + "***");
            boolean condition = true;
            String tempString;
            char choice;
            while (condition) {
                System.out.println("a. Check Performance of Developers & Testers \n"
                        + "b. View Bugs  \nc. Exit");
                System.out.print("Enter your choice (a/b/c): ");
                tempString = input.nextLine();
//                Making sure that the user didn't leave the field empty
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You Cannot leave this field Empty!");
                    continue;
                }
//                Making sure that the user entered only one letter
                if (Validation.isOnlyOneCharacter(tempString)) {
                    choice = tempString.charAt(0);
                } else {
                    System.out.println("You must enter ONLY ONE character");
                    continue;
                }
                switch (choice) {
                    case 'a':
                        System.out.println("");
                        ((PM) user).checkPerformance();
                        break;
                    case 'b':
                        ((PM) user).showBugs();
                        break;
                    case 'c':
                        condition = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
        }
    }
}
