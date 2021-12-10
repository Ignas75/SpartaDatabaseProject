package com.database.controller;

import com.database.cli.Cli;
import com.database.employee.Employee;
import com.database.reader.Reader;
import com.database.sqlmanager.SQLObject;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseController
{
    private Reader reader;
    private SQLObject sqlObject;

    public void parseCSV(String filePath)
    {
        reader = new Reader();

        if (!isValidFileType(filePath))
        {
            System.out.println("Invalid file extension");
            //TODO add logging here
            Cli.logger.log(Level.ERROR, "Invalid file extension", filePath);
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

    public void batchInsert(HashSet<Employee> set, int splits)
    {

        List<HashSet<Employee>> theSets = new ArrayList<HashSet<Employee>>(splits);
        for (int i = 0; i < splits; i++)
        {
            theSets.add(new HashSet<Employee>());
        }

        int index = 0;
        for (Employee object : set)
        {
            theSets.get(index++ % splits).add(object);
        }

        for(HashSet<Employee> e :theSets)
        {
            SQLObject connection = new SQLObject();
            connection.establishConnection();
            connection.batchInsert(e);
        }
    }

}
