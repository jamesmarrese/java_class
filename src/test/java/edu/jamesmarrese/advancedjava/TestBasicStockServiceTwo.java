package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.BasicStockService;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This TestBasicStockService class tests BasicStockService instances.
 * This class tests stockSymbol (String) and stockPrice (double). Both
 * variables are hard-coded in the BasicStockService class (APPL 100.25).
 *
 * @author James Marrese
 */

public class TestBasicStockServiceTwo {

    /**
     * Tests BasicStockService instances according to their symbols
     * and verifies that the correct symbols are returned. Test verifies
     * as true even though different symbols are used in the two method calls.
     * This is because the StockQuote instance returned by the BasicStockService
     * class is hard-coded, no matter the symbol.
     */

    @Test
    public void testBasicStockServiceStockSymbolShouldReturnTrue () {

        StockQuote newStock = new BasicStockService().getQuote("A");

        StockQuote anotherStock = new BasicStockService().getQuote("B");

        assertTrue("The stock symbols are the same",
                newStock.getStockSymbol().equals(anotherStock.getStockSymbol()) );
    }

    /**
     * Tests BasicStockService instances according to their symbols
     */

    @Test
    public void testBasicStockServiceStockSymbolShouldReturnFalse () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote newStock = new BasicStockService().getQuote("A");

        StockQuote fakeStock = new StockQuote("AMZN", 45.44, date);

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

        StockQuote newStock = new BasicStockService().getQuote("A");

        StockQuote anotherStock = new BasicStockService().getQuote("B");

        assertTrue("The stock prices are the same",
                newStock.getStockPrice() == anotherStock.getStockPrice() );
    }

    /**
     * Tests BasicStockService instances according to their prices
     * and verifies that the correct prices are returned. The StockQuote
     * instance returned by the BasicStockService class is hard-coded, which
     * includes the stock price.
     */
    @Test
    public void testBasicStockServiceStockPriceShouldReturnFalse () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote newStock = new BasicStockService().getQuote("A");

        StockQuote fakeStock = new StockQuote("AMZN", 45.44, date);

        assertFalse("The stock prices are different",
                newStock.getStockPrice() == fakeStock.getStockPrice() );
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

        BasicStockService applicationTest = new BasicStockService();

        List<StockQuote> stockList = new ArrayList<>();

        stockList = applicationTest.getQuote("APPL", startDate, endDate);

        for (int i = 0; i < numberOfDays; ++i) {
            stockList.get(i);
        }

        assertTrue("The stock symbol should be APPL",
                stockList.get(1).getStockSymbol().equals("APPL"));
    }

}
