package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.IntervalEnum;
import edu.jamesmarrese.advancedjava.service.StockService;
import edu.jamesmarrese.advancedjava.service.StockServiceException;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.jar.Pack200.Packer.PASS;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

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
    public void testStockServiceFactoryStockSymbolShouldReturnTrue ()
                    throws StockServiceException {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("GOOG", date);

        StockQuote stockQuote = new StockQuote("GOOG", date, new BigDecimal(100));

        assertTrue("The stock symbols are the same",
                populatedStock.getStockSymbol().equals(stockQuote.getStockSymbol()) ) ;
    }

    /**
     * Tests StockServiceFactory instances according to their symbols
     * and verifies that the correct symbols are returned.
     */

    @Test
    public void testStockServiceFactoryStockSymbolShouldReturnFalse () throws StockServiceException {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("GOOG", date);

        StockQuote stockQuote = new StockQuote("AMZN", date, new BigDecimal(100.25));

        assertFalse("The stock symbols are different",
                populatedStock.getStockSymbol().equals(stockQuote.getStockSymbol()) ) ;
    }

    /**
     * Tests StockServiceFactory instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testStockServiceFactoryStockPriceShouldReturnTrue () throws StockServiceException{

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("GOOG", date);

        StockQuote stockQuote = new StockQuote("GOOG", date, new BigDecimal(85));

        assertTrue("The stock prices are the same",
                populatedStock.getStockPrice().equals(stockQuote.getStockPrice()) ) ;
    }

    /**
     * Tests StockServiceFactory instances according to their prices
     * and verifies that the correct prices are returned.
     */

    @Test
    public void testStockServiceFactoryStockPriceShouldReturnFalse () throws StockServiceException {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("GOOG", date);

        StockQuote stockQuote = new StockQuote("AMZN", date, new BigDecimal(367.31));

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

        StockQuote stockQuote = new StockQuote("AMZN", date, new BigDecimal(100.25));

        Date testDate = stockQuote.getDateRecorded();

        assertNotNull("The date object is not null", testDate);
    }


    /**
     * Tests that method getStockQuote returns a list of StockQuote objects
     * according to the interval specified by testing the number of days of
     * data that should be on the list.
     */

    @Test
    public void testGetListOfStockQuotesWithIntervalSpecifiedAsDailyShouldReturnTrue ()
            throws StockServiceException, ParseException {

        String symbol = "GOOG";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //Set the begin date for the list of stock quotes
        Date beginDate = format.parse("2018-09-21");
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(beginDate);

        //Set the end date for the list of stock quotes
        Date stopDate = format.parse("2018-09-27");
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(stopDate);

        IntervalEnum chosenInterval = IntervalEnum.DAILY;

        int numberOfQuotes = 13;

        StockServiceFactory applicationTest = new StockServiceFactory();

        List<StockQuote> stockList = applicationTest.getQuote(symbol, startDate, endDate, chosenInterval);

        for (int i = 0; i < numberOfQuotes; ++i) {
            stockList.get(i);
        }

        assertTrue("There should be 13 quotes returned from the database",
                stockList.size() == numberOfQuotes);
    }

    /**
     * Tests that method getStockQuote returns a list of StockQuote objects
     * according to the interval specified by testing the number of hours of
     * data that should be on the list.
     */

    @Test
    public void testGetListOfStockQuotesWithIntervalSpecifiedAsHourlyShouldReturnTrue ()
            throws StockServiceException, ParseException {

        String symbol = "GOOG";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //Set the begin date for the list of stock quotes
        Date beginDate = format.parse("2018-09-21");
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(beginDate);

        //Set the end date for the list of stock quotes
        Date stopDate = format.parse("2018-09-29");
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(stopDate);

        IntervalEnum chosenInterval = IntervalEnum.HOURLY;

        int numberOfQuotes = 14;

        StockServiceFactory applicationTest = new StockServiceFactory();

        List<StockQuote> stockList = applicationTest.getQuote(symbol, startDate, endDate, chosenInterval);

        for (int i = 0; i < numberOfQuotes; ++i) {
            stockList.get(i);
        }

        assertTrue("There should be 14 quotes returned from the database",
                stockList.size() == numberOfQuotes);
    }

}