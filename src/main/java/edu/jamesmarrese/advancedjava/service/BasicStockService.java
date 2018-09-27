package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;

import java.math.BigDecimal;
import java.util.*;

/**
 * This BasicStockService class implements the StockService interface and
 * creates and returns a hard-coded StockQuote instance consisting of
 * a stockSymbol (String), stockPrice (double), and date (Calendar).
 *
 * @author  James Marrese
 */

public class BasicStockService implements StockService {

    /**
     * Create a new StockQuote object
     *
     * @param symbol the stock symbol for a company
     * @return a hard-coded dummy instance of a StockQuote object,
     * based on the stockSymbol provided.
     * e.g., for "APPL", return Apple 100.25 09/13/2018
     */

    public StockQuote getQuote(String symbol, Date date) {

        return new StockQuote("APPL", new BigDecimal(100.25), date);
    }

    /**
     * Get a list of stock prices for the date range specified
     *
     * @param symbol the stock symbol to get the price for
     * @param from   the first day to get the price for
     * @param until  the last day to get the price for
     * @return a list of <CODE>StockQuote</CODE> instances - one for each day specified
     * in the date range provided.
     */

    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) {

        List<StockQuote> stockQuoteList = new ArrayList<>();

        String stockSymbol = symbol;
        Calendar beginDate = from;
        Calendar stopDate = until;

        while ( (beginDate.before(stopDate) )  ||  (beginDate.equals(stopDate)) )  {
            Date date = beginDate.getTime();
            StockQuote dummyQuote = getQuote(stockSymbol, date);
            stockQuoteList.add(dummyQuote);
            beginDate.add(Calendar.DAY_OF_MONTH, 1);
        }

        return stockQuoteList;
    }

    /**
     *
     * @param symbol   the stock symbol to search for
     * @param from     the date of the first stock quote
     * @param until    the date of the last stock quote
     * @param interval the number of StockQuotes to get. E.g., if
     *                 IntervalEnum.DAILY is specified, then one StockQuote
     *                 per day will be returned
     *
     * @return a list of <CODE>StockQuote</CODE> instances - one for each day specified
     *         in the date range provided and at the specified interval.
     */

    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) {

        List<StockQuote> stockQuoteList = new ArrayList<>();

        String stockSymbol = symbol;
        Calendar beginDate = from;
        Calendar stopDate = until;
        IntervalEnum chosenInterval = interval;

        stopDate.add(Calendar.HOUR_OF_DAY, 23);

        if (chosenInterval == IntervalEnum.HOURLY) {
            while ((beginDate.before(stopDate)) || (beginDate.equals(stopDate))) {
                Date date = beginDate.getTime();
                StockQuote dummyQuote = getQuote(stockSymbol, date);
                stockQuoteList.add(dummyQuote);
                beginDate.add(Calendar.HOUR_OF_DAY, 1);
            }
            return stockQuoteList;
        }

        if (chosenInterval == IntervalEnum.DAILY) {
            while ( (beginDate.before(stopDate) )  ||  (beginDate.equals(stopDate)) )  {
                Date date = beginDate.getTime();
                StockQuote dummyQuote = getQuote(stockSymbol, date);
                stockQuoteList.add(dummyQuote);
                beginDate.add(Calendar.DAY_OF_MONTH, 1);
            }
            return stockQuoteList;

        } else if (chosenInterval == IntervalEnum.WEEKLY) {
            while ( (beginDate.before(stopDate) )  ||  (beginDate.equals(stopDate)) )  {
                Date date = beginDate.getTime();
                StockQuote dummyQuote = getQuote(stockSymbol, date);
                stockQuoteList.add(dummyQuote);
                beginDate.add(Calendar.WEEK_OF_MONTH, 1);
            }

            return stockQuoteList;

        }

        //Return empty list if interval is not specified
        List<StockQuote> unfilledStockQuoteList = new ArrayList<>();

        return unfilledStockQuoteList;

    }

}
