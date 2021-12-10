package com.database.sqlmanager;

import com.database.cli.Cli;
import com.database.employee.Employee;
import org.apache.logging.log4j.Level;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

public class SQLObject {
    private Connection connection = null;
    private final String databaseName = "employee_records";

    public void CreateStatement() {
        String query = "CREATE TABLE " + databaseName + " (EmployeeID int, Title VARCHAR (6), " +
                "FirstName VARCHAR (35), " + "MiddleInital VARCHAR (3), " + "LastName VARCHAR(35), " +
                "Gender VARCHAR (1), " + "Email (62), " + "DOB DATE, " + "DateOfJoining DATE, " + "Salary int )";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        } catch (SQLException e) {
            e.printStackTrace();
            Cli.logger.log(Level.ERROR, "SQLException Thrown", e);
            // TODO ADD LOGGER?!?
        }
    }

    public void InsertStatement(Employee employee) {
        String query = "INSERT INTO " + databaseName + " (EmployeeID int, Title VARCHAR (6), FirstName VARCHAR (35)," +
                " MiddleInital VARCHAR (3), LastName VARCHAR(35), Gender VARCHAR (1), Email (62), DOB DATE," +
                " DateOfJoining DATE, Salary int )" +
                " VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
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
                properties.load(new FileReader("connection.properties"));
                String url = properties.getProperty("database_url");
                String userid = properties.getProperty("database_user");
                String password = properties.getProperty("database_password");
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
    }

    // Untested...
    public void batchInsert(HashSet<Employee> employees) {
        String query = "INSERT INTO " + databaseName + " (EmployeeID int, Title VARCHAR (6), FirstName VARCHAR (35)," +
                " MiddleInital VARCHAR (3), LastName VARCHAR(35), Gender VARCHAR (1), Email (62), DOB DATE," +
                " DateOfJoining DATE, Salary int )" +
                " VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int i = 0;
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
                i++;

                if (i % 1000 == 0 || i == employees.size()) {
                    statement.executeBatch(); // Execute every 1000 items.
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO ADD LOGGER?!?
            Cli.logger.log(Level.ERROR, "SQLException Thrown", e);

        }
    }

    // Creates database????
    public void createDatabase() {
        String create = "CREATE DATABASE " + databaseName;
        try (PreparedStatement statement = connection.prepareStatement(create)) {
            String sql = "CREATE DATABASE STUDENTS";
            statement.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
