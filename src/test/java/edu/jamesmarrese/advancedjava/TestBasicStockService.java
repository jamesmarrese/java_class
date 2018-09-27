package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.BasicStockService;
import edu.jamesmarrese.advancedjava.service.IntervalEnum;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This TestBasicStockService class tests BasicStockService instances.
 * This class tests stockSymbol (String) and stockPrice (BigDecimal). Both
 * variables are hard-coded in the BasicStockService class (APPL 100.25).
 * The date returned by the getQuote method, which is a Calendar object,
 * is also tested for @notNull.
 *
 * @author James Marrese
 */

public class TestBasicStockService {

    /**
     * Tests BasicStockService instances according to their symbols
     * and verifies that the correct symbols are returned. Test verifies
     * as true even though different symbols are used in the two method calls.
     * This is because the StockQuote instance returned by the BasicStockService
     * class is hard-coded, no matter the symbol.
     */

    @Test
    public void testBasicStockServiceStockSymbolShouldReturnTrue () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote newStock = new BasicStockService().getQuote("A", date);

        StockQuote anotherStock = new BasicStockService().getQuote("B", date);

        assertTrue("The stock symbols are the same",
                newStock.getStockSymbol().equals(anotherStock.getStockSymbol()) );
    }

    /**
     * Tests BasicStockService instances according to their symbols
     */

    @Test
    public void testBasicStockServiceStockSymbolShouldReturnFalse () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote newStock = new BasicStockService().getQuote("A", date);

        StockQuote fakeStock = new StockQuote("AMZN", new BigDecimal(45.44), date);

        assertFalse("The stocks symbols are different",
                newStock.getStockSymbol() == fakeStock.getStockSymbol() );
    }

    /**
     * Tests BasicStockService instances according to their prices
     * and verifies that the correct prices are returned. The StockQuote
     * instance returned by the BasicStockService class is hard-coded, which
     * includes the stock price.
     */

    @Test
    public void testBasicStockServiceStockPriceShouldReturnTrue () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote newStock = new BasicStockService().getQuote("A", date);

        StockQuote anotherStock = new BasicStockService().getQuote("B", date);

        assertTrue("The stock prices are the same",
                newStock.getStockPrice().equals(anotherStock.getStockPrice()) );
    }

    /**
     * Tests BasicStockService instances according to their prices
     * and verifies that the correct prices are returned. The StockQuote
     * instance returned by the BasicStockService class is hard-coded, which
     * includes the stock price.
     */
    @Test
    public void testBasicStockServiceStockPriceShouldReturnFalse () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote newStock = new BasicStockService().getQuote("A", date);

        StockQuote fakeStock = new StockQuote("AMZN", new BigDecimal(45.44), date);

        assertFalse("The stock prices are different",
                newStock.getStockPrice().equals(fakeStock.getStockPrice()) );
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

        BasicStockService applicationTest = new BasicStockService();

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

        BasicStockService applicationTest = new BasicStockService();

        List<StockQuote> stockList = new ArrayList<>();

        stockList = applicationTest.getQuote(symbol, startDate, endDate, chosenInterval);

        for (int i = 0; i < numberOfDays - 1; ++i) {
            stockList.get(i);
        }

        assertTrue("There should be 6 days of stock data",
                stockList.size() == 6);
    }

}
