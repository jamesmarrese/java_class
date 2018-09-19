package edu.jamesmarrese.advancedjava;

import org.junit.Test;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * This TestStockService class creates StockQuote objects that are created
 * from using the StockService interface. This test class uses mocks to test
 * the stockSymbol (String) and stockPrice (double) fields.
 *
 * @author  James Marrese
 */

public class TestStockService {

    /**
     * Tests StockQuote instances using mocking to verify that the
     * correct stock symbols are returned.
     */

    @Test
    public void testStockServiceSymbolMockShouldReturnTrue () {

        StockService stockServiceMock = Mockito.mock(StockService.class);

        String mockStockSymbol = "APPL";

        double mockStockPrice = 100.25;

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        when(stockServiceMock.getQuote(any(String.class))).thenReturn(new StockQuote(mockStockSymbol, mockStockPrice, date));

        StockQuote testStock = stockServiceMock.getQuote("Z");

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        assertTrue("The stock symbols are the same",
                testStock.getStockSymbol().equals(populatedStock.getStockSymbol()) );
    }

    /**
     * Tests StockQuote instances using mocking to verify that the
     * correct stock symbols are returned.
     */

    @Test
    public void testStockServiceSymbolMockShouldReturnFalse () {

        StockService stockServiceMock = Mockito.mock(StockService.class);

        String mockStockSymbol = "NFLX";

        double mockStockPrice = 100.25;

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        when(stockServiceMock.getQuote(any(String.class))).thenReturn(new StockQuote(mockStockSymbol, mockStockPrice, date));

        StockQuote testStock = stockServiceMock.getQuote("Z");

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        assertFalse("The stock symbols are different",
                testStock.getStockSymbol().equals(populatedStock.getStockSymbol()) );
    }

    /**
     * Tests StockQuote instances using mocking to verify that the
     * correct stock prices are returned.
     */

    @Test
    public void testStockServiceStockPriceMockShouldReturnTrue () {

        StockService stockServiceMock = Mockito.mock(StockService.class);

        String mockStockSymbol = "Apple";

        double mockStockPrice = 100.25;

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        when(stockServiceMock.getQuote(any(String.class))).thenReturn(new StockQuote(mockStockSymbol, mockStockPrice, date));

        StockQuote testStock = stockServiceMock.getQuote("Z");

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        assertTrue("The stock prices are the same",
                testStock.getStockPrice() == populatedStock.getStockPrice() );
    }

    /**
     * Tests StockQuote instances using mocking to verify that the
     * correct stock prices are returned.
     */

   @Test
    public void testStockServiceStockPriceMockShouldReturnFalse () {

        StockService stockServiceMock = Mockito.mock(StockService.class);

        String mockStockSymbol = "TestSymbol";

        double mockStockPrice = 100.25;

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        when(stockServiceMock.getQuote(any(String.class))).thenReturn(new StockQuote(mockStockSymbol, mockStockPrice, date));

        StockQuote testStock = stockServiceMock.getQuote("Z");

        StockServiceFactory fakeStock = new StockServiceFactory();
        StockQuote populatedStock = fakeStock.getQuote("APPL");

        assertFalse("The stock prices are different",
                testStock.getStockSymbol() == populatedStock.getStockSymbol() );
    }

}
