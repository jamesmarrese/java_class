package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    /*@Test
    public void testGetStockSymbolShouldReturnTrue () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("GOOG", date, new BigDecimal(100.25));
        StockQuote stockQuoteTwo = new StockQuote("GOOG", date, new BigDecimal(100.25));

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

        StockQuote stockQuote = new StockQuote("GOOG", date, new BigDecimal(100.25));
        StockQuote stockQuoteTwo = new StockQuote("APPL", date, new BigDecimal(100.25));

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

        StockQuote stockQuote = new StockQuote("GOOG", date, new BigDecimal(100.25));
        StockQuote stockQuoteTwo = new StockQuote("GOOG", date, new BigDecimal(100.25));

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

        StockQuote stockQuote = new StockQuote("GOOG", date, new BigDecimal(100.25));
        StockQuote stockQuoteTwo = new StockQuote("GOOG", date, new BigDecimal(223.75));

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

        StockQuote stockQuote = new StockQuote("GOOG", date, new BigDecimal(100.25));

        Date testDate = stockQuote.getDateRecorded();

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

        StockQuote stockQuote = new StockQuote("GOOG", date, new BigDecimal(100.25));

        assertNotNull("The stock object is not null", stockQuote);
    }

}