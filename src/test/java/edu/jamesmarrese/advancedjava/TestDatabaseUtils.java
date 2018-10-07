/*package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.util.DatabaseConnectionException;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

/*public class TestDatabaseUtils {

    @Test
    public void testDatabaseConnection () throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        assertNotNull("verify that we can get a connection ok",connection);
    }

    @Test
    public void testGetConnectionWorks() throws Exception{

        Connection connection = DatabaseUtils.getConnection();
        Statement statement = connection.createStatement();
        boolean execute = statement.execute("select * from quotes;");
        assertTrue("verify that we can execute a statement",execute);
    }

}*/