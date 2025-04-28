/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roaam
 */
public class PM extends User {

    private static FileOperations usersFile;
    private static FileOperations performanceFile;
    private static FileOperations bugsFile;

    public PM() {
        usersFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\users.txt");
        performanceFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\performance.txt");
        bugsFile = new FileOperations("C:\\Users\\roaam\\Desktop\\Level-2\\PL2\\Project\\bugs.txt");
    }

    public void checkPerformance() {
        String developerName = "";
        String returnedRowsDevelopers = usersFile.returnRows(5, "Developer");
        if (returnedRowsDevelopers == null) {
            System.out.println("No Developers Available");
        } else {
            String[] allRowsDevelopers = returnedRowsDevelopers.split("\n");
            for (int i = 0; i < allRowsDevelopers.length; i++) {
                int counter = 0;
                String[] line = allRowsDevelopers[i].split("-");
                int developerId = Integer.parseInt(line[0]);
                String devName = line[1];
                String rowsPerformance = performanceFile.returnRows(1, developerId + "");
                if (rowsPerformance != null) {
                    String[] linesPerformance = rowsPerformance.split("\n");
                    counter = linesPerformance.length;
                }
                System.out.println("Developer : " + devName + "\nID : " + developerId /*+ "\nTester Name : " + testerName*/
                        + "\nSolved " + counter + " Bugs");
                System.out.println("-------------------");
            }
        }

        String returnedRowsTesters = usersFile.returnRows(5, "Tester");
        if (returnedRowsTesters == null) {
            System.out.println("No Testers Available");
        } else {
            String[] allRowsTesters = returnedRowsTesters.split("\n");
            for (int i = 0; i < allRowsTesters.length; i++) {
                int counter = 0;
                String[] line = allRowsTesters[i].split("-");
                int testerid = Integer.parseInt(line[0]);
                String testerName = line[1];
                String rowsPerformance = performanceFile.returnRows(1, testerid + "");
                if (rowsPerformance != null) {
                    String[] linesPerformance = rowsPerformance.split("\n");
                    counter = linesPerformance.length;
                }
                System.out.println("Tester : " + testerName + "\nID : " + testerid
                        + "\nAssigned " + counter + " Bugs");
                System.out.println("-------------------");
            }
        }
    }

    @Override
    public void showBugs() {
        System.out.println("                                                 --Bugs Table--");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", "ID",
                "Name", "Type", "Priority", "Level", "Status", "Project", "Bug Date");
        bugsFile.readFromFile();
    }
}
