package com.database.sqlmanager;

import com.database.cli.Cli;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class InitializeDB {

    private static Connection connection = null;
    private static String url;
    private static String userid;
    private static String password;
    private static String databaseName;
    private static String tableName;


    //
    public static void createDatabase() {

        try {
            if (connection == null) {
                Properties properties = new Properties();
                File prop = new File("src/main/resources/connection.properties");
                properties.load(new FileReader(prop));
                url = properties.getProperty("dburl");
                userid = properties.getProperty("dbuser");
                password = properties.getProperty("dbpassword");
                databaseName = properties.getProperty("dbname");
                tableName = properties.getProperty("tablename");
                connection = DriverManager.getConnection(url, userid, password);
            }
        } catch (IOException e) {
            System.err.println("Could not load connection.properties");
            // TODO ADD LOGGER?!?
            Cli.logger.log(Level.ERROR, "IOException Thrown", e);
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Could not establish connection, something wrong with: connection properties: dburl / dbuser / dbpassword");
            // TODO ADD LOGGER?!?
            Cli.logger.log(Level.ERROR, "SQLException Thrown", e);
            e.printStackTrace();
        }

        PreparedStatement statement = null;
        String createDB = "CREATE DATABASE " + databaseName;
        String dropDB = "DROP DATABASE IF EXISTS " + databaseName;
        String dropTable = "DROP TABLE IF EXISTS " + tableName;
        String createTable = "CREATE TABLE " + tableName + "(" +
                "EmployeeID int," +
                "Title VARCHAR (6)," +
                "FirstName VARCHAR (35)," +
                "MiddleInital VARCHAR (3)," +
                "LastName VARCHAR(35)," +
                "Gender VARCHAR (1)," +
                "Email VARCHAR (62)," +
                "DOB VARCHAR (10)," +
                "DateOfJoining VARCHAR (10)," +
                "Salary int)";
        try { // Can't try with resources as we change statement with a new connection to a newly created DB
            // Try with resources also makes the variable final.
            statement = connection.prepareStatement(createDB);
            statement.executeUpdate(dropDB);
            statement.executeUpdate(createDB);
            System.out.println("Database created successfully...");

            connection = DriverManager.getConnection(url + databaseName, userid, password);
            statement = connection.prepareStatement(createTable);
            statement.executeUpdate(dropTable);
            statement.executeUpdate(createTable);
            System.out.println("Table created successfully...");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
