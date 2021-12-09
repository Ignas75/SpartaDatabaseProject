package com.database.factories;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection = null;

    public static Connection getConnection() {
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

        } catch (SQLException e) {
            // TODO ADD LOGGER?!?
        }
        return connection;
    }
}
