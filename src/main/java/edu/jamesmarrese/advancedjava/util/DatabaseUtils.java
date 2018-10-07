package edu.jamesmarrese.advancedjava.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class that contains database related utility methods.
 */

public class DatabaseUtils {

    // JDBC driver name and database UR
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/stocks?autoReconnect=true&useSSL=false";

    // Database credentials
    private static final String USER = "bob";
    private static final String PASS = "some_pass";

    public static Connection getConnection() throws DatabaseConnectionException {

        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException  |  SQLException e){
            throw new DatabaseConnectionException("Could not connect to database." + e.getMessage(), e);
        }

        return connection;
    }

}
