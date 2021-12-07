package com.database.cli;

import com.database.EmployeeList;
import com.database.reader.Reader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Cli {
    private Logger logger = LogManager.getLogger("SpartaDatabaseProject");
    private EmployeeList employeeList = new EmployeeList();
    private Reader reader = new Reader();
    private int option;
    private File chosenFile;

    public void menu() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please Select an Option" + "\n" + "'1' for File 1" + "\n" + "'2' for File 2 "
                + "\n" + "'3' to Upload File");
        try {
            option = Integer.parseInt(input.nextLine().trim());
            switch (option) {
                case (1):
                    System.out.println("\nFile 1 Selected");
                    selectfile(option);
                    break;
                case (2):
                    System.out.println("\nFile 2 Selected");
                    selectfile(option);
                    break;
                case (3):
                    System.out.println("\nUpload File Selected");
                    selectfile(option);
                    break;
                default:
                    System.err.println("\nError: Option not Available \n");
                    menu();

            }
        } catch (NumberFormatException e) {
            System.err.println("\nError: Please Insert a Valid Input\n");
            logger.log(Level.ERROR, e);
            logger.error("Exception Thrown");
            menu();
//            e.printStackTrace();

        }
    }

    public void selectfile(int selection) {
        JFileChooser chooser;
        switch (selection) {
            case (1):
//                chooser = new JFileChooser("src/main/resources/EmployeeRecords.csv");
                chosenFile = new File("src/main/resources/EmployeeRecords.csv");
                System.out.println(chosenFile.getName());
                reader.readCSV(chosenFile,employeeList.getEmployeeList());
                System.out.println(employeeList);
                break;
            case (2):
//                chooser = new JFileChooser("src/main/resources/testCSV.csv");
                chosenFile = new File("src/main/resources/testCSV.csv");
                System.out.println(chosenFile.getName());
                reader.readCSV(chosenFile,employeeList.getEmployeeList());
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
                reader.readCSV(chosenFile,employeeList.getEmployeeList());
                System.out.println(employeeList);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Error:Invalid Option", "Error", JOptionPane.ERROR_MESSAGE);
                menu();
        }
    }
}
