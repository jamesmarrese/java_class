package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This TestStockServiceFactory class tests StockServiceFactory instances.
 * This class tests stockSymbol (String) and stockPrice (double).
 *
 * @author James Marrese
 */

public class TestStockServiceFactoryTwo {

    /**
     * Tests StockServiceFactory instances according to their symbols
     * and verifies that the correct symbols are returned.
     */

    @Test
    public void testStockServiceFactoryStockSymbolShouldReturnTrue () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
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
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
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
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
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
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        StockQuote stockQuote = new StockQuote("AMZN", 367.31, date);

        assertFalse("The stock prices are different",
                populatedStock.getStockPrice() == stockQuote.getStockPrice() ) ;
    }

    /**
     * Tests StockQuote instances to verify that the date
     * returned in not null.
     */

    @Test
    public void testGetStockDate () {

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, Calendar.getInstance().getTime());

        Date testDate = stockQuote.getDateRecorded();

        assertNotNull("The date object is not null", testDate);
    }

    @Test
    public void testGetListOfStockQuotesShouldReturnTrue () {

        String symbol = "APPL";

        Calendar startDate = new GregorianCalendar(2018,9,21);
        Calendar endDate = new GregorianCalendar(2018,9,26);

        int numberOfDays = 5;

        StockServiceFactory applicationTest = new StockServiceFactory();

        List<StockQuote> stockList = new ArrayList<>();

        stockList = applicationTest.getQuote("APPL", startDate, endDate);

        for (int i = 0; i < numberOfDays; ++i) {
            stockList.get(i);
        }

        assertTrue("The stock symbol should be APPL",
                stockList.get(1).getStockSymbol().equals("APPL"));

    }

}
