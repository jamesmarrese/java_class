package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.BasicStockService;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This TestStockQuote class tests StockQuote instances. This
 * class tests stockSymbol (String) and stockPrice (BigDecimal).
 * The date returned by the getQuote method, which is a Calendar object,
 * is also tested for @notNull.
 *
 * @author James Marrese
 */

public class TestStockQuote {

    /**
     * Tests StockQuote instances according to their symbols
     * and verifies that the correct symbols are returned.
     */

    @Test
    public void testGetStockSymbolShouldReturnTrue () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.setTime(date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(100.25), testCalendar);
        StockQuote stockQuoteTwo = new StockQuote("AMZN", new BigDecimal(100.25), testCalendar);

        assertTrue( "The two stock symbols are the same",
                stockQuote.getStockSymbol().equals(stockQuoteTwo.getStockSymbol()) );
    }

    /**
     * Tests StockQuote instances according to their symbols
     * and verifies that the correct symbols are returned.
     */

    @Test
    public void testGetStockSymbolShouldReturnFalse () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.setTime(date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(100.25), testCalendar);
        StockQuote stockQuoteTwo = new StockQuote("APPL", new BigDecimal(100.25), testCalendar);

        assertFalse( "The two stock symbols are different",
                stockQuote.getStockSymbol().equals(stockQuoteTwo.getStockSymbol()) );
    }

    /**
     * Tests StockQuote instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testGetStockPriceShouldReturnTrue () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.setTime(date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(100.25), testCalendar);
        StockQuote stockQuoteTwo = new StockQuote("APPL", new BigDecimal(100.25), testCalendar);

        BigDecimal stockQuoteAmazon = stockQuote.getStockPrice();
        BigDecimal stockQuoteApple  = stockQuoteTwo.getStockPrice();

        assertTrue( "The stock prices are the same",
                stockQuoteAmazon.equals(stockQuoteApple) );
    }

    /**
     * Tests StockQuote instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testGetStockPriceShouldReturnFalse () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.setTime(date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(100.25), testCalendar);
        StockQuote stockQuoteTwo = new StockQuote("APPL", new BigDecimal(223.75), testCalendar);

        BigDecimal stockQuoteAmazon = stockQuote.getStockPrice();
        BigDecimal stockQuoteApple  = stockQuoteTwo.getStockPrice();

        assertFalse( "The stock prices are different",
                stockQuoteAmazon.equals(stockQuoteApple) );
    }

    /**
     * Tests StockQuote instances to verify that the date
     * returned is not null.
     */

    @Test
    public void testGetStockDate () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.setTime(date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(100.25), testCalendar);

        Calendar testDate = stockQuote.getDateRecorded();

        assertNotNull("The date object is not null", testDate);
    }

    /**
     * Tests StockQuote instances to verify that the object
     * returned is not null.
     */

    @Test
    public void testGetStockObject () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.setTime(date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(100.25), testCalendar);

        assertNotNull("The stock object is not null", stockQuote);
    }

    /**
     * Test that a returned StockQuote object is not null.
     */

    @Test
    public void testStockToString () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.setTime(date);

        BasicStockService testStock = new BasicStockService();

        StockQuote populatedStock = testStock.getQuote("APPL", testCalendar);

        String stockString = populatedStock.toString();

        assertNotNull("The stock string should not be null", stockString);

    }

}
