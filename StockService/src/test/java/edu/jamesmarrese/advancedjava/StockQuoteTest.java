package edu.jamesmarrese.advancedjava;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockQuoteTest {

    @Test
    public void testGetStockSymbolShouldReturnTrue () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);
        StockQuote stockQuoteTwo = new StockQuote("AMZN", 100.25, date);

        assertTrue( "The two stock symbols are the same",
                stockQuote.getStockSymbol().equals(stockQuoteTwo.getStockSymbol()) );
    }

    @Test
    public void testGetStockSymbolShouldReturnFalse () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);
        StockQuote stockQuoteTwo = new StockQuote("APPL", 100.25, date);

        assertFalse( "The two stock symbols are different",
                stockQuote.getStockSymbol().equals(stockQuoteTwo.getStockSymbol()) );
    }

    @Test
    public void testGetStockPriceShouldReturnTrue () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);
        StockQuote stockQuoteTwo = new StockQuote("APPL", 100.25, date);

        double stockQuoteAmazon = stockQuote.getStockPrice();
        double stockQuoteApple  = stockQuoteTwo.getStockPrice();

        assertTrue( "The two stock prices are equal",
                stockQuoteAmazon == stockQuoteApple);
    }

    @Test
    public void testGetStockPriceShouldReturnFalse () {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);
        StockQuote stockQuoteTwo = new StockQuote("APPL", 223.75, date);

        double stockQuoteAmazon = stockQuote.getStockPrice();
        double stockQuoteApple  = stockQuoteTwo.getStockPrice();

        assertFalse( "The two stock prices are different",
                stockQuoteAmazon == stockQuoteApple);
    }

}
