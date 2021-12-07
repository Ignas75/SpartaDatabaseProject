package com.database.reader;

import com.database.employee.Employee;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Reader
{
    private List<Employee> data;
    private List<Employee> filteredData;
    private List<Employee> duplicateData;

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

    public List<Employee> filterDuplicates(List<Employee> list){
        List<Employee> filteredList = new ArrayList<>();
        duplicateData = new ArrayList<>();
        Collections.sort(list);
        Employee prevInserted = list.get(0);
        filteredList.add(prevInserted);
        for(int i = 1; i<list.size(); i++){
            Employee currentEmployee = list.get(i);
            if(prevInserted.compareTo(currentEmployee) == 0){
                duplicateData.add(currentEmployee);
                System.out.println("Duplicate with " + currentEmployee.getID());
                // TODO: ADD LOGGING OF DUPLICATES HERE
            }
            else{
                System.out.println("added employee");
                filteredList.add(currentEmployee);
                prevInserted = currentEmployee;
            }
        }
        filteredData = filteredList;
        return filteredList;
    }

    private Employee createEmployee(String line)
    {
        return new Employee(line);
    }
}
