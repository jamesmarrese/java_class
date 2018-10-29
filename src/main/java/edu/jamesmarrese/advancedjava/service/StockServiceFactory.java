package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;

import javax.validation.constraints.NotNull;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.*;

/**
 * This StockServiceFactory class implements the StockService interface
 * and returns StockQuote instances. StockQuote instances consist of a
 * stock symbol (String), stock price (double), and date (Calendar).
 *
 * @author  James Marrese
 */

public class StockServiceFactory implements StockService {

    /**
     * Create a new StockQuote object
     *
     * @param symbol the stock symbol for a company
     * @return a hard-coded dummy instance of a StockQuote object,
     * based on the stockSymbol provided.
     * e.g., for "APPL", return APPL 100.25 09/13/2018
     */

    public StockQuote getQuote (@NotNull String symbol, @NotNull Calendar date) throws StockServiceException {

        StockQuote stockQuote = null;

        try {
            Stock yahooStock = YahooFinance.get(symbol, date);
            stockQuote = new StockQuote(yahooStock.getQuote().getSymbol(), yahooStock.getQuote().getLastTradeTime(),
                    yahooStock.getQuote().getPrice());

            if (yahooStock.getQuote().getSymbol() == null) {
                throw new StockServiceException("Stock symbol " + symbol + " does not exist. " +
                        "Please select another stock symbol.");
            } if (yahooStock.getQuote().getLastTradeTime() == null) {
                throw new StockServiceException("Date " + date.toString() + " is not valid. " +
                        "Please select another date.");
            } if (yahooStock.getQuote().getPrice() == null) {
                throw new StockServiceException("Price not found. Check if stock symbol " +
                        "or date are invalid.");
            }
        } catch (IOException e) {
            throw new StockServiceException(e.getMessage(), e);
        }

        return stockQuote;

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

    public List<StockQuote> getQuote(@NotNull String symbol, @NotNull Calendar from,
                                     @NotNull Calendar until) throws StockServiceException {

        List<StockQuote> listOfQuotes = new ArrayList<StockQuote>();

        try {
            Stock yahooStock = YahooFinance.get(symbol);

            /*"Dummy" yahoo finance "Interval" is set to daily to make HistoricalQuote method work.
              API method requires a specified interval, will not work without one.
             */

            List<HistoricalQuote> listOfHistoricalQuotes = yahooStock.getHistory(from, until, Interval.DAILY);

            if (yahooStock.getQuote().getSymbol() == null) {
                throw new StockServiceException("Stock symbol " + symbol + " does not exist. " +
                        "Please select another stock symbol.");
            }

            for (HistoricalQuote historicalQuote : listOfHistoricalQuotes) {
                listOfQuotes.add(new StockQuote(historicalQuote.getSymbol(), historicalQuote.getDate(), historicalQuote.getAdjClose()));
            }

            for (int i = 0; i < listOfHistoricalQuotes.size(); ++i) {
                if (listOfHistoricalQuotes.get(i).getDate() == null) {
                    throw new StockServiceException("Date " + listOfHistoricalQuotes.get(i).getDate().toString() +
                            " is not valid. Please select another date.");
                } if (listOfHistoricalQuotes.get(i).getAdjClose() == null) {
                    throw new StockServiceException("Price not found. Check if stock symbol " +
                            "or date are invalid.");
                }
            }

        } catch (IOException e) {
            throw new StockServiceException(e.getMessage(), e);
        }

        return listOfQuotes;
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

    public List<StockQuote> getQuote(@NotNull String symbol, @NotNull Calendar from,
                                     @NotNull Calendar until, @NotNull IntervalEnum interval)
            throws StockServiceException {

        List<StockQuote> listOfQuotes = new ArrayList<StockQuote>();
        yahoofinance.histquotes.Interval yahooQuoteInterval = null;

        /* convert Interval ENUM to yahoo finance interval.
           there is there is no "HOURLY" interval in the API, so convert to DAILY if received.
           default yahoo finance interval is DAILY.
         */
        if (interval == IntervalEnum.HOURLY) {
            yahooQuoteInterval = Interval.DAILY;
        } else if (interval == IntervalEnum.DAILY) {
            yahooQuoteInterval = yahoofinance.histquotes.Interval.DAILY;
        } else if (interval == IntervalEnum.WEEKLY) {
            yahooQuoteInterval = Interval.WEEKLY;
        } else {
            yahooQuoteInterval = Interval.DAILY;
        }

        try {
            Stock yahooStock = YahooFinance.get(symbol);
            List<HistoricalQuote> listOfHistoricalQuotes = yahooStock.getHistory(from, until, yahooQuoteInterval);

            if (yahooStock.getQuote().getSymbol() == null) {
                throw new StockServiceException("Stock symbol " + symbol + " does not exist. " +
                        "Please select another stock symbol.");
            }

            for (HistoricalQuote historicalQuote : listOfHistoricalQuotes) {
                listOfQuotes.add(new StockQuote(historicalQuote.getSymbol(), historicalQuote.getDate(), historicalQuote.getAdjClose()));
            }

            for (int i = 0; i < listOfHistoricalQuotes.size(); ++i) {
                if (listOfHistoricalQuotes.get(i).getDate() == null) {
                    throw new StockServiceException("Date " + listOfHistoricalQuotes.get(i).getDate().toString() +
                            " is not valid. Please select another date.");
                } if (listOfHistoricalQuotes.get(i).getAdjClose() == null) {
                    throw new StockServiceException("Price not found. Check if stock symbol " +
                            "or date are invalid.");
                }
            }

        } catch (IOException e) {
            throw new StockServiceException(e.getMessage(), e);
        }

        return listOfQuotes;

    }

}