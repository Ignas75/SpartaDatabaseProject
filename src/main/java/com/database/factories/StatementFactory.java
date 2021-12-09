package com.database.factories;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementFactory {
    public PreparedStatement createStatement = null;
    public PreparedStatement insertStatement = null;
    private String databaseName = "employee_records";

    public PreparedStatement getCreateStatement() {
        if (createStatement == null) {
            try {
                createStatement = ConnectionFactory.getConnection().prepareStatement(
                        "CREATE TABLE " + databaseName +
                                " (EmployeeID int, " +
                                "Title VARCHAR (6), " +
                                "FirstName VARCHAR (35), " +
                                "MiddleInital VARCHAR (3), " +
                                "LastName VARCHAR(35), " +
                                "Gender VARCHAR (1), " +
                                "Email (62), " +
                                "DOB DATE, " +
                                "DateOfJoining DATE, " +
                                "Salary int )");
            } catch (SQLException e) {
                e.printStackTrace();
                // TODO ADD LOGGER?!?
            }
        }
        return createStatement;
    }

    public PreparedStatement getInsertStatement() {
        if (insertStatement == null) {
            try {
                insertStatement = ConnectionFactory.getConnection().prepareStatement(
                        "INSERT INTO " + databaseName +
                                " (EmployeeID int, " +
                                "Title VARCHAR (6), " +
                                "FirstName VARCHAR (35), " +
                                "MiddleInital VARCHAR (3), " +
                                "LastName VARCHAR(35), " +
                                "Gender VARCHAR (1), " +
                                "Email (62), " +
                                "DOB DATE, " +
                                "DateOfJoining DATE, " +
                                "Salary int )" +
                                "VALUES " +
                                "(?," +
                                "?," +
                                "?," +
                                "?," +
                                "?," +
                                "?," +
                                "?," +
                                "?," +
                                "?," +
                                "?)");

            } catch (SQLException e) {
                e.printStackTrace();
                // TODO ADD LOGGER?!?
            }
        }
        return insertStatement;
    }

    public void closeStatement() {
        try {
            if (createStatement != null) createStatement.close();
            if (insertStatement != null) insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO ADD LOGGER?!?
        }
    }
}
