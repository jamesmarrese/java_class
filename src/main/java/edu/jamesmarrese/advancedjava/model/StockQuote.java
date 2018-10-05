package edu.jamesmarrese.advancedjava.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * This StockQuote class creates StockQuote instances consisting of
 * a stockSymbol (String), stockPrice (double), and date (Calendar).
 *
 * @author  James Marrese
 */

@Immutable
public class StockQuote {

    private String stockSymbol;
    private BigDecimal stockPrice;
    private Date dateRecorded;

    /**
     * Create a new StockQuote instance
     * @param stockName the stock symbol, e.g. AMZN for Amazon
     * @param stockValue the price of the stock for the provided date
     * @param date  the date the stock info was recorded
     */

    @NotNull
    public StockQuote (String stockName, BigDecimal stockValue, Date date) {
        this.stockSymbol = stockName;
        this.stockPrice = stockValue;
        this.dateRecorded = date;
    }

    /**
     * @return the symbol that represents the company whose stock this is.
     * e.g. AMZN for Amazon
     */
    @NotNull
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     * @return The price of one share of stock.
     */
    @NotNull
    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    /**
     * @return the date of the stock price.
     */
    @NotNull
    public Date getDateRecorded() {
        return dateRecorded;
    }

    @Override
    /**
     * @return the StockQuote object as a readable String
     */
    public String toString () {
        return getStockSymbol() + " " + getStockPrice() + " " + getDateRecorded();
    }
}
