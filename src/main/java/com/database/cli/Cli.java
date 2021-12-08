package com.database.cli;

import com.database.employee.Employee;
import com.database.reader.Reader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cli {
    private Logger logger = LogManager.getLogger("SpartaDatabaseProject");
    private Reader reader = new Reader();
    private int option;
    private File chosenFile;

    public void menu() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please Select an Option" + "\n" + "'1' for EmployeeRecords.csv" + "\n" + "'2' for EmployeeRecordsLarge.csv"
                + "\n" + "'3' to Upload File");
        try {
            option = Integer.parseInt(input.nextLine().trim());
            switch (option) {
                case (1):
                    System.out.println("\nEmployeeRecords.csv Selected");
                    selectFile(option);
                    break;
                case (2):
                    System.out.println("\nEmployeeRecordsLarge.csv Selected");
                    selectFile(option);
                    break;
                case (3):
                    System.out.println("\nUpload File Selected");
                    selectFile(option);
                    break;
                default:
                    System.err.println("\nError: Option not Available \n");
                    menu();

            }
        } catch (NumberFormatException e) {
            System.err.println("\nError: Please Insert a Valid Input\n");
            logger.log(Level.ERROR, "NumberFormatException Thrown", e);
            menu();
//            e.printStackTrace();

        }
    }

    List<Employee> employeeList = new ArrayList<>();

    public void selectFile(int selection) {
        JFileChooser chooser;
        try {
            switch (selection) {
                case (1):
//                chooser = new JFileChooser("src/main/resources/EmployeeRecords.csv");
                    chosenFile = new File("src/main/resources/EmployeeRecords.csv");
                    System.out.println(chosenFile.getName());
                    reader.readCSV(chosenFile);
                    System.out.println(employeeList);
                    break;
                case (2):
//                chooser = new JFileChooser("src/main/resources/testCSV.csv");
                    chosenFile = new File("src/main/resources/EmployeeRecordsLarge.csv");
                    System.out.println(chosenFile.getName());
                    reader.readCSV(chosenFile);
                    System.out.println(employeeList);
                    break;
                case (3):
                    JFrame frame = new JFrame();
                    frame.setAlwaysOnTop(true);
                    chooser = new JFileChooser(System.getProperty("user.home") + "/Downloads/");
                    int choice = chooser.showOpenDialog(frame);
                    if (choice != JFileChooser.APPROVE_OPTION) return;
                    chosenFile = chooser.getSelectedFile();
                    System.out.println(chosenFile.getName());
                    reader.readCSV(chosenFile);
                    System.out.println(employeeList);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error:Invalid Option", "Error", JOptionPane.ERROR_MESSAGE);
                    menu();
            }
        } catch (NumberFormatException e) {
            System.err.println("\nError: Please Insert a Valid Input\n");
            logger.log(Level.ERROR, "NumberFormatException Thrown", e);
            menu();
//            e.printStackTrace();

        }
    }
}
