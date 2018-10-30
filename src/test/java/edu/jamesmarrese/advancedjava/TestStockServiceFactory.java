package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.IntervalEnum;
import edu.jamesmarrese.advancedjava.service.StockServiceException;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;
import edu.jamesmarrese.advancedjava.ui.StockQuoteApplication;
import edu.jamesmarrese.advancedjava.util.DatabaseInitializationException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TestStockServiceFactory {

    private String testSymbol = "AMZN";
    private BigDecimal testPrice = new BigDecimal(123.45);

    //Date is 2018 October 23 - note that months start at 0, so Jan. is month 0, Feb. is month 1, etc.
    private Calendar startCalendar = new GregorianCalendar(2018, 9, 23);

    //Date is 2018 October 26
    private Calendar endCalendar = new GregorianCalendar(2018, 11, 26);

    //Date is 2017 September 22 - note that months start at 0, so Jan. is month 0, Feb. is month 1, etc.
    private Calendar testCal = new GregorianCalendar(2017, 8, 22);

    /**
     * Tests for getQuote method with symbol and date parameters only
     *
     * @throws StockServiceException
     */
    @Test
    public void testYahooFinanceStockServiceFactoryGetSymbol() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        StockQuote stockTest = stockQuote.getQuote("AMZN", startCalendar);

        assertTrue("The stock symbols match", stockTest.getStockSymbol().equals(testSymbol));

    }

    @Test
    public void testYahooFinanceStockServiceFactoryGetDate() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        StockQuote stockTest = stockQuote.getQuote("AMZN", startCalendar);

        assertFalse("The stock dates do not match", stockTest.getDateRecorded().equals(testCal));

    }

    @Test
    public void testYahooFinanceStockServiceFactoryGetPrice() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        StockQuote stockTest = stockQuote.getQuote("AMZN", startCalendar);

        assertFalse("The stock prices do not match", stockTest.getStockPrice().equals(testPrice));
    }



    /**
     * Tests for getQuote method with symbol, start, and stop dates (interval is default DAILY)
     *
     * @throws StockServiceException
     */
    @Test
    public void testListOfStockQuotesGetSymbol() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar);

        String retrievedSymbol = listOfQuotes.get(1).getStockSymbol();

        assertTrue("The stock symbols match", retrievedSymbol.equals(testSymbol));

    }

    @Test
    public void testListOfStockQuotesGetPrice() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar);

        BigDecimal retrievedPrice = listOfQuotes.get(1).getStockPrice();

        assertFalse("The stock prices do not match", retrievedPrice.equals(testPrice));
    }

    @Test
    public void testListOfStockQuotesGetDate() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar);

        Calendar retrievedDate = listOfQuotes.get(1).getDateRecorded();

        assertFalse("The stock dates do not match", retrievedDate.equals(testCal));
    }

    /**
     * Tests for getQuote method with interval
     *
     * @throws StockServiceException
     */
    @Test
    public void testListOfStockQuotesWithIntervalGetSymbol() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar, IntervalEnum.DAILY);

        String retrievedSymbol = listOfQuotes.get(1).getStockSymbol();

        assertTrue("The stock symbols match", retrievedSymbol.equals(testSymbol));

    }

    @Test
    public void testListOfStockQuotesWithIntervalGetDate() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar, IntervalEnum.DAILY);

        Calendar retrievedDate = listOfQuotes.get(1).getDateRecorded();

        assertFalse("The Interval enum DAILY works", retrievedDate.equals(testCal));

    }

    @Test
    public void testListOfStockQuotesWithIntervalHourly() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar, IntervalEnum.HOURLY);

        Calendar retrievedDate = listOfQuotes.get(1).getDateRecorded();

        assertFalse("The Interval enum HOURLY works", retrievedDate.equals(testCal));

    }

    @Test
    public void testListOfStockQuotesWithIntervalMonthly() throws Exception {

        StockServiceFactory stockQuote = new StockServiceFactory();
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar, IntervalEnum.WEEKLY);

        Calendar retrievedDate = listOfQuotes.get(1).getDateRecorded();

        assertFalse("The Interval enum WEEKLY works", retrievedDate.equals(testCal));

    }

    @Test
    public void testMainMethodShouldReturnValidResult () throws ParseException,
            DatabaseInitializationException, IOException, StockServiceException {

        String[] argString = new String [4];
        argString[0] = "AMZN";
        argString[1] = "2018-09-17";
        argString[2] = "2018-09-20";
        argString[3] = "DAILY";

        StockQuoteApplication.main(argString);
    }

}
