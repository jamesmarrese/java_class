package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.util.DatabaseConnectionException;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public StockQuote getQuote(@NotNull String symbol, @NotNull Date date) throws StockServiceException {

        List<StockQuote> stockQuotes = null;

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            //Use a dummy date range to get a stock quote
            String queryString = "select * from quotes where symbol = '" + symbol + "'" +
                    "and time between '2018-09-21' and '2018-09-30';";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());

            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                date = resultSet.getDate("time");
                BigDecimal price = resultSet.getBigDecimal("price");
                stockQuotes.add(new StockQuote(symbolValue, date, price));
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }

        return stockQuotes.get(0);
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

        List<StockQuote> anotherStockQuoteList = null;

        java.sql.Date sqlFromDate = new java.sql.Date(from.getTimeInMillis());
        java.sql.Date sqlUntilDate = new java.sql.Date(until.getTimeInMillis());

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            String queryString = "select * from quotes where symbol = '" + symbol +
                    "' and time between '" + sqlFromDate +
                    "' and '" + sqlUntilDate + "';";

            ResultSet rs = statement.executeQuery(queryString);
            anotherStockQuoteList = new ArrayList<>(rs.getFetchSize());

            while ((rs.next())) {
                String symbolValue = rs.getString("symbol");
                java.sql.Date date = rs.getDate("time");
                BigDecimal price = rs.getBigDecimal("price");
                if (symbolValue.equals(symbol)) {
                    anotherStockQuoteList.add(new StockQuote(symbolValue, date, price));
                }
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }

        if (anotherStockQuoteList.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }

        return anotherStockQuoteList;

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

        return null;

    }

}
