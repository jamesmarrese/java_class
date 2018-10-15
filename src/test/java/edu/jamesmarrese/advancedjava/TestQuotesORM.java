package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.Quotes;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for the Quotes ORM class
 */
public class TestQuotesORM {

    public static final BigDecimal price = new BigDecimal(100.25);
    public static final String symbol = "APPL";
    static Timestamp time = new Timestamp(System.currentTimeMillis());

    /**
     * Testing helper method for generating Quote test data
     *
     * @return a Quote object that uses static constants for data.
     */
    public static Quotes createQuotes () {
        Quotes quotes = new Quotes();
        quotes.setPrice(price);
        quotes.setSymbol(symbol);
        quotes.setTime(time);
        return quotes;
    }

    @Test
    public void testQuotesGettersAndSetters() {
        Quotes quotes = createQuotes();
        int id = 10;
        quotes.setID(id);
        assertEquals("price matches", price, quotes.getPrice());
        assertEquals("symbol matches", symbol, quotes.getSymbol());
        assertEquals("time matches", time, quotes.getTime());
        assertEquals("id matches", id, quotes.getID());

    }


}
