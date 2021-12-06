package com.database.reader;

import com.database.employee.Employee;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Reader
{
    public void readCSV(File fileName, List<Employee> list)
    {
        try
        {
            Scanner reader = new Scanner(fileName);
            //discard the first line
            if(reader.hasNextLine())
                reader.nextLine();
            while(reader.hasNextLine())
            {
                String line = reader.nextLine();
                list.add(createEmployee(line));
            }
        }
        catch(IOException e)
        {
            System.out.println("File does not exist");
            //TODO ADD LOGGER HERE
        }
    }

    private Employee createEmployee(String line)
    {
        return new Employee(line);
    }
}
