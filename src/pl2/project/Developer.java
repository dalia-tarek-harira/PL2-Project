/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roaam
 */
public class Developer extends User {

    private static FileOperations bugsFile;
    private static FileOperations usersFile;
    private static FileOperations assignedBugsFile;
    private static FileOperations performanceFile;

    public Developer() {
        bugsFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\bugs.txt");
        usersFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\users.txt");
        assignedBugsFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\assignedBugs.txt");
        performanceFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\performance.txt");
    }

    private void displayBug(int bugId) {
        String row = bugsFile.returnRows(0, bugId + "");
        String[] rowArr = row.split("-");
        for (int i = 0; i < rowArr.length; i++) {
            System.out.printf("%-20s", rowArr[i]);
        }
    }

    @Override
    public void showBugs() {
        String rows = assignedBugsFile.returnRows(2, this.getId() + "");
        if (rows == null) {
            System.out.println("No Assigned Bugs Yet for You");
            return;
        }
        System.out.println("                                            ---Assigned Bugs Table---");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID",
                "Name", "Type", "Priority", "Level", "Status", "Project", "Date");
        int developerId = this.getId();

        String[] rowsArr = rows.split("\n");
        for (int i = 0; i < rowsArr.length; i++) {
            int bugId = Integer.parseInt(rowsArr[i].split("-")[0]);
            displayBug(bugId);
        }
    }

    public void finishedBug() {
        String rows = assignedBugsFile.returnRows(2, this.getId() + "");
        if (rows == null) {
            System.out.println("No Assigned Bugs Yet for You, then you didn't finish any bug !");
            return;
        }
        showBugs();
        String tempString;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Enter the ID of the bug that you have Finished : ");
            tempString = input.nextLine();
            if (Validation.isEmpty(tempString)) {
                System.out.println("You cannot leave this field Empty!");
            } else if (!Validation.isOnlyNumbers(tempString)) {
                System.out.println("Only Numbers are available");
            }
        } while (!Validation.isOnlyNumbers(tempString) || Validation.isEmpty(tempString));
        int enteredBugID = Integer.parseInt(tempString);
        String[] rowsArr = rows.split("\n");
        for (int i = 0; i < rowsArr.length; i++) {
            int bugId = Integer.parseInt(rowsArr[i].split("-")[0]);
            if (bugId == enteredBugID) {
                bugsFile.updateInFile(bugId, "Closed", 5);
                sendMail(bugId);
                assignedBugsFile.deleteFromFile(bugId);
                performanceFile.appendInFile("Developer" + "-" + this.getId() + "-Solved a Bug");
                System.out.println("Done");
                return;
            }
        }
        System.out.println("Bug Doesn't Exist");
    }
//the user will write the bug's id that he finished and we will send mail automatically to the tester that assigned this bug

    private void sendMail(int bugId) {
        String rowTester = assignedBugsFile.returnRows(0, bugId + "");
        int testerId = Integer.parseInt(rowTester.split("-")[1]);
        String rowTester2 = usersFile.returnRows(0, testerId + "");
        String testerName = rowTester2.split("-")[1];
        String rowBug = bugsFile.returnRows(0, bugId + "");
        String[] rowBugArr = rowBug.split("-");
        /*           Send a mail to a tester containing => testerName bugId developerId developerName
             , make the file's name with the id of tester*/
        String emailSubject = "\n\nSubject: Notification About " + rowBugArr[1];
        String emailBody = "\n\nDear Tester, " + testerName + "\n\n"
                + "I hope this email finds you well. This email is to inform you about the status of a bug that you have assigned. \n\n"
                + "Bug ID: " + bugId + "\n\n"
                + "Bug name: " + rowBugArr[1] + "\n\n"
                + "Bug Type: " + rowBugArr[2] + "\n\n"
                + "Bug Level: " + rowBugArr[3] + "\n\n"
                + "Bug Priority: " + rowBugArr[4] + "\n\n"
                + "Project Name: " + rowBugArr[6] + "\n\n"
                + "Bug Date: " + rowBugArr[7] + "\n"
                + "Bug status: Resolved\n\n"
                + "Best Regards,\n"
                + this.getName()
                + "\n-------------------------";
        String email = emailSubject + emailBody;
        String fileName = "C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\inbox\\" + testerId + "_inbox.txt";
        FileOperations mail = new FileOperations(fileName);
        mail.appendInFile(email);
    }
}
