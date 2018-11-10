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

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Tests for the StockServiceFactory class
 */
public class TestStockServiceFactory {

    private String testSymbol = "GOOG";
    private BigDecimal testPrice = new BigDecimal(123.45);

    //Date is 2018 September 21
    private Calendar startCalendar = new GregorianCalendar(2018, 8, 21);

    //Date is 2018 September 23
    private Calendar calendarSeptTwoThree = new GregorianCalendar(2018, 8, 23);

    //Date is 2018 September 27
    private Calendar testCal = new GregorianCalendar(2018, 8, 27);

    //Date is 2018 October 2
    private Calendar endCalendar = new GregorianCalendar(2018, 9, 2);

    StockServiceFactory stockQuote = new StockServiceFactory();

    /**
     * Tests for getQuote method with symbol and date parameters only
     *
     * @throws StockServiceException
     */
    @Test
    public void testYahooFinanceStockServiceFactoryGetSymbol() throws Exception {
        StockQuote stockTest = stockQuote.getQuote(testSymbol, startCalendar);
        assertTrue("The stock symbols match", stockTest.getStockSymbol().equals(testSymbol));
    }

    @Test
    public void testYahooFinanceStockServiceFactoryGetDate() throws Exception {
        StockQuote stockTest = stockQuote.getQuote(testSymbol, startCalendar);
        assertFalse("The stock dates do not match", stockTest.getDateRecorded().equals(testCal));
    }

    @Test
    public void testYahooFinanceStockServiceFactoryGetPrice() throws Exception {
        StockQuote stockTest = stockQuote.getQuote(testSymbol, startCalendar);
        assertFalse("The stock prices do not match", stockTest.getStockPrice().equals(testPrice));
    }

    /**
     * Tests for getQuote method with symbol, start, and stop dates (interval is default DAILY)
     *
     * @throws StockServiceException
     */
    @Test
    public void testListOfStockQuotesGetSymbol() throws Exception {
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar);
        String retrievedSymbol = listOfQuotes.get(1).getStockSymbol();
        assertTrue("The stock symbols match", retrievedSymbol.equals(testSymbol));
    }

    @Test
    public void testListOfStockQuotesGetPrice() throws Exception {
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar);
        BigDecimal retrievedPrice = listOfQuotes.get(1).getStockPrice();
        assertFalse("The stock prices do not match", retrievedPrice.equals(testPrice));
    }

    @Test
    public void testListOfStockQuotesGetDate() throws Exception {
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
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar, IntervalEnum.DAILY);
        String retrievedSymbol = listOfQuotes.get(1).getStockSymbol();
        assertTrue("The stock symbols match", retrievedSymbol.equals(testSymbol));

    }

    @Test
    public void testListOfStockQuotesWithIntervalGetDate() throws Exception {
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar, IntervalEnum.DAILY);
        Calendar retrievedDate = listOfQuotes.get(1).getDateRecorded();
        assertFalse("The Interval enum DAILY works", retrievedDate.equals(testCal));
    }

    @Test
    public void testListOfStockQuotesWithIntervalHourly() throws Exception {
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar, IntervalEnum.HOURLY);
        Calendar retrievedDate = listOfQuotes.get(1).getDateRecorded();
        assertFalse("The Interval enum HOURLY works", retrievedDate.equals(testCal));

    }

    @Test
    public void testListOfStockQuotesWithIntervalMonthly() throws Exception {
        List<StockQuote> listOfQuotes = stockQuote.getQuote(testSymbol, startCalendar, endCalendar, IntervalEnum.WEEKLY);
        Calendar retrievedDate = listOfQuotes.get(1).getDateRecorded();
        assertFalse("The Interval enum WEEKLY works", retrievedDate.equals(testCal));
    }

    @Test
    public void testMainMethodShouldReturnValidResult () throws ParseException,
            DatabaseInitializationException, StockServiceException {

        String[] argString = new String [5];
        argString[0] = "AMZN";
        argString[1] = "2018-09-17";
        argString[2] = "2018-09-20";
        argString[3] = "DAILY";
        argString[4] = "YAHOO";

        StockQuoteApplication.main(argString);
    }

    @Test(expected = AssertionError.class)
    public void testMainServiceTypeAssertionError() throws ParseException, StockServiceException, DatabaseInitializationException {
        String[] argString = new String [5];

        argString[0] = "AMZN";
        argString[1] = "2018-09-17";
        argString[2] = "2018-09-20";
        argString[3] = "DAILY";
        argString[4] = "BOGUS"; //bad service type

        StockQuoteApplication.main(argString);
    }

    @Test(expected = AssertionError.class)
    public void testMainStartDateAndEndDateAssertionError() throws ParseException, StockServiceException, DatabaseInitializationException {
        String[] argString = new String [5];

        argString[0] = "AMZN";
        argString[1] = "2018-09-23"; //start date is before end date
        argString[2] = "2018-09-20";
        argString[3] = "DAILY";
        argString[4] = "YAHOO";

        StockQuoteApplication.main(argString);
    }

    @Test(expected = NullPointerException.class)
    public void testMainNegative() throws ParseException, StockServiceException, DatabaseInitializationException {
        StockQuoteApplication.main(null);
    }

    @Test
    public void testGetDatabaseQuote() throws StockServiceException, DatabaseInitializationException {

        BigDecimal price = new BigDecimal(85);

        StockQuote quote = stockQuote.getDatabaseQuote(testSymbol, calendarSeptTwoThree);

        assertTrue("The symbols are the same", testSymbol.equals(quote.getStockSymbol()));
        assertTrue("The prices are the same", price.equals(quote.getStockPrice()));
    }

    @Test
    public void testGetDatabaseQuoteListOfQuotes() throws StockServiceException, DatabaseInitializationException {

        BigDecimal price = new BigDecimal(195);

        List<StockQuote> listOfQuotes = stockQuote.getDatabaseQuote(testSymbol, calendarSeptTwoThree, testCal);

        assertTrue("The symbols are the same", testSymbol.equals(listOfQuotes.get(2).getStockSymbol()));
        assertTrue("The prices are the same", price.equals(listOfQuotes.get(2).getStockPrice()));
        assertTrue("The list size should be 5", listOfQuotes.size() == 5);
    }

    @Test
    public void testGetDatabaseQuoteListOfQuotesWithInterval() throws StockServiceException, DatabaseInitializationException {

        BigDecimal price = new BigDecimal(105);

        List<StockQuote> listOfQuotes = stockQuote.getDatabaseQuote(testSymbol, startCalendar, endCalendar, IntervalEnum.DAILY);

        assertTrue("The symbols are the same", testSymbol.equals(listOfQuotes.get(2).getStockSymbol()));
        assertTrue("The prices are the same", price.equals(listOfQuotes.get(2).getStockPrice()));
        assertTrue("The list size should be 5", listOfQuotes.size() == 20);
    }

}
