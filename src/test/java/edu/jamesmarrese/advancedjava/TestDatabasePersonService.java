package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.util.DatabaseConnectionException;
import edu.jamesmarrese.advancedjava.util.DatabaseInitializationException;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.assertTrue;

/**
 * Tests methods in the DatabasePersonService class
 */
public class TestDatabasePersonService {

    @Test
    public void testGetListOfPersons () throws DatabaseConnectionException, java.sql.SQLException,
            DatabaseInitializationException {

        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);

        Connection connection = DatabaseUtils.getConnection();
        Statement statement = connection.createStatement();
        boolean execute = statement.execute("select * from person");
        assertTrue("verify that we can execute a statement",execute);
    }

    @Test
    public void test() throws DatabaseConnectionException, java.sql.SQLException,
    DatabaseInitializationException {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
        testGetListOfPersons();
    }

}