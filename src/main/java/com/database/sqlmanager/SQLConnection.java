package com.database.sqlmanager;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnection
{
    private static Connection theConnection = null;
    public static Connection getConnection() {
        if (theConnection == null)
        {
            Properties props = new Properties();
            try {
                props.load(new FileReader("connection.properties"));
            } catch (IOException e) {
                System.out.println("Could not load connection.properties");
            }
            String url = props.getProperty("dburl");
            String userid = props.getProperty("dbuser");
            String password = props.getProperty("dbpassword");
            try {
                return DriverManager.getConnection(url, userid, password);
            } catch (SQLException e) {
                System.out.println("Could not establish connection, something wrong with: connection properties: dburl / dbuser / dbpassword");
            }
        }
        return theConnection;
    }

    public static void closeConnection()
    {
        try {
            if(theConnection!= null) theConnection.close();
        } catch (SQLException e) {
            System.out.println("Something wrong happened whilst closing the connection!");
        }
    }
}
