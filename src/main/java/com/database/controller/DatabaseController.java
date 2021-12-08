package com.database.controller;

import com.database.reader.Reader;

public class DatabaseController
{
    private Reader reader;

    public DatabaseController()
    {
        Reader reader = new Reader();
    }
    public void parseCSV(String filePath)
    {
        if(!isValidFileType(filePath))
        {
            System.out.println("Invalid file extension");
            //TODO add logging here
        }
        else
        {

        }


    }

    private boolean isValidFileType(String path)
    {
        return path.endsWith(".csv") || path.endsWith(".txt");
    }
}
