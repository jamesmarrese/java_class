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
     *
     * @return a hard-coded dummy instance of a StockQuote object,
     * based on the stockSymbol provided.
     * e.g., for "APPL", return Apple 100.25 2018/09/13
     */

    public StockQuote getQuote(String symbol) {

        return new StockQuote ("APPL", new BigDecimal(100.25), Calendar.getInstance().getTime());
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

    public List<StockQuote> getQuote (String symbol, Calendar from, Calendar until) {

            List<StockQuote> stockQuoteList = new ArrayList<>();

            Calendar beginDate = from;
            Calendar stopDate  = until;

            while (beginDate.before(stopDate)) {
                StockQuote dummyQuote = getQuote("APPL");
                stockQuoteList.add(dummyQuote);
                beginDate.add(Calendar.DAY_OF_YEAR, 1);
            }

            return stockQuoteList;
    }

    public List<StockQuote> getQuote (String symbol, Calendar from, Calendar until, Interval interval) {

        List<StockQuote> stockQuoteList = new ArrayList<>();

        String stockSymbol = symbol;
        Calendar beginDate = from;
        Calendar stopDate  = until;
        Interval chosenInterval = interval;

        if (chosenInterval == Interval.HOURLY) {

        } else if (chosenInterval == Interval.DAILY) {

        } else if (chosenInterval == Interval.WEEKLY) {

        }

        while (beginDate.before(stopDate)) {
            StockQuote dummyQuote = getQuote(stockSymbol);
            stockQuoteList.add(dummyQuote);
            beginDate.add(Calendar.DAY_OF_YEAR, 1);
        }

        return stockQuoteList;
    }
}