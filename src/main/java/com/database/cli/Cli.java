package com.database.cli;

import com.database.controller.DatabaseController;
import com.database.employee.Employee;
import com.database.reader.Reader;
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

    public Cli()
    {
        controller = new DatabaseController();
    }

    public void menu() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please Select an Option" + "\n" + "'1' for EmployeeRecords.csv" + "\n" + "'2' for EmployeeRecordsLarge.csv"
                + "\n" + "'3' to Upload File");
        try {
            int option = Integer.parseInt(input.nextLine().trim());
            switch (option)
            {
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
//            e.printStackTrace();

        }
        input.close();
    }

    public void selectFile(int selection) {
        JFileChooser chooser;
        try {
            switch (selection)
            {
                case (1) -> {
//                chooser = new JFileChooser("src/main/resources/EmployeeRecords.csv");
                    chosenFile = new File("src/main/resources/EmployeeRecords.csv");
                    controller.parseCSV("src/main/resources/EmployeeRecords.csv");
                    printEmployees(controller.head(10));
                }
                case (2) -> {
//                chooser = new JFileChooser("src/main/resources/testCSV.csv");
                    controller.parseCSV("src/main/resources/EmployeeRecordsLarge.csv");
                    printEmployees(controller.head(10));
                }
                case (3) -> {
                    JFrame frame = new JFrame();
                    frame.setAlwaysOnTop(true);
//                    chooser = new JFileChooser(System.getProperty("user.home") + "/Downloads/");
                    chooser = new JFileChooser(System.getProperty("user.dir"));
                    int choice = chooser.showOpenDialog(frame);
                    if (choice != JFileChooser.APPROVE_OPTION) return;
                    chosenFile = chooser.getSelectedFile();
                    String path = chooser.getSelectedFile().getPath();
                    controller.parseCSV(path);
                    printEmployees(controller.head(10));
                    frame.dispose();
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
//            e.printStackTrace();
        }
    }

    private void printEmployees(Set<Employee> set)
    {
        for(Employee e : set)
        {
            System.out.println(e.toString());
        }
    }
}
