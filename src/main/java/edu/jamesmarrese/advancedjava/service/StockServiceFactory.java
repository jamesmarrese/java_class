package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.StockQuote;

import javax.validation.constraints.NotNull;

import edu.jamesmarrese.advancedjava.util.DatabaseConnectionException;
import edu.jamesmarrese.advancedjava.util.DatabaseInitializationException;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * This StockServiceFactory class implements the StockService interface
 * and returns StockQuote instances from either the Yahoo Finance API
 * or from a SQL database. StockQuote instances consist of a stock
 * symbol (String), stock price (double), and date (Calendar).
 *
 * @author  James Marrese
 */
public class StockServiceFactory implements StockService {

    /**
     * Private helper method for iterating through a List of Historical Quotes from Yahoo Finance API
     *
     * @param histQuote a list of stock quotes from the Yahoo Finance API
     * @return a List of locally defined StockQuotes (not the Yahoo Finance API)
     * @throws StockServiceException
     */
    private List<StockQuote> stockQuoteHelper (List<HistoricalQuote> histQuote) throws StockServiceException {

        List<StockQuote> localQuotes = new ArrayList<>();

        for (HistoricalQuote historicalQuote : histQuote) {
            if (historicalQuote.getSymbol() == null) {
                throw new StockServiceException("Stock symbol " + historicalQuote.getSymbol() + " does not exist. " +
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

        for (HistoricalQuote quote : histQuote) {
            localQuotes.add(new StockQuote(quote.getSymbol(), quote.getDate(), quote.getClose()));
        }

        return localQuotes;
    }

    /**
     * Private method that adds 23 hours and 59 minutes to the date passed in.
     * The date passed in has a time of 00:00:00.
     * This method helps ensure StockQuote list sorting proceeds to the end of a given day
     *
     * @param calendar
     */
    private void addTimeHelper (Calendar calendar) {
        calendar.add(Calendar.HOUR_OF_DAY, 23);
        calendar.add(Calendar.MINUTE, 59);
    }

    /**
     * Private method that converts Calendar objects to java.sql.Timestamp objects
     *
     * @param date
     * @return sqlDate a java.sql.Timestamp object, converted from a Calendar object
     */
    private java.sql.Timestamp calendarToSQLHelper (Calendar date) {
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTimeInMillis());
        return sqlDate;
    }

    /**
     * Create a new StockQuote object from the Yahoo Finance API
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
                throw new StockServiceException("Stock symbol " + yahooStock.getQuote().getSymbol() + " does not exist. " +
                        "Please select another stock symbol.");
            } if (yahooStock.getQuote().getLastTradeTime() == null) {
                throw new StockServiceException("Date " + yahooStock.getQuote().getLastTradeTime().toString() + " is not valid. " +
                        "Please select another date.");
            } if (yahooStock.getQuote().getPrice() == null) {
                throw new StockServiceException("Price not found. Check if stock symbol " +
                        "or date are invalid.");
            }
        } catch (IOException e) {
            throw new StockServiceException("Problem with file input or output for getting stock quote", e);
        }

        return stockQuote;

    }

    /**
     * Get a list of stock prices for the date range specified
     * from the Yahoo Finance API
     *
     * @param symbol the stock symbol to get the price for
     * @param from   the first day to get the price for
     * @param until  the last day to get the price for
     * @return a list of <CODE>StockQuote</CODE> instances - one for each day specified
     * in the date range provided.
     */
    public List<StockQuote> getQuote(@NotNull String symbol, @NotNull Calendar from,
                                     @NotNull Calendar until) throws StockServiceException {

        //This is a list of locally defined stocks (not the Yahoo Finance API)
        List<StockQuote> listOfLocalStocks = new ArrayList<StockQuote>();

        try {
            Stock stock = YahooFinance.get(symbol);

            /*Yahoo finance "Interval" is set to daily here, as the API will not return a range of stocks
              without an interval present.
             */
            List<HistoricalQuote> stockHistoricalQuote = stock.getHistory(from, until, Interval.DAILY);
            listOfLocalStocks = stockQuoteHelper(stockHistoricalQuote);

        } catch (IOException e) {
            throw new StockServiceException("Problem with file input or output for getting stock quote", e);
        }

        return listOfLocalStocks;
    }

    /**
     * Get a list of stock prices for the date range specified and returned at the
     * specified interval from the Yahoo Finance API
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
                                     @NotNull Calendar until, @NotNull IntervalEnum interval) throws StockServiceException {

        //This is a list of locally defined stocks (not the Yahoo Finance API)
        List<StockQuote> listOfLocalStocks = new ArrayList<StockQuote>();

        yahoofinance.histquotes.Interval yahooQuoteInterval = null;

        /* Convert Interval ENUM to Yahoo Finance interval.
           There is no "HOURLY" interval in the API, so convert to DAILY if received.
           Default yahoo finance interval is DAILY.
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

            List<HistoricalQuote> stockHistoricalQuote = stock.getHistory(from, until, yahooQuoteInterval);
            listOfLocalStocks = stockQuoteHelper(stockHistoricalQuote);

        } catch (IOException e) {
            throw new StockServiceException("Problem with file input or output for getting stock quote", e);
        }

        return listOfLocalStocks;

    }

    /**
     * Create a new StockQuote object from the database
     *
     * @param symbol the stock symbol for a company
     * @return a hard-coded dummy instance of a StockQuote object,
     * based on the stockSymbol provided.
     * e.g., for "APPL", return APPL 100.25 09/13/2018
     */
    public StockQuote getDatabaseQuote(@NotNull String symbol, @NotNull Calendar date)
            throws StockServiceException, DatabaseInitializationException {

        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);

        List<StockQuote> stockQuotes = new ArrayList<>();

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            //Use a dummy date range to get a stock quote
            String queryString = "select * from quotes where symbol = '" + symbol + "';";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());

            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                java.sql.Timestamp sqlDate = resultSet.getTimestamp("quote_time");
                BigDecimal price = resultSet.getBigDecimal("price");

                //Convert java.sql.Timestamp to Calendar
                Calendar calendarForStockQuote = Calendar.getInstance();
                calendarForStockQuote.setTimeInMillis(sqlDate.getTime());

                stockQuotes.add(new StockQuote(symbolValue, date, price));
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException("Problem with either the database connection or " +
                    "SQL database itself.", exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for: " + symbol);
        }

        return stockQuotes.get(0);

    }

    /**
     * Get a list of stock prices for the date range specified
     * from the databaseAPI
     *
     * @param symbol the stock symbol to get the price for
     * @param from   the first day to get the price for
     * @param until  the last day to get the price for
     * @return a list of <CODE>StockQuote</CODE> instances - one for each day specified
     * in the date range provided.
     */
    public List<StockQuote> getDatabaseQuote(@NotNull String symbol, @NotNull Calendar from,
                                     @NotNull Calendar until)
            throws StockServiceException, DatabaseInitializationException {

        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);

        List<StockQuote> anotherStockQuoteList = new ArrayList<>();

        //Add hours and minutes to Calendar objects to ensure correct processing
        addTimeHelper(until);

        //Convert Calendar date parameters to java.sql dates with timestamps
        java.sql.Timestamp sqlFromDate = calendarToSQLHelper(from);
        java.sql.Timestamp sqlUntilDate = calendarToSQLHelper(until);

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            //MySQL statement to fetch data from the database
            String queryString = "select distinct symbol, quote_time, price from quotes where symbol = '" + symbol +
                    "' and quote_time between '" + sqlFromDate +
                    "' and '" + sqlUntilDate +
                    "' order by quote_time asc;";

            ResultSet rs = statement.executeQuery(queryString);
            anotherStockQuoteList = new ArrayList<>(rs.getFetchSize());

            while ((rs.next())) {
                String symbolValue = rs.getString("symbol");
                java.sql.Timestamp date = rs.getTimestamp("quote_time");
                BigDecimal price = rs.getBigDecimal("price");

                //Convert java.sql.Timestamp to Calendar
                Calendar calendarForStockQuote = Calendar.getInstance();
                calendarForStockQuote.setTimeInMillis(date.getTime());

                if (symbolValue.equals(symbol)) {
                    anotherStockQuoteList.add(new StockQuote(symbolValue, calendarForStockQuote, price));
                }
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException("Problem with either the database connection or " +
                    "SQL database itself.", exception);
        }

        if (anotherStockQuoteList.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }

        return anotherStockQuoteList;
    }

    /**
     * Get a list of stock prices for the date range specified and returned at the
     * specified interval from the database
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
    public List<StockQuote> getDatabaseQuote (@NotNull String symbol, @NotNull Calendar from,
                                     @NotNull Calendar until, @NotNull IntervalEnum interval)
            throws StockServiceException, DatabaseInitializationException {

        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);

        List<StockQuote> thirdStockQuoteList = new ArrayList<>();
        IntervalEnum chosenInterval = interval;

        //Add hours and minutes to Calendar objects to ensure correct processing
        addTimeHelper(until);

        //Convert Calendar date parameters to java.sql dates with timestamps
        java.sql.Timestamp sqlFromDate = calendarToSQLHelper(from);
        java.sql.Timestamp sqlUntilDate = calendarToSQLHelper(until);

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            //MySQL statement to fetch data from the database
            String queryString = "select distinct symbol, quote_time, price from quotes where symbol = '" + symbol +
                    "' and quote_time between '" + sqlFromDate +
                    "' and '" + sqlUntilDate +
                    "' order by quote_time asc;";


            ResultSet rs = statement.executeQuery(queryString);
            thirdStockQuoteList = new ArrayList<>(rs.getFetchSize());

            /*If a stock quote has the same date and hour as another stock quote,
              then they occur within the same hour and only one will be added.
             */
            if (chosenInterval == IntervalEnum.HOURLY) {
                while ((rs.next())) {
                    String symbolValue = rs.getString("symbol");
                    java.sql.Timestamp date = rs.getTimestamp("quote_time");
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
                        thirdStockQuoteList.add(new StockQuote(symbolValue, databaseDate, price));
                    }

                    for (int i = 0; i < thirdStockQuoteList.size(); i++) {

                        //Get the day and hour of the date retrieved from the database
                        int day = databaseDate.get(Calendar.DAY_OF_MONTH);
                        int hour = databaseDate.get(Calendar.HOUR_OF_DAY);

                        //Get the date of "this" stock quote from the list
                        Calendar listCal = thirdStockQuoteList.get(i).getDateRecorded();

                        /*If the day and hour match another stock quote, do not
                          add it to the list. Otherwise, add it to the list.
                         */
                        if ( (day == listCal.get(Calendar.DAY_OF_MONTH))  &&
                                (hour == listCal.get(Calendar.HOUR_OF_DAY)) ) {
                        } else {
                            thirdStockQuoteList.add(new StockQuote(symbolValue, listCal, price));
                            break;
                        }

                        /*Clear the Calendar object retrieved from the list in order
                          to prep for the next for loop iteration
                         */
                        listCal.clear();
                    }

                    /*Clear the Calendar object retrieved from the database in order
                          to prep for the next while loop iteration
                         */
                    databaseDate.clear();
                }
            }

            if (chosenInterval == IntervalEnum.DAILY) {
                while (rs.next()) {
                    String symbolValue = rs.getString("symbol");
                    java.sql.Timestamp date = rs.getTimestamp("quote_time");
                    BigDecimal price = rs.getBigDecimal("price");

                    //Convert java.sql.Timestamp to Calendar
                    Calendar calendarForStockQuote = Calendar.getInstance();
                    calendarForStockQuote.setTimeInMillis(date.getTime());

                    thirdStockQuoteList.add(new StockQuote(symbolValue, calendarForStockQuote, price));
                }
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException("Problem with either the database connection or " +
                    "SQL database itself.", exception);
        }

        if (thirdStockQuoteList.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }

        return thirdStockQuoteList;

    }

}