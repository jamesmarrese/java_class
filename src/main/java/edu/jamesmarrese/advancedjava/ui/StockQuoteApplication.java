package edu.jamesmarrese.advancedjava.ui;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * This StockQuoteApplication class instantiates a StockServiceFactory
 * class and calls the two getStockQuote methods in the class titled
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

        /**
         * @throws AssertionError if endDate is before startDate
         */
        if (endDate.before(startDate)) {
            throw new AssertionError("The end date is before the start date");
        }

        /*Get the difference between the begin date and
          the end date for the stock quote list
         */
        long dateDifference = stopDate.getTime() - beginDate.getTime();

        /*Convert the difference from long to int
          Also convert from milliseconds to days
          long variable is currently expressed in milliseconds
          1,000 milliseconds in a second
          60 seconds in a minute
          60 minutes in an hour
          24 hours in a day
         */
        int  numberOfDays = (int) dateDifference / 1000 / 60 / 60 / 24;

        StockQuote populatedStock = applicationTest.getQuote(symbol);

        System.out.println("Result of call to get a Stock Quote: "
                + populatedStock.toString());

        List<StockQuote> stockList = new ArrayList<>();

        stockList = applicationTest.getQuote(symbol, startDate, endDate);

        /**
         * @throws NullPointerException if startDate or endDate are null
         */
        if (startDate == null  ||  endDate == null) {
            throw new NullPointerException();
        }

        System.out.println();
        System.out.println("Result of call to get a list of Stock Quotes: ");

        for (int i = 0; i < numberOfDays; ++i) {
            System.out.println(stockList.get(i).toString());
        }

    }
}