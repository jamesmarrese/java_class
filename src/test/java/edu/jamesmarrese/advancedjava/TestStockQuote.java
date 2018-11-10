package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This TestStockQuote class tests StockQuote instances.
 *
 * @author James Marrese
 */

public class TestStockQuote {

    private String goodSymbol = "GOOG";
    private BigDecimal goodPrice = new BigDecimal(100);
    private Calendar goodDate = new GregorianCalendar(2018, 9, 23);

    private String badSymbol = "AMZN";
    private BigDecimal badPrice = new BigDecimal(500);
    private Calendar badDate = new GregorianCalendar(2018, 10, 15);

    StockQuote stockQuote = new StockQuote(goodSymbol, goodDate, goodPrice);
    StockQuote stockQuoteGoodTest = new StockQuote(goodSymbol, goodDate, goodPrice);
    StockQuote stockQuoteBadTest = new StockQuote(badSymbol, badDate, badPrice);

    @Test
    public void testGetStockSymbolShouldReturnTrue () {
        assertTrue( "The two stock symbols are the same",
                stockQuote.getStockSymbol().equals(stockQuoteGoodTest.getStockSymbol()) );
    }

    @Test
    public void testGetStockSymbolShouldReturnFalse () {
        assertFalse( "The two stock symbols are different",
                stockQuote.getStockSymbol().equals(stockQuoteBadTest.getStockSymbol()) );
    }

    @Test
    public void testGetStockPriceShouldReturnTrue () {
        assertTrue( "The stock prices are the same",
                stockQuote.getStockPrice().equals(stockQuoteGoodTest.getStockPrice()) );
    }

    @Test
    public void testGetStockPriceShouldReturnFalse () {
        assertFalse( "The stock prices are different",
                stockQuote.getStockPrice().equals(stockQuoteBadTest.getStockPrice()));
    }

    @Test
    public void testGetStockDateShouldReturnTrue () {
        assertTrue("The dates are the same",
                stockQuote.getDateRecorded().equals(stockQuoteGoodTest.getDateRecorded()));
    }

    @Test
    public void testGetStockDateShouldReturnFalse () {
        assertFalse("The dates are different",
                stockQuote.getDateRecorded().equals(stockQuoteBadTest.getDateRecorded()));
    }

    @Test
    public void testGetStockObject () {
        assertNotNull("The stock object is not null", stockQuote);
    }

    @Test
    public void testStockQuoteToString() {
        assertTrue("The toString method works", stockQuote.toString().equals(stockQuote.toString()));
    }

}