package com.database.cli;

import com.database.controller.DatabaseController;
import com.database.employee.Employee;
import com.database.sqlmanager.InitializeDB;
import com.database.sqlmanager.SQLObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.util.*;

public class Cli {
    public static final Logger logger = LogManager.getLogger("SpartaDatabaseProject");
    private File chosenFile;
    private DatabaseController controller;


    public Cli() {
        controller = new DatabaseController();
    }


    public void menu() throws InterruptedException {
        InitializeDB.createDatabase();
        Scanner input = new Scanner(System.in);

        System.out.println("Please Select an Option" + "\n" + "'1' for EmployeeRecords.csv" + "\n" + "'2' for EmployeeRecordsLarge.csv"
                + "\n" + "'3' to Upload File");
        try {
            int option = Integer.parseInt(input.nextLine().trim());
            switch (option) {
                case (1) -> {
                    System.out.println("\nEmployeeRecords.csv Selected");
                    selectFile(option);
                }
                case (2) -> {
                    System.out.println("\nEmployeeRecordsLarge.csv Selected");
                    selectFile(option);
                }
                case (3) -> {
                    System.out.println("\nUpload File Selected");
                    selectFile(option);
                }
                default -> {
                    System.err.println("\nError: Option not Available \n");
                    menu();
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("\nError: Please Insert a Valid Input\n");
            logger.log(Level.ERROR, "NumberFormatException Thrown", e);
            menu();

        }
        input.close();
    }

    public void selectFile(int selection) throws InterruptedException {
        JFileChooser chooser;
        try {
            switch (selection) {
                case (1) -> {
                    chosenFile = new File("src/main/resources/EmployeeRecords.csv");
                    controller.parseCSV("src/main/resources/EmployeeRecords.csv");
                    System.out.println("Please Insert Number of Threads you like to use");
                    System.out.println("Please note that the minimum number of threads allowed is 3");
                    startBatchInsert(numberOfThreads());
                    selectQuery();
                }
                case (2) -> {
                    controller.parseCSV("src/main/resources/EmployeeRecordsLarge.csv");
                    System.out.println("Please Insert Number of Threads you like to use");
                    System.out.println("Please note that the minimum number of threads allowed is 3");
                    startBatchInsert(numberOfThreads());
                    selectQuery();
                }
                case (3) -> {
                    JFrame frame = new JFrame();
                    frame.setAlwaysOnTop(true);
                    chooser = new JFileChooser(System.getProperty("user.dir"));
                    int choice = chooser.showOpenDialog(frame);
                    if (choice != JFileChooser.APPROVE_OPTION) return;
                    else if (choice != JFileChooser.CANCEL_OPTION) return;
                    chosenFile = chooser.getSelectedFile();
                    String path = chooser.getSelectedFile().getPath();
                    controller.parseCSV(path);
                    printEmployees(controller.head(10));
                    frame.dispose();
                    System.out.println("Please Insert Number of Threads you like to use");
                    System.out.println("Please note that the minimum number of threads allowed is 3");
                    startBatchInsert(numberOfThreads());
                    selectQuery();
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Error:Invalid Option", "Error", JOptionPane.ERROR_MESSAGE);
                    menu();
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("\nError: Please Insert a Valid Input\n");
            logger.log(Level.ERROR, "NumberFormatException Thrown", e);
            menu();
        }
    }

    private void startBatchInsert(int nThreads) throws InterruptedException {
        System.out.println("Loading...");
        long time = System.nanoTime();
        controller.batchInsert(controller.getDataSet(), nThreads);
        long endtime = System.nanoTime() - time;
        endtime = endtime / 1000000;
        System.out.print("Completed in: ");
        System.out.print(Double.toString(endtime).substring(0, Double.toString(endtime).length() - 3));
        System.out.print(" Milliseconds \n");
    }

    private void printEmployees(Set<Employee> set) {
        for (Employee e : set) {
            System.out.println(e.toString());
        }
    }

    public void selectQuery() throws InterruptedException {
        SQLObject sqlObject = new SQLObject();
        String condition;
        sqlObject.establishConnection();
        System.out.println("\nPlease Select a Column you'd like to query" + "\n" + "'1' for Employee ID" + "\n"
                + "'2' for Title" + "\n" + "'3' for First Name" + "\n" + "'4' for Middle Initial" + "\n"
                + "'5' for Last Name" + "\n" + "'6' for Gender" + "\n" + "'7' for Email" + "\n" + "'8' for DOB"
                + "\n" + "'9' for Date of Joining"
                + "\n" + "'10' for Salary");
        Scanner input = new Scanner(System.in);

        try {
            int option = Integer.parseInt(input.nextLine().trim());
            switch (option) {
                case (1) -> {
                    System.out.println("\nEmployee ID Selected");
                    System.out.println("\nPlease Insert the Employee ID");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("EmployeeID", condition);
                }
                case (2) -> {
                    System.out.println("\nTitle Selected");
                    System.out.println("\nPlease Insert the Title");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("Title", condition);
                }
                case (3) -> {
                    System.out.println("\nFirst Names Selected");
                    System.out.println("\nPlease Insert the First Name");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("FirstName", condition);
                }
                case (4) -> {
                    System.out.println("\nMiddle Initial Selected");
                    System.out.println("\nPlease Insert the Middle Initial");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("MiddleInitial", condition.toUpperCase());
                }
                case (5) -> {
                    System.out.println("\nLast Name Selected");
                    System.out.println("\nPlease Insert the Last Name");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("LastName", condition);
                }
                case (6) -> {
                    System.out.println("\nGender Selected");
                    System.out.println("\nPlease Insert the Gender (E.g. M or F) ");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("Gender", condition.toUpperCase());
                }
                case (7) -> {
                    System.out.println("\nEmail Selected");
                    System.out.println("\nPlease Insert the Email");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("Email", condition);
                }
                case (8) -> {
                    System.out.println("\nDOB Selected");
                    System.out.println("\nPlease Insert the DOB (E.g. MM/DD/YYYY or M/D/YYYY)");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("DOB", condition);
                }
                case (9) -> {
                    System.out.println("\nDate of Joining Selected");
                    System.out.println("\nPlease Insert the Date of Joining (E.g. MM/DD/YYYY or M/D/YYYY");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("DateOfJoining", condition);
                }
                case (10) -> {
                    System.out.println("\nSalary Selected");
                    System.out.println("\nPlease Insert the Salary");
                    System.err.println("WARNING:Entry is Case Sensitive");
                    condition = input.nextLine().trim();
                    sqlObject.selectQuery("Salary", condition);
                }
                default -> {
                    System.err.println("\nError: Option not Available \n");
                    menu();
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("\nError: Please Insert a Valid Input\n");
            logger.log(Level.ERROR, "NumberFormatException Thrown", e);
            menu();
        }
        sqlObject.closeConnection();
        input.close();
    }

    public int numberOfThreads() {
        int value = 0;
        while (value < 3) {
            try {

                Scanner input = new Scanner(System.in);
                value = Integer.parseInt(input.nextLine().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (value >= 3) {

                return value;
            } else {
                System.out.println("Error:Minimum Number of threads must be 3");
            }
        }
        return value;
    }

}
