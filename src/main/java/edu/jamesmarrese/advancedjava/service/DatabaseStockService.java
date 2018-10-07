/*package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.util.DatabaseConnectionException;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * An implementation of the StockService interface that gets
 * stock data from a database.
 */

/*public class DatabaseStockService implements StockService {
    /**
     * Return the current price for a share of stock  for the given symbol
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. APPL for APPLE
     * @return a  <CODE>BigDecimal</CODE> instance
     * @throws StockServiceException if using the service generates an exception. If this
     *                               happens, trying the service may work, depending on
     *                               the actual cause of the error.
     */

   /* public StockQuote getQuote(@NotNull String symbol, java.util.Date date) throws StockServiceException {

        List<StockQuote> stockQuotes = null;

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            //Use dummy dates to get a stock quote
            String queryString = "select * from quotes where symbol = '" + symbol + "'" +
                    "and time between '%%%%-%%-%%' and '2020-12-30'";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());

            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                date = resultSet.getDate("time");
                BigDecimal price = resultSet.getBigDecimal("price");
                stockQuotes.add(new StockQuote(symbolValue, price, date));
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
     * Get a historical list of stock quotes for the provided symbol
     *
     * @param symbol the stock symbol to search for
     * @param from   the date of the first stock quote
     * @param until  the date of the last stock quote
     * @return a list of StockQuote instances
     * @throws   StockServiceException if using the service generates an exception.
     * If this happens, trying the service may work, depending on the actual cause of the
     * error.
     */

    /*@Override
    public List<StockQuote> getQuote(@NotNull String symbol, @NotNull Calendar from, @NotNull Calendar until)
            throws StockServiceException{

        List<StockQuote> anotherStockQuoteList = null;

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from quotes where symbol = '" + symbol +
                    "' and time between '" + from + "' and '" + until + "'";

            ResultSet resultSet = statement.executeQuery(queryString);
            anotherStockQuoteList = new ArrayList<>(resultSet.getFetchSize());

            while (resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                Date date = resultSet.getDate("time");
                BigDecimal price = resultSet.getBigDecimal("price");
                anotherStockQuoteList.add(new StockQuote(symbolValue, price, date));
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
     * Get a historical list of stock quotes for the provided symbol
     *
     * @param symbol   the stock symbol to search for
     * @param from     the date of the first stock quote
     * @param until    the date of the last stock quote
     * @param interval The number of StockQuotes to get. E.g., if
     *                 IntervalEnum.DAILY is specified, then one StockQuote
     *                 per day will be returned.
     *
     * @return a list of StockQuote instances
     * @throws   StockServiceException if using the service generates an exception.
     * If this happens, trying the service may work, depending on the actual cause of the
     * error.
     */

    /*@Override
    public List<StockQuote> getQuote (@NotNull String symbol, @NotNull Calendar from,
                                      @NotNull Calendar until, @NotNull IntervalEnum interval)
            throws StockServiceException {

        List<StockQuote> thirdStockQuoteList = null;

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from quotes where symbol = '" + symbol +
                    "' and time between '" + from + "' and '" + until + "'";

            ResultSet resultSet = statement.executeQuery(queryString);
            thirdStockQuoteList = new ArrayList<>(resultSet.getFetchSize());

            while (resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                Date date = resultSet.getDate("time");
                BigDecimal price = resultSet.getBigDecimal("price");
                thirdStockQuoteList.add(new StockQuote(symbolValue, price, date));
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }

        if (thirdStockQuoteList.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }

        return thirdStockQuoteList;

    }

}
*/