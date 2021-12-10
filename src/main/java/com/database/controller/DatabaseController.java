package com.database.controller;

import com.database.employee.Employee;
import com.database.reader.Reader;
import com.database.sqlmanager.SQLObject;

import java.io.File;
import java.util.Set;

public class DatabaseController
{
    private Reader reader;
    private SQLObject sqlObject;
    public void parseCSV(String filePath)
    {
        reader = new Reader();

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

    public Set<Employee> head(int n)
    {
        return reader.head(n);
    }

    public void connectToDB()
    {
        sqlObject = new SQLObject();
        sqlObject.establishConnection();
    }

    public void insertInDatabase(Employee e)
    {
        sqlObject.InsertStatement(e);
    }

    public void createTable()
    {
        sqlObject.CreateStatement();
    }

    private boolean isValidFileType(String path)
    {
        return path.endsWith(".csv") || path.endsWith(".txt");
    }
}
