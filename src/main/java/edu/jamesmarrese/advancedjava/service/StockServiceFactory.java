package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.util.DatabaseConnectionException;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

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
                date = resultSet.getTimestamp("time");
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

        //Convert Calendar date parameters to java.sql dates with timestamps
        java.sql.Timestamp sqlFromDate = new java.sql.Timestamp(from.getTimeInMillis());
        java.sql.Timestamp sqlUntilDate = new java.sql.Timestamp(until.getTimeInMillis());

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            //MySQL statement to fetch data from the database
            String queryString = "select distinct symbol, time, price from quotes where symbol = '" + symbol +
                    "' and time between '" + sqlFromDate +
                    "' and '" + sqlUntilDate +
                    "' order by time asc;";

            ResultSet rs = statement.executeQuery(queryString);
            anotherStockQuoteList = new ArrayList<>(rs.getFetchSize());

            while ((rs.next())) {
                String symbolValue = rs.getString("symbol");
                java.sql.Timestamp date = rs.getTimestamp("time");
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

        List<StockQuote> thirdStockQuoteList = null;
        IntervalEnum chosenInterval = interval;

        until.add(Calendar.HOUR_OF_DAY, 23);
        until.add(Calendar.MINUTE, 59);

        //Convert Calendar date parameters to java.sql dates with timestamps
        java.sql.Timestamp sqlFromDate = new java.sql.Timestamp(from.getTimeInMillis());
        java.sql.Timestamp sqlUntilDate = new java.sql.Timestamp(until.getTimeInMillis());

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            //MySQL statement to fetch data from the database
            String queryString = "select distinct symbol, time, price from quotes where symbol = '" + symbol +
                    "' and time between '" + sqlFromDate +
                    "' and '" + sqlUntilDate +
                    "' order by time asc;";


            ResultSet rs = statement.executeQuery(queryString);
            thirdStockQuoteList = new ArrayList<>(rs.getFetchSize());

            /*If a stock quote has the same date and hour as another stock quote,
              then they occur within the same hour and only one will be added.
             */
            if (chosenInterval == IntervalEnum.HOURLY) {
                while ((rs.next())) {
                    String symbolValue = rs.getString("symbol");
                    java.sql.Timestamp date = rs.getTimestamp("time");
                    BigDecimal price = rs.getBigDecimal("price");

                    /*Convert the java.sql date retrieved from the database
                      to a Calendar object.
                     */
                    Calendar databaseDate = Calendar.getInstance();
                    databaseDate.setTime(date);

                    /*Add the "first" stock quote fetched from the database to the list,
                      whether the list is null or has a size of zero. This is necessary
                      in order to ensure there is something to which the "second" stock
                      quote retrieved from the database can be compared.
                     */
                    if ( (thirdStockQuoteList == null)  ||  (thirdStockQuoteList.size() == 0) ) {
                        thirdStockQuoteList.add(new StockQuote(symbolValue, date, price));
                    }

                    for (int i = 0; i < thirdStockQuoteList.size(); i++) {

                        //Get the day and hour of the date retrieved from the database
                        int day = databaseDate.get(Calendar.DAY_OF_MONTH);
                        int hour = databaseDate.get(Calendar.HOUR_OF_DAY);

                        /*Get the date of "this" stock quote from the list
                         and convert it to a Calendar object.
                         */
                        Date listDate = thirdStockQuoteList.get(i).getDateRecorded();
                        Calendar listCal = Calendar.getInstance();
                        listCal.setTime(listDate);

                        /*If the day and hour match another stock quote, do not
                          add it to the list. Otherwise, add it to the list.
                         */
                        if ( (day == listCal.get(Calendar.DAY_OF_MONTH))  &&
                                (hour == listCal.get(Calendar.HOUR_OF_DAY)) ) {
                        } else {
                            thirdStockQuoteList.add(new StockQuote(symbolValue, date, price));
                            break;
                        }

                        /*Clear the Calendar object retrieved from the list in order
                          to prep for the next for loop iteration
                         */
                        listCal.clear();
                    }

                    /*Clear the Calendar object retrieved from the database  in order
                          to prep for the next while loop iteration
                         */
                    databaseDate.clear();
                }
            }

            if (chosenInterval == IntervalEnum.DAILY) {
                while (rs.next()) {
                    String symbolValue = rs.getString("symbol");
                    java.sql.Timestamp date = rs.getTimestamp("time");
                    BigDecimal price = rs.getBigDecimal("price");
                    thirdStockQuoteList.add(new StockQuote(symbolValue, date, price));
                }
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