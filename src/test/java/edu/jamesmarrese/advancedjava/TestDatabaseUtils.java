package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.util.DatabaseInitializationException;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This TestDatabaseUtils class tests that the
 * DatabaseUtils class can get connections and
 * query statements.
 *
 * @author James Marrese
 */

public class TestDatabaseUtils {

    /*@Test
    public void testGoodInitFile() throws Exception {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }*/

    @Test(expected = DatabaseInitializationException.class)
    public void testBadInitFile() throws Exception {
        DatabaseUtils.initializeDatabase("bogus");
    }

    /*@Test
    public void testGetConnection() throws Exception{
        Connection connection = DatabaseUtils.getConnection();
        assertNotNull("verify that we can get a connection ok",connection);
        connection.close();
    }*/

    /*@Test
    public void testGetConnectionWorks() throws Exception{
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
        Connection connection = DatabaseUtils.getConnection();
        Statement statement = connection.createStatement();
        boolean execute = statement.execute("select * from person");
        assertTrue("verify that we can execute a statement",execute);
        connection.close();
    }*/

}