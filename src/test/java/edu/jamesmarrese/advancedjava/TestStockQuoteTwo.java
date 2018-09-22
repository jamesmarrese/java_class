package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.BasicStockService;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This TestStockQuote class tests StockQuote instances. This
 * class tests stockSymbol (String) and stockPrice (double).
 *
 * @author James Marrese
 */

public class TestStockQuoteTwo {

    /**
     * Tests StockQuote instances according to their symbols
     * and verifies that the correct symbols are returned.
     */

    @Test
    public void testGetStockSymbolShouldReturnTrue () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);
        StockQuote stockQuoteTwo = new StockQuote("AMZN", 100.25, date);

        assertTrue( "The two stock symbols are the same",
                stockQuote.getStockSymbol().equals(stockQuoteTwo.getStockSymbol()) );
    }

    /**
     * Tests StockQuote instances according to their symbols
     * and verifies that the correct symbols are returned.
     */

    @Test
    public void testGetStockSymbolShouldReturnFalse () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);
        StockQuote stockQuoteTwo = new StockQuote("APPL", 100.25, date);

        assertFalse( "The two stock symbols are different",
                stockQuote.getStockSymbol().equals(stockQuoteTwo.getStockSymbol()) );
    }

    /**
     * Tests StockQuote instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testGetStockPriceShouldReturnTrue () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);
        StockQuote stockQuoteTwo = new StockQuote("APPL", 100.25, date);

        double stockQuoteAmazon = stockQuote.getStockPrice();
        double stockQuoteApple  = stockQuoteTwo.getStockPrice();

        assertTrue( "The stock prices are the same",
                stockQuoteAmazon == stockQuoteApple);
    }

    /**
     * Tests StockQuote instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testGetStockPriceShouldReturnFalse () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);
        StockQuote stockQuoteTwo = new StockQuote("APPL", 223.75, date);

        double stockQuoteAmazon = stockQuote.getStockPrice();
        double stockQuoteApple  = stockQuoteTwo.getStockPrice();

        assertFalse( "The stock prices are different",
                stockQuoteAmazon == stockQuoteApple);
    }

    @Test
    public void testGetStockDate () {

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, Calendar.getInstance().getTime());

        Date testDate = stockQuote.getDateRecorded();

        assertNotNull("The date object is not null", testDate);
    }

    @Test
    public void testGetStockObject () {

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, Calendar.getInstance().getTime());

        assertNotNull("The stock object is not null", stockQuote);
    }

    @Test
    public void testStockToString () {

        BasicStockService testStock = new BasicStockService();
        StockQuote populatedStock = testStock.getQuote("APPL");

        String stockString = populatedStock.toString();

        assertNotNull("The stock string should not be null", stockString);

    }

}