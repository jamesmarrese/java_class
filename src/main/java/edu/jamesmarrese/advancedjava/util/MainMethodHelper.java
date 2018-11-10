package edu.jamesmarrese.advancedjava.util;

import edu.jamesmarrese.advancedjava.model.StockQuote;

import java.util.Calendar;
import java.util.List;

/**
 * Helper methods for printing out stock quotes in main
 */
public class MainMethodHelper {

    /**
     * Helper method that prints out a single stock quote
     *
     * @param stockQuote a StockQuote object
     */
    public static void printOneStockQuote (StockQuote stockQuote) {

        Calendar fourPM = Calendar.getInstance();
        fourPM.set(Calendar.HOUR_OF_DAY, 16);
        fourPM.set(Calendar.MINUTE, 0);
        fourPM.set(Calendar.SECOND, 0);

        Calendar nineThirtyAM = Calendar.getInstance();
        nineThirtyAM.set(Calendar.HOUR_OF_DAY, 9);
        nineThirtyAM.set(Calendar.MINUTE, 30);
        nineThirtyAM.set(Calendar.SECOND, 0);

        Calendar rightNow = Calendar.getInstance();

        if (rightNow.before(nineThirtyAM) || rightNow.after(fourPM)) {
            System.out.println();
            System.out.println("Result of call to get a single Stock Quote for today: ");
            System.out.println(stockQuote.getStockSymbol() + " " +
                    stockQuote.getStockPrice().toString() + " " +
                    "<-- This is the previous closing price");
        } else {
            System.out.println();
            System.out.println("Result of call to get a single Stock Quote for today: ");
            System.out.println(stockQuote.getStockSymbol() + " " +
                    stockQuote.getStockPrice().toString() + " " +
                    rightNow.getTime().toString());
        }
    }


    /**
     * Helper method that prints out a list of StockQuotes within a specified date range
     *
     * @param stockQuoteList a list of StockQuote objects
     */
    public static void printListOfStockQuotesWithoutInterval (List<StockQuote> stockQuoteList) {

        System.out.println();
        System.out.println("Result of call to get a list of Stock Quotes: ");

        for (StockQuote stockQuote : stockQuoteList) {
            System.out.println(stockQuote.getStockSymbol() + " " +
                    stockQuote.getStockPrice().toString() + " " +
                    stockQuote.getDateRecorded().getTime().toString());
        }
    }

    /**
     * Helper method that prints out a list of StockQuotes within the specified date range
     * and according to the specified interval.
     *
     * @param stockQuoteListWithInterval a list of StockQuote objects
     */
    public static void printListOfStockQuotesWithtInterval (List<StockQuote> stockQuoteListWithInterval) {
        System.out.println();
        System.out.println("Result of call to get a list of Stock Quotes with specified interval: ");

        for (StockQuote stockQuote : stockQuoteListWithInterval) {
            System.out.println(stockQuote.getStockSymbol() + " " +
                    stockQuote.getStockPrice().toString() + " " +
                    stockQuote.getDateRecorded().getTime().toString());
        }
    }


}
