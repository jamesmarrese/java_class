package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This StockServiceFactory class implements the StockService interface
 * and returns StockQuote instances. StockQuote instances consist of a
 * stock symbol (String), stock price (double), and date (Date).
 *
 * @author  James Marrese
 */

public class StockServiceFactory implements StockService {

    /**
     * Create a new StockQuote object
     *
     * @param symbol the stock symbol for a company
     *
     * @return a hard-coded dummy instance of a StockQuote object,
     * based on the stockSymbol provided.
     * e.g., for "APPL", return APPL 100.25 2018/09/13
     */

    public StockQuote getQuote(String symbol) {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13");
        Date date = new Date();
        dateFormat.format(date);

        if (symbol == null)
            throw new NullPointerException();
        if (symbol.equals("APPL"))
            return new StockQuote("APPL", 100.25, date);
        if (symbol.equals("AMZN"))
            return new StockQuote("AMZN", 150.75, date);
        if (symbol.equals("NFLX"))
            return new StockQuote("NFLX", 201.54, date);

        else return new StockQuote("DMMY", 123.45, date);
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

        String stockSymbol = symbol;
        Calendar beginDate = from;
        Calendar stopDate  = until;

        while (beginDate.before(stopDate)) {
            StockQuote dummyQuote = getQuote(stockSymbol);
            stockQuoteList.add(dummyQuote);
            beginDate.add(Calendar.DAY_OF_YEAR, 1);
        }

        return stockQuoteList;

    }

}