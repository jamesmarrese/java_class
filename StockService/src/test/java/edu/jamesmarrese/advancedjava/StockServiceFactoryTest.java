package edu.jamesmarrese.advancedjava;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockServiceFactoryTest {

    @Test
    public void testStockServiceFactoryShouldReturnTrue () {
        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        StockQuote stockQuote = new StockQuote("Apple", 100.25, date);

        assertTrue("The stocks are the same",
                populatedStock.getStockSymbol().equals(stockQuote.getStockSymbol()) ) ;
    }

    @Test
    public void testStockServiceFactoryShouldReturnFalse () {
        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        StockQuote stockQuote = new StockQuote("Amazon", 100.25, date);

        assertFalse("The stocks are different",
                populatedStock.getStockSymbol().equals(stockQuote.getStockSymbol()) ) ;
    }

}
