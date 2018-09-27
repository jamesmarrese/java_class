package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.IntervalEnum;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This TestStockServiceFactory class tests StockServiceFactory instances.
 * This class tests stockSymbol (String) and stockPrice (BigDecimal).
 *
 * @author James Marrese
 */

public class TestStockServiceFactory {

    /**
     * Tests StockServiceFactory instances according to their symbols
     * and verifies that the correct symbols are returned.
     * The date returned by the getQuote method, which is a Calendar object,
     * is also tested for @notNull.
     */

    @Test
    public void testStockServiceFactoryStockSymbolShouldReturnTrue () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL", date);

        StockQuote stockQuote = new StockQuote("APPL", new BigDecimal(100.25), date);

        assertTrue("The stock symbols are the same",
                populatedStock.getStockSymbol().equals(stockQuote.getStockSymbol()) ) ;
    }

    /**
     * Tests StockServiceFactory instances according to their symbols
     * and verifies that the correct symbols are returned.
     */

    @Test
    public void testStockServiceFactoryStockSymbolShouldReturnFalse () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL", date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(100.25), date);

        assertFalse("The stock symbols are different",
                populatedStock.getStockSymbol().equals(stockQuote.getStockSymbol()) ) ;
    }

    /**
     * Tests StockServiceFactory instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testStockServiceFactoryStockPriceShouldReturnTrue () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL", date);

        StockQuote stockQuote = new StockQuote("APPL", new BigDecimal(100.25), date);

        assertTrue("The stock prices are the same",
                populatedStock.getStockPrice().equals(stockQuote.getStockPrice()) ) ;
    }

    /**
     * Tests StockServiceFactory instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testStockServiceFactoryStockPriceShouldReturnFalse () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL", date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(367.31), date);

        assertFalse("The stock prices are different",
                populatedStock.getStockPrice().equals(stockQuote.getStockPrice()) ) ;
    }

    /**
     * Tests StockQuote instances to verify that the date
     * returned in not null.
     */

    @Test
    public void testGetStockDate () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(100.25), date);

        Date testDate = stockQuote.getDateRecorded();

        assertNotNull("The date object is not null", testDate);
    }

    /**
     * Tests that method getStockQuote returns a list of StockQuote objects
     * by testing the symbols returned by the list.
     */

    @Test
    public void testGetListOfStockQuotesShouldReturnTrue () {

        String symbol = "APPL";

        Calendar startDate = new GregorianCalendar(2018,9,21);
        Calendar endDate = new GregorianCalendar(2018,9,26);

        int numberOfDays = 5;

        StockServiceFactory applicationTest = new StockServiceFactory();

        List<StockQuote> stockList = new ArrayList<>();

        stockList = applicationTest.getQuote(symbol, startDate, endDate);

        for (int i = 0; i < numberOfDays; ++i) {
            stockList.get(i);
        }

        assertTrue("The stock symbol should be APPL",
                stockList.get(1).getStockSymbol().equals("APPL"));

    }

    /**
     * Tests that method getStockQuote returns a list of StockQuote objects
     * according to the interval specified by testing the symbols returned
     * by the list.
     */

    @Test
    public void testGetListOfStockQuotesWithIntervalSpecifiedShouldReturnTrue () {

        String symbol = "APPL";

        Calendar startDate = new GregorianCalendar(2018,9,21);
        Calendar endDate = new GregorianCalendar(2018,9,26);

        IntervalEnum chosenInterval = IntervalEnum.DAILY;

        int numberOfDays = 6;

        StockServiceFactory applicationTest = new StockServiceFactory();

        List<StockQuote> stockList = new ArrayList<>();

        stockList = applicationTest.getQuote(symbol, startDate, endDate, chosenInterval);

        for (int i = 0; i < numberOfDays; ++i) {
            stockList.get(i);
        }

        assertTrue("There should be 6 days of stock data",
                stockList.size() == 6);
    }

}
