package com.database.reader;

import com.database.employee.Employee;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Reader {
    private Logger logger = LogManager.getLogger("SpartaDatabaseProject");
    private HashSet<Employee> filteredData;
    private List<Employee> duplicateData;
    private List<String> corruptDataLines;
    private int duplicateDataCounter;
    private int corruptedDataCounter;


    public void readCSV(File fileName) {
        duplicateDataCounter = 0;
        corruptedDataCounter = 0;
        corruptDataLines = new ArrayList<>();
        filteredData = new HashSet<>();
        duplicateData = new ArrayList<>();
        try(Scanner reader = new Scanner(fileName)) {
            //discard the first line
            if (reader.hasNextLine())
                reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                processEmployeeLine(line);
            }
        } catch (IOException e) {
            System.out.println("File does not exist");
//            e.printStackTrace();
            //TODO ADD LOGGER HERE
            logger.log(Level.ERROR, "IOException Thrown", e);
        }
    }

    public void readCsvWithLambdas(File fileName) {
        duplicateDataCounter = 0;
        corruptedDataCounter = 0;
        corruptDataLines = new ArrayList<>();
        filteredData = new HashSet<>();
        duplicateData = new ArrayList<>();
        Path path = fileName.toPath();
        try(Stream<String> stream = Files.lines(path)) {
            stream.forEach(this::processEmployeeLine);
        } catch (IOException e) {
            System.out.println("File does not exist");
//            e.printStackTrace();
            //TODO ADD LOGGER HERE
            logger.log(Level.ERROR, "IOException Thrown", e);
        }
    }

    private void processEmployeeLine(String line){
        if(Employee.isValid(line)){
            // adds to filtered data and checking if duplicates exist
            if(!filteredData.add(createEmployee(line))){
                duplicateData.add(createEmployee(line));
                duplicateDataCounter++;
                //TODO ADD LOGGER HERE
                logger.log(Level.ERROR, "Duplicate Entry Logged", createEmployee(line));


            }
        }
        else{
            // not counting empty lines as corrupt entry
            if(!line.equals("")){
                corruptDataLines.add(line);
                corruptedDataCounter++;
                //TODO ADD LOGGER HERE
                logger.log(Level.ERROR, "Corrupted Entry Logged", line);

            }
        }
    }


    public HashSet<Employee> getFilteredData() {
        return filteredData;
    }

    public List<Employee> getDuplicateData() {
        return duplicateData;
    }


    public int getCorruptedDataCounter() {
        return corruptedDataCounter;
    }

    public int getDuplicateDataCounter() {
        return duplicateDataCounter;
    }

    private Employee createEmployee(String line) {
        return new Employee(line);
    }

    public List<String> getCorruptDataLines(){
        return corruptDataLines;
    }

    public Set<Employee> head(int n)
    {
        if( n > filteredData.size())
        {
            System.out.println("The set only has " + filteredData.size() + " elements." );
            return null;
        }

        Set<Employee> out = new HashSet<>(n);
        Iterator<Employee> iterator = filteredData.iterator();
        int counter = 0;
        while(counter < n)
        {
            out.add(iterator.next());
            counter++;
        }
        return out;
    }
}
