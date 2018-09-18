package edu.jamesmarrese.advancedjava;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This StockServiceFactoryTest class tests StockServiceFactory instances.
 * This class tests stockSymbol (String) and stockPrice (double).
 *
 * @author James Marrese
 */

public class StockServiceFactoryTest {

    /**
     * Tests StockServiceFactory instances according to their symbols
     * and verifies that the correct symbols are returned.
     */

    @Test
    public void testStockServiceFactoryStockSymbolShouldReturnTrue () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        StockQuote stockQuote = new StockQuote("APPL", 100.25, date);

        assertTrue("The stock symbols are the same",
                populatedStock.getStockSymbol().equals(stockQuote.getStockSymbol()) ) ;
    }

    /**
     * Tests StockServiceFactory instances according to their symbols
     * and verifies that the correct symbols are returned.
     */

    @Test
    public void testStockServiceFactoryStockSymbolShouldReturnFalse () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);

        assertFalse("The stock symbols are different",
                populatedStock.getStockSymbol().equals(stockQuote.getStockSymbol()) ) ;
    }

    /**
     * Tests StockServiceFactory instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testStockServiceFactoryStockPriceShouldReturnTrue () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        StockQuote stockQuote = new StockQuote("APPL", 100.25, date);

        assertTrue("The stock prices are the same",
                populatedStock.getStockPrice() == stockQuote.getStockPrice() ) ;
    }

    /**
     * Tests StockServiceFactory instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testStockServiceFactoryStockPriceShouldReturnFalse () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        StockQuote stockQuote = new StockQuote("AMZN", 367.31, date);

        assertFalse("The stock prices are different",
                populatedStock.getStockPrice() == stockQuote.getStockPrice() ) ;
    }

}
