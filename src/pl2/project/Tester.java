/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roaam
 */
public class Tester extends User {

    //public static void main(Sting[] args){
    private final Bugs bug;
    private static FileOperations bugsFile;
    private static FileOperations usersFile;

    public Tester() {
        bug = new Bugs();
        bugsFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\bugs.txt");
        usersFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\users.txt");
    }

    public void defineBug() {
        String tempString;
        char choice;
        Scanner input = new Scanner(System.in);

//         Name Must be a ValidString and  Not Empty 
        do {
            System.out.print("Enter bug name : ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You Cannot Leave this field Empty!");
            } else if (!Validation.isValidString(tempString)) {
                System.out.println("only those special characters are valid (@)( )(.)(_) ");
            }
        } while (Validation.isEmpty(tempString) || !Validation.isValidString(tempString));
        bug.setBugName(tempString.trim());

//       Bug Type Must be a ValidString and not empty
        do {
            System.out.print("Enter bug Type : ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You Cannot Leave this field Empty!");
            } else if (!Validation.isValidString(tempString)) {
                System.out.println("only those special characters are valid (@)( )(.)(_) ");
            }
        } while (Validation.isEmpty(tempString) || !Validation.isValidString(tempString));
        bug.setBugType(tempString.trim());

//        User Must enter the choice right => Single character and not empty
        do {
            do {
                System.out.print("Priority: a.Low  b.Medium  c.High\n");
                System.out.print("Enter Your Choice : ");
                tempString = input.nextLine();
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You Cannot Leave this Field Empty!");
                } else if (!Validation.isOnlyOneCharacter(tempString)) {
                    System.out.println("You must enter ONLY ONE character");
                }
            } while (Validation.isEmpty(tempString) || !Validation.isOnlyOneCharacter(tempString));
            choice = tempString.charAt(0);
            switch (choice) {
                case 'a':
                    bug.setPriority("Low");
                    break;
                case 'b':
                    bug.setPriority("Medium");
                    break;
                case 'c':
                    bug.setPriority("High");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (choice != 'a' && choice != 'b' && choice != 'c');

//        User Must enter the choice right => Single character and not empty
        do {
            do {
                System.out.print("Level: a.Low  b.Minor  c.Major  d.Critical\n");
                System.out.print("Enter Your Choice : ");
                tempString = input.nextLine();
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You Cannot Leave this Field Empty!");
                } else if (!Validation.isOnlyOneCharacter(tempString)) {
                    System.out.println("You must enter ONLY ONE character");
                }
            } while (Validation.isEmpty(tempString) || !Validation.isOnlyOneCharacter(tempString));
            choice = tempString.charAt(0);
            switch (choice) {
                case 'a':
                    bug.setBugLevel("Low");
                    break;
                case 'b':
                    bug.setBugLevel("Minor");
                    break;
                case 'c':
                    bug.setBugLevel("Major");
                    break;
                case 'd':
                    bug.setBugLevel("Critical");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (choice != 'a' && choice != 'b' && choice != 'c' && choice != 'd');

//        Date Will Be assigned automatically
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        bug.setBugDate(dtf.format(now));

//        Project Name Must be Not Empty and ValidString 
        do {
            System.out.print("Enter the Project Name : ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You Cannot Leave this field Empty!");
            } else if (!Validation.isValidString(tempString)) {
                System.out.println("only those special characters are valid (@)( )(.)(_) ");
            }
        } while (Validation.isEmpty(tempString) || !Validation.isValidString(tempString));
        bug.setProjectName(tempString.trim());

        bug.setBugId(bugsFile.getNumRows());

        String details = bug.getBugId() + "-" + bug.getBugName() + "-" + bug.getBugType() + "-" + bug.getPriority() + "-"
                + bug.getBugLevel() + "-" + bug.getBugStatus() + "-" + bug.getProjectName() + "-" + bug.getBugDate();

        bugsFile.appendInFile(details);
        System.out.println("Bug Defined Successfully");
    }

    public void assignBug() {
        FileOperations assignedBugsFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\assignedBugs.txt");
        FileOperations performanceFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\performance.txt");
        showDevelopers();
        Scanner input = new Scanner(System.in);
        int developerId;
        String tempString;
        do {
            do {
                System.out.print("Enter the Developer's id that you want to assign this bug to: ");
                tempString = input.nextLine();
                if (Validation.isEmpty(tempString)) {
                    System.out.println("You cannot Leave this field Empty");
                } else if (!Validation.isOnlyNumbers(tempString)) {
                    System.out.println("Only Numbers are Valid");
                }
            } while (Validation.isEmpty(tempString) || !Validation.isOnlyNumbers(tempString));
            developerId = Integer.parseInt(tempString);
            if (isDeveloperFound(developerId)) {
//              assignerBugs.txt file => BugId-TesterId-DeveloperId
                String info = bug.getBugId() + "-" + this.getId() + "-" + developerId;
                assignedBugsFile.appendInFile(info);
                System.out.println("Bug Assigned Successfully");
                performanceFile.appendInFile("Tester" + "-" + this.getId() + "-Assigned a Bug");
                SendEmail(developerId);
            } else {
                System.out.println("Developer Doesn't Exist");
            }
        } while (!isDeveloperFound(developerId));
    }

    public void addScreenshot() {
        FileOperations screenshotsFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\screenshots.txt");
        String tempString;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Enter the path of the Screenshot : ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You Cannot Leave This Field Empty!");
            } else if (!Validation.isValidScreenshot(tempString)) {
                System.out.println("only those special characters are valid (.)(_)(:)(\\) ");
            }
        } while (Validation.isEmpty(tempString) || !Validation.isValidScreenshot(tempString));
        bug.setBugScreenshot(tempString.trim());
        screenshotsFile.appendInFile(this.getId() + "-" + bug.getBugId() + "-" + bug.getBugScreenshot());
    }

    @Override
    public void showBugs() {
        System.out.println("                                            ---Assigned Bugs Table---");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", "ID",
                 "Name", "Type", "Priority", "Level", "Status", "Project", "Date");
        bugsFile.readFromFile();
        System.out.println("");
    }

    private void showDevelopers() {
        String allRows = usersFile.returnRows(5, "Developer");
        if (allRows == null) {
            System.out.println("No Developers are available");
            return;
        }
        System.out.println("    --Developers--");
        System.out.printf("%-15s %-15s\n", "ID",
                "Username");
        String[] row = allRows.split("\n");
        for (int i = 0; i < row.length; i++) {
            String[] line = row[i].split("-");
            System.out.printf("%-15s %-15s\n", line[0], line[1]);
        }
    }

    private boolean isDeveloperFound(int developerId) {
        String allRows = usersFile.returnRows(5, "Developer");
        String[] row = allRows.split("\n");
        for (int i = 0; i < row.length; i++) {
            String[] line = row[i].split("-");
            if (line[0].equals(developerId + "")) {
                return true;
            }
        }
        return false;
    }

//     Sending mail To Developer
    private void SendEmail(int developerId) {
        String emailSubject = "\n\nSubject: Bug Report: " + bug.getBugName();
        String row = usersFile.returnRows(0, developerId + "");
        String developerName = row.split("-")[1];
        String emailBody = "\nDear, " + developerName + "\n\n"
                + "A new bug has been assigned to you.\n\n"
                + "Bug Name: " + bug.getBugName() + "\n\n"
                + "Bug Type: " + bug.getBugType() + "\n\n"
                + "Bug Level: " + bug.getBugLevel() + "\n\n"
                + "Bug Priority: " + bug.getPriority() + "\n\n"
                + "Project Name: " + bug.getProjectName() + "\n\n"
                + "Bug Date: " + bug.getBugDate() + "\n\n"
                + "Please address this issue.\n\n"
                + "Best Regards,\n" + this.getName()
                + "\n-------------------------\n";
        String fileName = "C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\inbox\\" + developerId + "_inbox.txt";
        String email = emailSubject + emailBody;
        FileOperations file = new FileOperations(fileName);
        file.appendInFile(email);
    }

}
