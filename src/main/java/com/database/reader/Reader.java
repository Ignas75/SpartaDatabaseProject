package com.database.reader;

import com.database.employee.Employee;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private Logger logger = LogManager.getLogger("SpartaDatabaseProject");
    private List<Employee> data;
    private List<Employee> filteredData;
    private List<Employee> duplicateData;
    private int duplicateDataCounter;
    private int corruptedDataCounter;


    public void readCSV(File fileName)
    {
        duplicateDataCounter = 0;
        corruptedDataCounter = 0;
        data = new ArrayList<>();
        filteredData = new ArrayList<>();
        duplicateData = new ArrayList<>();
        try
        {

            Scanner reader = new Scanner(fileName);
            //discard the first line
            if (reader.hasNextLine())
                reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                data.add(createEmployee(line));
            }
        } catch (IOException e) {
            System.out.println("File does not exist");
//            e.printStackTrace();
            //TODO ADD LOGGER HERE
            logger.log(Level.ERROR, "IOException Thrown", e);
        }
    }

    public List<Employee> filterDuplicates(List<Employee> list) {
        List<Employee> filteredList = new ArrayList<>();
        duplicateData = new ArrayList<>();
        Collections.sort(list);
        Employee prevInserted = list.get(0);
        filteredList.add(prevInserted);
        for (int i = 1; i < list.size(); i++) {
            Employee currentEmployee = list.get(i);
            if (prevInserted.compareTo(currentEmployee) == 0) {
                duplicateData.add(currentEmployee);
                System.out.println("Duplicate with " + currentEmployee.getID());
                // TODO: ADD LOGGING OF DUPLICATES HERE
                logger.log(Level.INFO, "Duplicate Entry Logged", currentEmployee.getID());
            } else {
                System.out.println("added employee");
                filteredList.add(currentEmployee);
                prevInserted = currentEmployee;
            }
        }
        filteredData = filteredList;
        return filteredList;
    }

    public List<Employee> getData()
    {
        return data;
    }

    public List<Employee> getDuplicateData()
    {
        return duplicateData;
    }

    public List<Employee> getFilteredData()
    {
        return filteredData;
    }

    public int getCorruptedDataCounter()
    {
        return corruptedDataCounter;
    }

    public int getDuplicateDataCounter()
    {
        return duplicateDataCounter;
    }

    private Employee createEmployee(String line)
    {
        return new Employee(line);
    }
}
