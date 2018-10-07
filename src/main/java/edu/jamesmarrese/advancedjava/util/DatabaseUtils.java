package edu.jamesmarrese.advancedjava.util;

import com.ibatis.common.jdbc.ScriptRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    /**
     * A utility method that runs a db initialize script.
     * @param initializationScript    full path to the script to run to create the schema
     * @throws DatabaseInitializationException
     */

    public static void initializeDatabase (String initializationScript) throws DatabaseInitializationException {

        Connection connection = null;
        try {
            connection = getConnection();
            ScriptRunner runner = new ScriptRunner(connection, false, false);
            InputStream inputStream = new FileInputStream(initializationScript);

            InputStreamReader reader = new InputStreamReader(inputStream);

            runner.runScript(reader);
            reader.close();
            connection.commit();
            connection.close();
        } catch (DatabaseConnectionException | SQLException | IOException e) {
            throw new DatabaseInitializationException("Could not initialize database because of " +
                    e.getMessage(), e);
        }

    }

}
