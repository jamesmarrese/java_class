package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;

import javax.validation.constraints.NotNull;

import edu.jamesmarrese.advancedjava.service.IntervalEnum;
import edu.jamesmarrese.advancedjava.service.StockService;
import edu.jamesmarrese.advancedjava.service.StockServiceException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * This StockServiceFactory class implements the StockService interface
 * and returns StockQuote instances. StockQuote instances consist of a
 * stock symbol (String), stock price (double), and date (Calendar).
 *
 * @author  James Marrese
 */

/**
 * THIS CODE WAS AN ATTEMPT TO FIX THE LIST METHODS IN ASSIGNMENT #8
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

    public StockQuote getQuote (@NotNull String symbol, @NotNull Calendar date)
            throws StockServiceException {

        StockQuote stockQuote = null;

        try {
            Stock yahooStock = YahooFinance.get(symbol);
            BigDecimal stockPrice = yahooStock.getQuote().getPrice();

            stockQuote = new StockQuote(symbol, date, stockPrice);

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
                                     @NotNull Calendar until) throws StockServiceException, IOException {

        //This is a list of locally defined stocks
        List<StockQuote> listOfLocalStocks = new ArrayList<StockQuote>();

        try {
            Stock stock = YahooFinance.get(symbol);

            /*Yahoo finance "Interval" is set to daily. The API will not return a range of stocks
              without an interval present.
             */
            List<HistoricalQuote> stockHistoricalQuote = stock.getHistory(from, until, Interval.DAILY);

            for (HistoricalQuote historicalQuote : stockHistoricalQuote) {
                if (historicalQuote.getSymbol() == null) {
                    throw new StockServiceException("Stock symbol " + symbol + " does not exist. " +
                            "Please select another stock symbol.");
                }
                if (historicalQuote.getDate() == null) {
                    throw new StockServiceException("Date " + historicalQuote.getDate().getTime().toString() +
                            " is not valid. Please select another date.");
                }
                if (historicalQuote.getClose() == null) {
                    throw new StockServiceException("Price not found. Check if stock symbol " +
                            "or date are invalid.");
                }
            }

            for (HistoricalQuote quote : stockHistoricalQuote) {
                listOfLocalStocks.add(new StockQuote(symbol, quote.getDate(), quote.getClose()));
            }

        } catch (IOException e) {
            throw new StockServiceException(e.getMessage(), e);
        }

        return listOfLocalStocks;
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
            throws StockServiceException, IOException {

        //This is a list of locally defined stocks
        List<StockQuote> listOfLocalStocks = new ArrayList<StockQuote>();

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
            Stock stock = YahooFinance.get(symbol);

            /*Yahoo finance "Interval" is set to daily. The API will not return a range of stocks
              without an interval present.
             */
            List<HistoricalQuote> stockHistoricalQuote = stock.getHistory(from, until, yahooQuoteInterval);

            for (HistoricalQuote historicalQuote : stockHistoricalQuote) {
                if (historicalQuote.getSymbol() == null) {
                    throw new StockServiceException("Stock symbol " + symbol + " does not exist. " +
                            "Please select another stock symbol.");
                }
                if (historicalQuote.getDate() == null) {
                    throw new StockServiceException("Date " + historicalQuote.getDate().getTime().toString() +
                            " is not valid. Please select another date.");
                }
                if (historicalQuote.getClose() == null) {
                    throw new StockServiceException("Price not found. Check if stock symbol " +
                            "or date are invalid.");
                }
            }

            for (HistoricalQuote quote : stockHistoricalQuote) {
                listOfLocalStocks.add(new StockQuote(symbol, quote.getDate(), quote.getClose()));
            }

        } catch (IOException e) {
            throw new StockServiceException(e.getMessage(), e);
        }

        return listOfLocalStocks;

    }

}