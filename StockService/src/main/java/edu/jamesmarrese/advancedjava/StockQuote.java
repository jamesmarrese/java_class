package edu.jamesmarrese.advancedjava;

/**
 * This StockQuote class creates StockQuote objects consisting of
 * a stockSymbol (String), stockPrice (double), and date (Date).
 *
 * @author  James Marrese
 */

import java.util.Date;

public class StockQuote {

    private String stockSymbol;
    private double stockPrice;
    private Date dateRecorded;

    /**
     * Create a new  StockQuote instance
     * @param stockSymbol the stock symbol, e.g. AMZN for Amazon
     * @param stockPrice the price of the stock for the provided date
     * @param dateRecorded  the date the stock info was recorded
     */

    //Constructor
    public StockQuote (String stockSymbol, double stockPrice, Date dateRecorded) {
        this.stockSymbol = stockSymbol;
        this.stockPrice = stockPrice;
        this.dateRecorded = dateRecorded;
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
