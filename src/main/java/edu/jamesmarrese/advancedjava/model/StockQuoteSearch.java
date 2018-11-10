package edu.jamesmarrese.advancedjava.model;

import edu.jamesmarrese.advancedjava.service.IntervalEnum;
import edu.jamesmarrese.advancedjava.service.StockServiceException;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * This class used for processing form data.
 *
 */

public class StockQuoteSearch {

    private String stockSymbol;
    private DateTime beginDate; //using joda time to simplify later conversion to String and Calendar types
    private DateTime endDate;   //using joda time to simplify later conversion to String and Calendar types
    private IntervalEnum interval;
    private String stockQuoteString;

    /**
     * Default constructor
     */
    public StockQuoteSearch() {}

    /**
     * Create new StockQuoteSearch instance
     *
     * @param stockSymbol the stock symbol, entered by the user on the form
     * @param beginDate   the start date for the stock search, entered by the user on the form
     * @param endDate     the end date for the stock search, entered by the user on the form
     * @param interval    the frequency with which stock quotes are to be retrieved
     */
    public StockQuoteSearch(String stockSymbol, String beginDate, String endDate, String interval) {
        setStockSymbol(stockSymbol);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setInterval(interval);
    }

    /**
     * Getter for stock symbol
     *
     * @return stockSymbol a string for the stock symbol
     */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     * Setter for the stockSymbol
     *
     * @param stockSymbol a string for the stock symbol
     */
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    /**
     * Getter for the begin date
     *
     * @return beginDate a string for the start date in the stock quote search
     */
    public String getBeginDate() {
        return beginDate.toString();
    }

    /**
     * Setter for the begin date
     *
     * @param beginDate a string for the begin date in the stock quote search
     */
    public void setBeginDate(String beginDate) {
        this.beginDate = DateTime.parse(beginDate);
    }

    /**
     * Getter for the end date
     *
     * @return endDate a string for the end date in the stock quote search
     */
    public String getEndDate() {
        return endDate.toString();
    }

    /**
     * Setter for the end date
     *
     * @param endDate a string for the end date in the stock quote search
     */
    public void setEndDate(String endDate) {
        this.endDate = DateTime.parse(endDate);
    }

    /**
     * Getter for the interval
     *
     * @return interval a string for the frequency with which stock quotes will be returned
     */
    public String getInterval() {
        return interval.toString();
    }

    /**
     * Setter for the interval
     *
     * @param interval a string for the frequency with which stock quotes will be returned
     */
    public IntervalEnum setInterval(String interval) {

        String intervalUpper = interval.toUpperCase();

        if (intervalUpper.startsWith("DAILY")) {
            this.interval = IntervalEnum.DAILY;
        } else if (intervalUpper.startsWith("WEEKLY")) {
            this.interval = IntervalEnum.WEEKLY;
        } else {
            this.interval = IntervalEnum.DAILY; //default to DAILY
        }

        return this.interval;
    }

    /**
     * Get a list of stock quotes and append to string builder
     *
     * @throws StockServiceException
     * @throws ParseException
     */
    public String getStockData(String symbol, String startOn, String endOn, String interval)
            throws StockServiceException, ParseException {

        IntervalEnum frequency = setInterval(interval);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        //Convert String to Calendar object
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(simpleDateFormat.parse(startOn));

        //Convert String to Calendar object
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(simpleDateFormat.parse(endOn));

        List<StockQuote> stockList = null;

        //Get the list of stock quotes and add them to the string builder
        StockServiceFactory stockServiceFactory = new StockServiceFactory();
        stockList = stockServiceFactory.getQuote(symbol, startCalendar, endCalendar, frequency);
        StringBuilder builder = new StringBuilder();

        for (StockQuote q : stockList) {
            builder.append(q.toString());
        }

        this.stockQuoteString = builder.toString();

        return this.stockQuoteString;

    }

}
