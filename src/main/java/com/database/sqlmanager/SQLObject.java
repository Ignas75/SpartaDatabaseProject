package com.database.sqlmanager;

import com.database.factories.ConnectionFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class SQLObject {

    private Connection connection = null;
    public PreparedStatement createStatement = null;
    public PreparedStatement insertStatement = null;
    private String databaseName = "employee_records";


    public void CreateStatement() {
        String statement = "CREATE TABLE " + databaseName + " (EmployeeID int, Title VARCHAR (6), " +
                "FirstName VARCHAR (35), " + "MiddleInital VARCHAR (3), " + "LastName VARCHAR(35), " +
                "Gender VARCHAR (1), " + "Email (62), " + "DOB DATE, " + "DateOfJoining DATE, " + "Salary int )";

        if (createStatement == null) {
            try {
                createStatement = establishConnection().prepareStatement(
                        statement);
            } catch (SQLException e) {
                e.printStackTrace();
                // TODO ADD LOGGER?!?
            }
        }
    }

    public void InsertStatement() {
        String statement = "INSERT INTO " + databaseName + " (EmployeeID int, Title VARCHAR (6), FirstName VARCHAR (35)," +
                " MiddleInital VARCHAR (3), LastName VARCHAR(35), Gender VARCHAR (1), Email (62), DOB DATE," +
                " DateOfJoining DATE, Salary int )" +
                " VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
        if (insertStatement == null) {
            try {
                insertStatement = establishConnection().prepareStatement(statement);

            } catch (SQLException e) {
                e.printStackTrace();
                // TODO ADD LOGGER?!?
            }
        }
    }

    public void closeStatement() {
        try {
            if (createStatement != null) createStatement.close();
            if (insertStatement != null) insertStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO ADD LOGGER?!?
        }
    }


    public Connection establishConnection() {
        try {
            if (connection == null) {
                Properties properties = new Properties();
                properties.load(new FileReader("connection.properties"));
                String url = properties.getProperty("database_url");
                String userid = properties.getProperty("database_user");
                String password = properties.getProperty("database_password");
                connection = DriverManager.getConnection(url, userid, password);
            }
        } catch (IOException e) {
            // TODO ADD LOGGER?!?
            System.err.println("Could not load connection.properties");
            e.printStackTrace();


        } catch (SQLException e) {
            // TODO ADD LOGGER?!?
            System.err.println("Could not establish connection, something wrong with: connection properties: dburl / dbuser / dbpassword");
            e.printStackTrace();
        }
        return connection;
    }


//    public static void closeConnection() {
//        try {
//            if (connection != null) connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // TODO ADD LOGGER?!?
//        }
//    }
}