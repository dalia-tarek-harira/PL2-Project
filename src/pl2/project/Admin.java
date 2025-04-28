/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roaam
 */
public class Admin extends User {

    private static FileOperations usersFile;
    private static FileOperations bugsFile;

    public Admin() {
        usersFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\users.txt");
        bugsFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\bugs.txt");
    }

//    Old Code before making User class (container)
//    public void addUser(String username,String pass,String email,String number,String role){
//        username = username.trim();
//        pass = pass.trim();
//        email = email.trim();
//        number = number.trim();
//        role = role.trim();
//        usersFile.appendInFile(username + "-" +pass + "-" +email + "-" +number + "-" +role);
//        System.out.println("User Added Successfully");
//    }
    public void addUser() {
        Scanner input = new Scanner(System.in);
        String tempString;
        char choice;
        /*Making an object of type User => making it as a container easier
        than sending a lot of distributed parameters*/
        PM user = new PM();
//       Making sure that the input that the user has typed is valid or not
        do {
            System.out.print("Enter the user's Username: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (Validation.isValidString(tempString)) {
                user.setName(tempString.trim());
            } else {
                System.out.println("only those special characters are valid (@)( )(.)(_) ");
            }
        } while (!Validation.isValidString(tempString) || Validation.isEmpty(tempString));
        do {
            System.out.print("Enter the user's Password: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (Validation.isValidString(tempString)) {
                user.setPassword(tempString.trim());
            } else {
                System.out.println("only those special characters are valid (@)( )(.)(_) ");
            }
        } while (!Validation.isValidString(tempString) || Validation.isEmpty(tempString));
        do {
            System.out.print("Enter the user's Number: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (Validation.isOnlyNumbers(tempString)) {
                user.setNumber(tempString.trim());
            } else {
                System.out.println("Only Numbers are available");
            }
        } while (!Validation.isOnlyNumbers(tempString) || Validation.isEmpty(tempString));
        do {
            System.out.print("Enter the user's Email: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (Validation.isValidString(tempString)) {
                user.setEmail(tempString.trim());
            } else {
                System.out.println("only those special characters are valid (@)( )(.)(_) ");
            }
        } while (!Validation.isValidString(tempString) || Validation.isEmpty(tempString));
//       Making the user choose a role instead of making him write it for reducing errors
        do {
//            making sure that the input that the user has typed is only one character 
            do {
                System.out.print("Choose a Role: \na.Admin, b.Tester, c.PM, d.Developer: ");
                tempString = input.nextLine();
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You cannot leave this field Empty!");
                } else if (!Validation.isOnlyOneCharacter(tempString)) {
                    System.out.println("You must enter ONLY ONE character");
                }
            } while (!Validation.isOnlyOneCharacter(tempString) || Validation.isEmpty(tempString));
            choice = tempString.charAt(0);
            switch (choice) {
                case 'a':
                    user.setRole("Admin");
                    break;
                case 'b':
                    user.setRole("Tester");
                    break;
                case 'c':
                    user.setRole("PM");
                    break;
                case 'd':
                    user.setRole("Developer");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (choice != 'a' && choice != 'b' && choice != 'c' && choice != 'd');
//        Accessing the object to get its values after validating them
        user.setId(usersFile.getNumRows());
        usersFile.appendInFile(user.getId() + "-" + user.getName() + "-" + user.getPassword()
                + "-" + user.getEmail() + "-" + user.getNumber() + "-" + user.getRole());
        System.out.println("User Added Successfully");
    }

    public void ShowUsers() {
        System.out.println("                                                 --Users Table--");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", "ID",
                "Username", "Password", "Email", "Number", "Role");
        usersFile.readFromFile();
    }

    public void updateUsername() {
        Scanner input = new Scanner(System.in);
        String newValue;
        String tempString;
        int id;
        do {
            System.out.print("Enter the user's id: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isOnlyNumbers(tempString)) {
                System.out.println("Only Numbers are available");
            }
        } while (!Validation.isOnlyNumbers(tempString) || Validation.isEmpty(tempString));
//      Converting a string into an integer
        id = Integer.parseInt(tempString);
//      Making sure that the input is valid
        do {
            System.out.print("Enter the new Username: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isValidString(tempString)) {
                System.out.println("only those special characters are valid (@)( )(.)(_) ");
            }
        } while (!Validation.isValidString(tempString) || Validation.isEmpty(tempString));
        newValue = tempString;
        String returned = usersFile.updateInFile(id, newValue, 1);
        System.out.println(returned);
    }

    public void updateUserPassword() {
        Scanner input = new Scanner(System.in);
        String newValue;
        String tempString;
        int id;
        do {
            System.out.print("Enter the user's id: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isOnlyNumbers(tempString)) {
                System.out.println("Only Numbers are available");
            }
        } while (!Validation.isOnlyNumbers(tempString) || Validation.isEmpty(tempString));
//      Converting a string into an integer
        id = Integer.parseInt(tempString);
//      Making sure that the input is valid
        do {
            System.out.print("Enter the new Password: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isValidString(tempString)) {
                System.out.println("only those special characters are valid (@)( )(.)(_) ");
            }
        } while (!Validation.isValidString(tempString) || Validation.isEmpty(tempString));
        newValue = tempString;
        String returned = usersFile.updateInFile(id, newValue, 2);
        System.out.println(returned);
    }

    public void updateUserEmail() {
        Scanner input = new Scanner(System.in);
        String newValue;
        String tempString;
        int id;
        do {
            System.out.print("Enter the user's id: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isOnlyNumbers(tempString)) {
                System.out.println("Only Numbers are available");
            }
        } while (!Validation.isOnlyNumbers(tempString) || Validation.isEmpty(tempString));
//      Converting a string into an integer
        id = Integer.parseInt(tempString);
//      Making sure that the input is valid
        do {
            System.out.print("Enter the new Email: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isValidString(tempString)) {
                System.out.println("only those special characters are valid (@)( )(.)(_) ");
            }
        } while (!Validation.isValidString(tempString) || Validation.isEmpty(tempString));
        newValue = tempString;
        String returned = usersFile.updateInFile(id, newValue, 3);
        System.out.println(returned);
    }

    public void updateUserNumber() {
        Scanner input = new Scanner(System.in);
        String tempString;
        String newValue;
        int id;
        do {
            System.out.print("Enter the user's id: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isOnlyNumbers(tempString)) {
                System.out.println("Only Numbers are available");
            }
        } while (!Validation.isOnlyNumbers(tempString) || Validation.isEmpty(tempString));
//      Converting a string into an integer
        id = Integer.parseInt(tempString);
//      Making sure that the input is valid
        do {
            System.out.print("Enter the new Number: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isOnlyNumbers(tempString)) {
                System.out.println("Only Numbers are available");
            }
        } while (!Validation.isOnlyNumbers(tempString) || Validation.isEmpty(tempString));
        newValue = tempString;
        String returned = usersFile.updateInFile(id, newValue, 4);
        System.out.println(returned);
    }

    public void updateUserRole() {
        Scanner input = new Scanner(System.in);
        String tempString;
        char choice;
        int id;
        do {
            System.out.print("Enter the user's id: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isOnlyNumbers(tempString)) {
                System.out.println("Only Numbers are available");
            }
        } while (!Validation.isOnlyNumbers(tempString) || Validation.isEmpty(tempString));
//      Converting a string into an integer
        id = Integer.parseInt(tempString);
        do {
//              Making sure that the input is valid
            do {
                System.out.print("Choose a Role: \na.Admin, b.Tester, c.PM, d.Developer: ");
                tempString = input.nextLine();
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You cannot leave this field Empty!");
                } else if (!Validation.isOnlyOneCharacter(tempString)) {
                    System.out.println("You must enter ONLY ONE character");
                }
            } while (!Validation.isOnlyOneCharacter(tempString) || Validation.isEmpty(tempString));
            choice = tempString.charAt(0);
            switch (choice) {
                case 'a':
                    String returned = usersFile.updateInFile(id, "Admin", 5);
                    System.out.println(returned);
                    break;
                case 'b':
                    String returned2 = usersFile.updateInFile(id, "Tester", 5);
                    System.out.println(returned2);
                    break;
                case 'c':
                    String returned3 = usersFile.updateInFile(id, "PM", 5);
                    System.out.println(returned3);
                    break;
                case 'd':
                    String returned4 = usersFile.updateInFile(id, "Developer", 5);
                    System.out.println(returned4);
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (choice != 'a' && choice != 'b' && choice != 'c' && choice != 'd');
    }

    public void deleteUser() {
        Scanner input = new Scanner(System.in);
        String tempString;
        int id;
        do {
            System.out.print("Enter the user's id: ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isOnlyNumbers(tempString)) {
                System.out.println("Only Numbers are available");
            }
        } while (!Validation.isOnlyNumbers(tempString) || Validation.isEmpty(tempString));
//      Converting a string into an integer
        id = Integer.parseInt(tempString);
        String returned = usersFile.deleteFromFile(id);
        System.out.println(returned);
    }

    @Override
    public void showBugs() {
        System.out.println("                                                 --Bugs Table--");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", "ID",
                "Name", "Type", "Priority", "Level", "Status", "Project", "Bug Date");
        bugsFile.readFromFile();
    }
}
