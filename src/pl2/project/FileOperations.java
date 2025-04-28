/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roaam
 */
public class FileOperations {

    private File file;

    public FileOperations(String path) {
        file = new File(path);
    }

    public int getNumColumns() {
        int counter = 0;
        try {
            Scanner fr = new Scanner(file);
            String[] array = fr.nextLine().split("-");
            counter = array.length;
            return counter;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return counter;
    }

    public int getNumRows() {
        int counter = 0;
        try {
            Scanner fr = new Scanner(file);
            while (fr.hasNext()) {
                counter++;
                fr.nextLine();
            }
            return counter;
        } catch (FileNotFoundException ex) {
            return counter;
        }
    }

    public void writeInFile(String line) {
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.write(line);
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    Add to file
    public int appendInFile(String line) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.append(line + "\n");
            fw.close();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

//    Read all the File
    public void readFromFile() {
        if (!file.exists()) {
            System.out.println("File Doesn't Exist");
            return;
        }
        int[] arrColumns = new int[getNumColumns()];
        for (int i = 0; i < getNumColumns(); i++) {
            arrColumns[i] = i;
        }
        readFromFile(arrColumns);
    }

    //    Read a Specific column from file
    public int readFromFile(int columnIndex) {
        if (!file.exists()) {
            System.out.println("File Doesn't Exist");
            return 0;
        }
        try {
            Scanner fr = new Scanner(file);
            while (fr.hasNext()) {
                String[] lines = fr.nextLine().split("-");
                System.out.println(lines[columnIndex]);
            }
            return 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

//    Read Specific columns from a file
    public int readFromFile(int... indexes) {
        if (!file.exists()) {
            System.out.println("File Doesn't Exist");
            return 0;
        }
        try {
            Scanner fr = new Scanner(file);
            while (fr.hasNext()) {
                String[] lines = fr.nextLine().split("-");
                for (int i = 0; i < indexes.length; i++) {
                    if (!lines[indexes[i]].equals(" ")) {
                        System.out.printf("%-20s", lines[indexes[i]]);
                        System.out.printf(" ");
                    }
                }
                if (!lines[indexes[0]].equals(" ")) {
                    System.out.print("\n");
                }
            }
            return 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

//    Here we get all the user's info by their id `PK`
    public String updateInFile(int id, String newValue, int updateIndex) {
        if (!file.exists()) {
            System.out.println("File Doesn't Exist");
            return null;
        }
//          making this counter to check if the user exists in the file or not
        newValue = newValue.trim();
        int counter = 0;
        try {
            Scanner fr = new Scanner(file);
            String[] line;
            String temp = "";
            while (fr.hasNext()) {
//                making each line into an array of strings to make it easy to be accessed
                line = fr.nextLine().split("-");
//                finding the user that we want to update his info by his id but after casting it into a string
//                line[0] => id column in the file
                if (line[0].equals(id + "")) {
//                Checking if the old value in the file equals the new entered value
                    if (line[updateIndex].equals(newValue)) {
//                    Exits the function
                        return "Already the same";
                    }
//                    Changing the value and adding it to a temporary String `temp` with formatting 
                    line[updateIndex] = newValue;
                } else {
                    counter++;
                }
//                    making it dynamic to suit different files with different number of columns
                for (int i = 0; i < getNumColumns(); i++) {
                    if (i == getNumColumns() - 1) {
                        temp += line[i] + "\n";
                    } else {
                        temp += line[i] + "-";
                    }
                }
            }
//            Overriting the old data that was in the file with the new updated data
            writeInFile(temp);
//            checks if the user doesn't exist
            if (counter == getNumRows()) {
                return "User Doesn't Exist";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Updated Successfully";
    }

    //    Here we get all the user's info by their id `PK`
    public String deleteFromFile(int id) {
        if (!file.exists()) {
            System.out.println("File Doesn't Exist");
            return null;
        }
//          making this counter to check if the user exists in the file or not
        int counter = 0;
        int initialNumRows = getNumRows();
        try {
            Scanner fr = new Scanner(file);
            String[] line;
            String temp = "";
            while (fr.hasNext()) {
//                making each line into an array of strings to make it easy to be accessed
                line = fr.nextLine().split("-");
//                finding the user that we want to update his info by his id but after casting it into a string
//                line[0] => id column in the file
                if (line[0].equals(id + "")) {
                    for (int i = 0; i < getNumColumns(); i++) {
                        if (i == getNumColumns() - 1) {
                            temp += " \n";
                        } else {
                            temp += " -";
                        }
                    }
                } else {
                    counter++;
                    for (int i = 0; i < getNumColumns(); i++) {
                        if (i == getNumColumns() - 1) {
                            temp += line[i] + "\n";
                        } else {
                            temp += line[i] + "-";
                        }
                    }
                }
            }
//            Overriting the old data that was in the file with the new updated data
            writeInFile(temp);
//            checks if the user doesn't exist
            if (counter == initialNumRows) {
                return "Doesn't Exist";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Deleted Successfully";
    }

//    Returns the role of the user if exists and returns null if the user does't exist
    public String userRole(String username, String password) {
        if (!file.exists()) {
            System.out.println("File Doesn't Exist");
            return null;
        }
        try {
            Scanner fr = new Scanner(file);
            String[] line;
            while (fr.hasNext()) {
                line = fr.nextLine().split("-");
                if (line[1].equals(username) && line[2].equals(password)) {
                    return line[5];
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getIdByName(String name, String password) {
        try {
            Scanner fr = new Scanner(file);
            while (fr.hasNext()) {
                String[] line = fr.nextLine().split("-");
                if (line[1].equals(name) && line[2].equals(password)) {
                    return Integer.parseInt(line[0]);
                }
            }
            return -1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public String returnRows(int searchColumnIndex, String searchValue) {
        if (!file.exists()) {
            System.out.println("File Doesn't Exist");
            return null;
        }
        String row = "";
        try {
            Scanner fr = new Scanner(file);
            while (fr.hasNext()) {
                String[] tempRow = fr.nextLine().split("-");
                if (tempRow[searchColumnIndex].equals(searchValue)) {
                    for (int i = 0; i < tempRow.length; i++) {
                        if (i == tempRow.length - 1) {
                            row += tempRow[i] + "\n";
                        } else {
                            row += tempRow[i] + "-";
                        }
                    }
                }
            }
            if (row.equals("")) {
                return null;
            } else {
                return row;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
}
