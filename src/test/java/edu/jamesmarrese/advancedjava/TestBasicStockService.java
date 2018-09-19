package edu.jamesmarrese.advancedjava;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This TestBasicStockService class tests BasicStockService instances.
 * This class tests stockSymbol (String) and stockPrice (double). Both
 * variables are hard-coded in the BasicStockService class (APPL 100.25).
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

        StockQuote newStock = new BasicStockService().getQuote("A");

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

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

        StockQuote newStock = new BasicStockService().getQuote("A");

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote fakeStock = new StockQuote("AMZN", 45.44, date);

        assertFalse("The stock prices are different",
                newStock.getStockPrice() == fakeStock.getStockPrice() );
    }

}
