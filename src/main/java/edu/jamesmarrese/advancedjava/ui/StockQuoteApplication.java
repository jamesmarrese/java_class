package edu.jamesmarrese.advancedjava.ui;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.IntervalEnum;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * This StockQuoteApplication class instantiates a StockServiceFactory
 * class and calls the three getStockQuote methods in the class titled
 * StockServiceFactory.
 */

public class StockQuoteApplication {

    public static void main(String[] args) throws ParseException {

        StockServiceFactory applicationTest = new StockServiceFactory();

        //The stock symbol
        String symbol = args[0];

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        //Set the begin date for the list of stock quotes
        Date beginDate = format.parse(args[1]);
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(beginDate);

        //Set the end date for the list of stock quotes
        Date stopDate = format.parse(args[2]);
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(stopDate);

        IntervalEnum chosenInterval = null;

        String intervalString = args[3];

        //Assign the correct interval enum
        if (intervalString.startsWith("HOURLY")) {
            chosenInterval = IntervalEnum.HOURLY;
        } else if (intervalString.startsWith("DAILY")) {
            chosenInterval = IntervalEnum.DAILY;
        } else if (intervalString.startsWith("WEEKLY")) {
            chosenInterval = IntervalEnum.WEEKLY;
        }

        /**
         * @throws AssertionError if endDate is before startDate
         */
        if (endDate.before(startDate)) {
            throw new AssertionError("The end date is before the start date");
        }

        StockQuote populatedStock = applicationTest.getQuote(symbol, beginDate);

        Calendar fakeDate = Calendar.getInstance();
        fakeDate.setTime(populatedStock.getDateRecorded());

        //Print out one StockQuote
        System.out.println("Result of call to get a Stock Quote: ");
        System.out.println(populatedStock.getStockSymbol()  + " " +
                populatedStock.getStockPrice().toString() + " " +
                fakeDate.getTime().toString());


        List<StockQuote> stockList = new ArrayList<>();

        stockList = applicationTest.getQuote(symbol, startDate, endDate);

        /**
         * @throws NullPointerException if startDate or endDate are null
         */
        if (startDate == null  ||  endDate == null) {
            throw new NullPointerException();
        }

        /*Print out a list of StockQuotes within the specified date range,
          ignoring the specified interval for now.
         */
        System.out.println();
        System.out.println("Result of call to get a list of Stock Quotes: ");

        for (StockQuote stockQuote : stockList) {
            System.out.println(stockQuote);
        }


        List<StockQuote> stockListWithInterval = new ArrayList<>();

        /*Clear startDate calendar object, then reset to original start date.
          Failure to do so will result in incorrect start date.
         */
        startDate.clear();
        startDate = Calendar.getInstance();
        startDate.setTime(beginDate);

        /*Clear endDate calendar object, then reset to original end date.
          Failure to do so will result in incorrect stop date.
         */
        endDate.clear();
        endDate = Calendar.getInstance();
        endDate.setTime(stopDate);

        stockListWithInterval = applicationTest.getQuote(symbol, startDate, endDate, chosenInterval);

        /**
         * @throws NullPointerException if startDate or endDate are null
         */
        if (startDate == null  ||  endDate == null) {
            throw new NullPointerException();
        }

        /*Print out a list of StockQuotes within the specified date range
          and according to the specified interval.
         */
        System.out.println();
        System.out.println("Result of call to get a list of Stock Quotes with specified interval: ");

        for (StockQuote stockQuote : stockListWithInterval) {
            System.out.println(stockQuote);
        }

    }
}