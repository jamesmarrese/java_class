package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;

import java.util.Calendar;
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
     *
     * @return a <CODE>StockQuote</CODE> instance consisting of
     *         a stockSymbol (String), stockPrice (double), and date (Date).
     */
    StockQuote getQuote(String symbol);

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
}
