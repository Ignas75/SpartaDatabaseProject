package com.database;

import com.database.cli.Cli;
import com.database.employee.Employee;
import com.database.reader.Reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        Cli cli = new Cli();
        cli.menu();

//        String fileName = "src/main/resources/EmployeeRecords.csv";
//        try {
//            File file = new File(fileName);
//            List<Employee> data = new ArrayList<>();
//            Reader reader = new Reader();
//            reader.readCSV(file, data);
//            List<Employee> employeeData = reader.filterDuplicates(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
