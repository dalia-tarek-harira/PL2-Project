/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

import java.util.Scanner;

/**
 *
 * @author roaam
 */
//Checks if the user exists or not and displays the right menu for him 
public class LoginPage {

    public static void login() {
        FileOperations usersFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\users.txt");
        Scanner input = new Scanner(System.in);
        while (true) {
            String tempString;
            do {
                System.out.print("Enter your Username: ");
                tempString = input.nextLine().trim();
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You can't leave this field empty!");
                }
            } while (Validation.isEmpty(tempString));
            String username = tempString;
            do {
                System.out.print("Enter your Password: ");
                tempString = input.nextLine().trim();
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You can't leave this field empty!");
                }
            } while (Validation.isEmpty(tempString));
            String password = tempString;
//            if the user doesn't exist / function returned null
            if (usersFile.userRole(username, password) == null) {
                System.out.println("Incorrect Username or Password");
            } //            if the function returned "Admin" as a role => we will open the admin menu
            else if (usersFile.userRole(username, password).equals("Admin")) {
//                 makes an object of type `Admin` and sends it to the fn `printMenu` that takes a `User` as a parameter POLYMORPHISM
                Admin admin = new Admin();
//                to keep the name of the person that logged in throughout the whole programm
                admin.setName(username);
//                to keep the id of the person that logged in throughout the whole programm
                int id = usersFile.getIdByName(username, password);
                admin.setId(id);
                Menu.printMenu(admin);
                break;
            } else if (usersFile.userRole(username, password).equals("Tester")) {
                Tester tester = new Tester();
                tester.setName(username);
                int id = usersFile.getIdByName(username, password);
                tester.setId(id);
                Menu.printMenu(tester);
                break;
            } else if (usersFile.userRole(username, password).equals("Developer")) {
                Developer developer = new Developer();
                developer.setName(username);
                int id = usersFile.getIdByName(username, password);
                developer.setId(id);
                Menu.printMenu(developer);
                break;
            } else if (usersFile.userRole(username, password).equals("PM")) {
                PM pm = new PM();
                pm.setName(username);
                int id = usersFile.getIdByName(username, password);
                pm.setId(id);
                Menu.printMenu(pm);
                break;
            }
        }
    }
}
