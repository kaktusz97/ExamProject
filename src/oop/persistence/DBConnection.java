package oop.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import oop.exceptions.PersistenceException;

public class DBConnection {

    private static Connection connection;

    private static String url;
    private static String username;
    private static String password;

    static {
        initProperties();
    }

    private DBConnection() {
    }

    private static void initProperties() {
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("config.properties.txt");
            props.load(fis);
            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
            fis.close();
        } catch (IOException ex) {
            throw new PersistenceException("Config file not found.");
        }
    }

    public static Connection getDatabaseConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                throw new PersistenceException("Connecting to database failed");
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }
}
