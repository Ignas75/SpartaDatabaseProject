package com.database.sqlmanager;

import com.database.cli.Cli;
import com.database.employee.Employee;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

public class SQLObject extends Thread {
    private Connection connection = null;
    private String url;
    private String userid;
    private String password;
    private String databaseName;
    private String tableName;

    private final HashSet<Employee> batch;

    public SQLObject(){batch = null;}

    public SQLObject(HashSet<Employee> batch){
        this.batch = batch;
    }

    /* ---------------------------------------------------------------------------------------------------------
    NOTES FOR ANY DEVS: If the database isn't working properly, you need to set up your connection.properties:

    dburl=jdbc:mysql://localhost:3306/ << Notice how this doesn't have the database name!
    dbuser=YOUR_USERNAME_HERE
    dbpassword=YOUR_PASSWORD_HERE
    dbname=employee_records
    tablename=employees
    ---------------------------------------------------------------------------------------------------------- */

    public void CreateStatement() {
        String query = "CREATE TABLE " + databaseName + " (EmployeeID int, Title VARCHAR (6), " +
                "FirstName VARCHAR (35), " + "MiddleInital VARCHAR (1), " + "LastName VARCHAR(35), " +
                "Gender VARCHAR (1), " + "Email VARCHAR (62), " + "DOB VARCHAR(10), " + "DateOfJoining VARCHAR(10), " + "Salary int )";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        } catch (SQLException e) {
            e.printStackTrace();
            Cli.logger.log(Level.ERROR, "SQLException Thrown", e);
            // TODO ADD LOGGER?!?
        }
    }

    public void InsertStatement(Employee employee) {
        String query = "INSERT INTO " + tableName +"(EmployeeID, Title, FirstName," +
                " MiddleInital, LastName, Gender, Email, DOB," +
                " DateOfJoining, Salary)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                //" VALUES (5555, Mr., Leo, H, Hoang, M, asdasd@gmail.com, 12/31/1992, 11/08/2021, 21000)";
        try (PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getTitle());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getMiddleName());
            statement.setString(5, employee.getLastName());
            statement.setString(6, employee.getGender());
            statement.setString(7, employee.getEmail());
            statement.setString(8, employee.getDob());
            statement.setString(9, employee.getJoinDate());
            statement.setInt(10, employee.getSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO ADD LOGGER?!?
            Cli.logger.log(Level.ERROR, "SQLException Thrown", e);
        }
    }

    public void establishConnection() {
        try {
            if (connection == null) {
                Properties properties = new Properties();
                File file = new File("src/main/resources/connection.properties");
                properties.load(new FileReader(file));
                url = properties.getProperty("dburl");
                userid = properties.getProperty("dbuser");
                password = properties.getProperty("dbpassword");
                databaseName = properties.getProperty("dbname");
                tableName = properties.getProperty("tablename");
                connection = DriverManager.getConnection(url+databaseName, userid, password);
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
    }

    public void batchInsert(HashSet<Employee> employees) {
        String query = "INSERT INTO " + tableName +"(EmployeeID, Title, FirstName," +
                " MiddleInital, LastName, Gender, Email, DOB," +
                " DateOfJoining, Salary)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Employee e : employees) {
                statement.setInt(1, e.getId());
                statement.setString(2, e.getTitle());
                statement.setString(3, e.getFirstName());
                statement.setString(4, e.getMiddleName());
                statement.setString(5, e.getLastName());
                statement.setString(6, e.getGender());
                statement.setString(7, e.getEmail());
                statement.setString(8, e.getDob());
                statement.setString(9, e.getJoinDate());
                statement.setInt(10, e.getSalary());

                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO ADD LOGGER?!?
            Cli.logger.log(Level.ERROR, "SQLException Thrown", e);
        }
    }

    public void batchInsert() {
        String query = "INSERT INTO " + tableName +"(EmployeeID, Title, FirstName," +
                " MiddleInital, LastName, Gender, Email, DOB," +
                " DateOfJoining, Salary)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Employee e : this.batch) {
                statement.setInt(1, e.getId());
                statement.setString(2, e.getTitle());
                statement.setString(3, e.getFirstName());
                statement.setString(4, e.getMiddleName());
                statement.setString(5, e.getLastName());
                statement.setString(6, e.getGender());
                statement.setString(7, e.getEmail());
                statement.setString(8, e.getDob());
                statement.setString(9, e.getJoinDate());
                statement.setInt(10, e.getSalary());

                statement.addBatch();
            }
            int[] out = statement.executeBatch();
            System.out.println(Arrays.toString(out));
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO ADD LOGGER?!?
            Cli.logger.log(Level.ERROR, "SQLException Thrown", e);
        }
    }

    public void selectQuery(String column, String condition)
    {
        String query = "SELECT * FROM " + tableName + " WHERE " + column + " = " + "'" + condition +"'";

        try (Statement statement = connection.createStatement())
        {
            System.out.println("Results for query: " + query);
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                System.out.print(resultSet.getString(1));
                System.out.print(", " + resultSet.getString(2));
                System.out.print(", " + resultSet.getString(3));
                System.out.print(", " + resultSet.getString(4));
                System.out.print(", " + resultSet.getString(5));
                System.out.print(", " + resultSet.getString(6));
                System.out.print(", " + resultSet.getString(7));
                System.out.print(", " + resultSet.getString(8));
                System.out.print(", " + resultSet.getString(9));
                System.out.print(", " + resultSet.getString(10));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO ADD LOGGER?!?
            Cli.logger.log(Level.ERROR, "SQLException Thrown", e);
        }
    }

    @Override
    public void run() {
        batchInsert(batch);
        closeConnection();
        System.out.println("Connection closed");
    }

    public void closeConnection()
    {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
