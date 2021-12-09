package com.database.controller;

import com.database.employee.Employee;
import com.database.reader.Reader;

import java.io.File;
import java.util.Set;

public class DatabaseController
{
    private Reader reader;

    public void parseCSV(String filePath)
    {
        Reader reader = new Reader();

        if(!isValidFileType(filePath))
        {
            System.out.println("Invalid file extension");
            //TODO add logging here
        }
        else
        {
            File file = new File(filePath);
            reader.readCSV(file);
        }
    }

    public Set<Employee> getElements(int n)
    {
        return reader.head(n);
    }

    private boolean isValidFileType(String path)
    {
        return path.endsWith(".csv") || path.endsWith(".txt");
    }
}
