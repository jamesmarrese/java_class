package edu.jamesmarrese.advancedjava.ui;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.IntervalEnum;
import edu.jamesmarrese.advancedjava.service.StockServiceException;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;
import edu.jamesmarrese.advancedjava.util.DatabaseInitializationException;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This StockQuoteApplication class instantiates a StockServiceFactory
 * class and calls the three getStockQuote methods in the class titled
 * StockServiceFactory.
 */

public class StockQuoteApplication {

    /**
     * An enumeration that indicates how the program terminates (ends)
     */
    private enum ProgramTerminationStatusEnum {

        // for now, we just have normal or abnormal but could more specific ones as needed.
        NORMAL(0),
        ABNORMAL(-1);

        // when the program exits, this value will be reported to underlying OS
        private int statusCode;

        /**
         * Create a new  ProgramTerminationStatusEnum
         *
         * @param statusCodeValue the value to return the OS. A value of 0
         *                        indicates success or normal termination.
         *                        non 0 numbers indicate abnormal termination.
         */
        private ProgramTerminationStatusEnum(int statusCodeValue) {
            this.statusCode = statusCodeValue;
        }

        /**
         * @return The value sent to OS when the program ends.
         */
        private int getStatusCode() {
            return statusCode;
        }
    }

    /**
     * Terminate the application.
     *
     * @param statusCode        an enum value that indicates if the program terminated ok or not.
     * @param diagnosticMessage A message to display to the user when the program ends.
     *                          This should be an error message in the case of abnormal termination
     *                          <p/>
     *                          NOTE: This is an example of DRY in action.
     *                          A program should only have one exit point. This makes it easy to do any clean up
     *                          operations before a program quits from just one place in the code.
     *                          It also makes for a consistent user experience.
     */
    private static void exit(ProgramTerminationStatusEnum statusCode, String diagnosticMessage) {
        if (statusCode == ProgramTerminationStatusEnum.NORMAL) {
            System.out.println(diagnosticMessage);
        } else if (statusCode == ProgramTerminationStatusEnum.ABNORMAL) {
            System.err.println(diagnosticMessage);
        } else {
            throw new IllegalStateException("Unknown ProgramTerminationStatusEnum.");
        }
        System.exit(statusCode.getStatusCode());
    }

    public static void main(String[] args) throws ParseException, StockServiceException,
            DatabaseInitializationException, IOException {

        if (args.length != 4) {
            exit(ProgramTerminationStatusEnum.ABNORMAL,
                    "Please supply 4 arguments a stock symbol, " +
                            "a start date (yyyy-MM-dd) and end date (yyyy-MM-dd)" +
                            " and an interval at which stock quotes will be returned.");
        }

        StockServiceFactory applicationTest = new StockServiceFactory();

        //The stock symbol
        String symbol = args[0];

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //Set the begin date for the list of stock quotes
        Date beginDate = format.parse(args[1]);
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(beginDate);

        //Set the end date for the list of stock quotes
        Date stopDate = format.parse(args[2]);
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(stopDate);

        /**
         * @throws NullPointerException if startDate or endDate are null
         */
        if (startDate == null  ||  endDate == null) {
            throw new NullPointerException();
        }

        /**
         * @throws AssertionError if endDate is before startDate
         */
        if (endDate.before(startDate)) {
            throw new AssertionError("The end date is before the start date");
        }

        IntervalEnum chosenInterval = null;

        String intervalString = args[3];

        /* Assign the correct interval enum. HOURLY interval will become DAILY, as the Yahoo API
           offers DAILY as the lowest interval. DAILY and WEEKLY intervals are accepted. All other
           intervals default to DAILY (the API will not return a date range of stock quotes unless
           an interval is specified).
         */
        if (intervalString.startsWith("HOURLY")) {
            chosenInterval = IntervalEnum.DAILY;
        } else if (intervalString.startsWith("DAILY")) {
            chosenInterval = IntervalEnum.DAILY;
        } else if (intervalString.startsWith("WEEKLY")) {
            chosenInterval = IntervalEnum.WEEKLY;
        } else {
            chosenInterval = IntervalEnum.DAILY;
        }

        StockQuote stock = applicationTest.getQuote(symbol, startDate);

        //Today's date
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 16);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 1);

        /*Print out a single stock quote.
         */
        System.out.println();
        System.out.println("Result of call to get a single Stock Quote for today: ");
        System.out.println(stock.getStockSymbol() + " " +
                stock.getStockPrice().toString() + " " +
                today.getTime().toString());

        List<StockQuote> stockList = applicationTest.getQuote(symbol, startDate, endDate);

        /*Print out a list of StockQuotes within the specified date range,
          ignoring the specified interval for now.
         */
        System.out.println();
        System.out.println("Result of call to get a list of Stock Quotes: ");

        for (StockQuote stockQuote : stockList) {
            System.out.println(stockQuote.getStockSymbol() + " " +
                    stockQuote.getStockPrice().toString() + " " +
                    stockQuote.getDateRecorded().getTime().toString());
        }


        List<StockQuote> stockListWithInterval = applicationTest.getQuote(symbol, startDate, endDate, chosenInterval);

        /*Print out a list of StockQuotes within the specified date range
          and according to the specified interval.
         */
        System.out.println();
        System.out.println("Result of call to get a list of Stock Quotes with specified interval: ");

        for (StockQuote stockQuote : stockListWithInterval) {
            System.out.println(stockQuote.getStockSymbol() + " " +
                    stockQuote.getStockPrice().toString() + " " +
                    stockQuote.getDateRecorded().getTime().toString());
        }

    }
}