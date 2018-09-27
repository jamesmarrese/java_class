package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This StockService interface provides the interface for the StockQuote and
 * BasicStockService classes.
 *
 * @author  James Marrese
 */

public interface StockService {
    /**
     * Return the current price for a share of stock for the given symbol
     *
     * @param symbol the stock symbol of the company you want a quote for
     *               e.g., APPL for Apple
     * @param date   the date on which the stock is quoted
     *
     * @return a <CODE>StockQuote</CODE> instance consisting of
     *         a stockSymbol (String), stockPrice (double), and date (Date).
     */
    StockQuote getQuote(String symbol, Date date);

    /**
     * Get a historical list of stock quotes for the provided symbol
     *
     * @param symbol the stock symbol to search for
     * @param from   the date of the first stock quote
     * @param until  the date of the last stock quote
     *
     * @return a list of StockQuote instances,
     *         one for each day in the range specified
     */
    List<StockQuote> getQuote (String symbol, Calendar from, Calendar until);

    /**
     * Get a historical list of stock quotes for the provided symbol
     * This method will return one StockQuote per 24 hour period
     * If you wish more or fewer StockQuotes returned, you can specify
     * the IntervalEnum yourself using the IntervalEnum parameter
     *
     * @param symbol   the stock symbol to search for
     * @param from     the date of the first stock quote
     * @param until    the date of the last stock quote
     * @param interval the number of StockQuotes to get. E.g., if
     *                 IntervalEnum.DAILY is specified, then one StockQuote
     *                 per day will be returned
     *
     * @return a list of StockQuote instances, one for each interval specified
     */

    List<StockQuote> getQuote (String symbol, Calendar from, Calendar until, IntervalEnum interval);

}
