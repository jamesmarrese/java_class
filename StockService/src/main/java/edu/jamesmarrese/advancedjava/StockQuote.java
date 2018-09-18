package edu.jamesmarrese.advancedjava;

import java.util.Date;

/**
 * This StockQuote class creates StockQuote instances consisting of
 * a stockSymbol (String), stockPrice (double), and date (Date).
 *
 * @author  James Marrese
 */

public class StockQuote {

    private String stockSymbol;
    private double stockPrice;
    private Date dateRecorded;

    /**
     * Create a new StockQuote instance
     * @param stockSymbol the stock symbol, e.g. AMZN for Amazon
     * @param stockPrice the price of the stock for the provided date
     * @param dateRecorded  the date the stock info was recorded
     */

    public StockQuote (String stockName, double stockValue, Date date) {
        this.stockSymbol = stockName;
        this.stockPrice = stockValue;
        this.dateRecorded = date;
    }

    /**
     * @return the symbol that represents the company whose stock this is.
     * e.g. AMZN for Amazon
     */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     * @return The price of one share of stock.
     */
    public double getStockPrice() {
        return stockPrice;
    }

    /**
     * @return the date of the stock price.
     */
    public Date getDateRecorded() {
        return dateRecorded;
    }
}
