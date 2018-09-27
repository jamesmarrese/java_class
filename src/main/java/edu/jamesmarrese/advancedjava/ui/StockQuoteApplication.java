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

        IntervalEnum chosenInterval = null;

        String intervalString = args[3];

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

        /*Get the difference between the begin date and
          the end date for the stock quote list
         */
        long dateDifference = stopDate.getTime() - beginDate.getTime();

        /*Convert from milliseconds to hours, days, and weeks.
          The + 23 for hour conversion is to ensure StockQuotes are
          retrieved into the final day's worth of data. E.g., for a
          range from 9/21/2018 to 9/28/2018, the last hour of output will
          occur on Fri Sep 28 23:00:00 EDT 2018
         */
        long numberOfHours = dateDifference / 1000 / 60 / 60 + 23;
        long numberOfDays  = dateDifference / 1000 / 60 / 60 / 24;
        long numberOfWeeks = dateDifference / 1000 / 60 / 60 / 24 / 7;

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

        //Print out a list of StockQuotes within the specified date range
        System.out.println();
        System.out.println("Result of call to get a list of Stock Quotes: ");

        for (int i = 0; i <= numberOfDays; ++i) {
            Calendar secondFakeDate = Calendar.getInstance();
            secondFakeDate.setTime(stockList.get(i).getDateRecorded());

            System.out.println(stockList.get(i).getStockSymbol() + " " +
                    stockList.get(i).getStockPrice().toString() + " " +
                    secondFakeDate.getTime().toString());
        }


        List<StockQuote> stockListWithInterval = new ArrayList<>();

        /*Clear startDate calendar object, then reset to original start date
          Failure to do so will result in incorrect start date
         */
        startDate.clear();;
        startDate = Calendar.getInstance();
        startDate.setTime(beginDate);

        /*Clear endDate calendar object, then reset to original end date
          Failure to do so will result in incorrect stop date
         */
        endDate.clear();;
        endDate = Calendar.getInstance();
        endDate.setTime(stopDate);

        stockListWithInterval = applicationTest.getQuote(symbol, startDate, endDate, chosenInterval);

        if (startDate == null  ||  endDate == null) {
            throw new NullPointerException();
        }

        /*Print out a list of StockQuotes within the specified date range
          and according to the specified interval
         */
        System.out.println();
        System.out.println("Result of call to get a list of Stock Quotes with specified interval: ");

        if (intervalString.startsWith("HOURLY")) {
            for (int i = 0; i <= numberOfHours; ++i) {
                Calendar thirdFakeDate = Calendar.getInstance();
                thirdFakeDate.setTime(stockListWithInterval.get(i).getDateRecorded());

                System.out.println(stockListWithInterval.get(i).getStockSymbol() + " " +
                        stockListWithInterval.get(i).getStockPrice().toString() + " " +
                        thirdFakeDate.getTime().toString() );
            }
        }

        if (intervalString.startsWith("DAILY")) {
            for (int i = 0; i <= numberOfDays; ++i) {
                Calendar thirdFakeDate = Calendar.getInstance();
                thirdFakeDate.setTime(stockListWithInterval.get(i).getDateRecorded());

                System.out.println(stockListWithInterval.get(i).getStockSymbol() + " " +
                        stockListWithInterval.get(i).getStockPrice().toString() + " " +
                        thirdFakeDate.getTime().toString() );
            }
        }

        else if (intervalString.startsWith("WEEKLY")) {
            for (int i = 0; i <= numberOfWeeks; ++i) {
                Calendar thirdFakeDate = Calendar.getInstance();
                thirdFakeDate.setTime(stockListWithInterval.get(i).getDateRecorded());

                System.out.println(stockListWithInterval.get(i).getStockSymbol() + " " +
                        stockListWithInterval.get(i).getStockPrice().toString() + " " +
                        thirdFakeDate.getTime().toString() );
            }
        }


    }
}