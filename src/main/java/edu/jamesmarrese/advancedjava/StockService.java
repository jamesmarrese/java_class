package edu.jamesmarrese.advancedjava;

/**
 * This StockService interface provides the interface for the StockQuote and
 * BasicStockService classes.
 *
 * @author  James Marrese
 */

public interface StockService {
    /**
     * Return the current price for a share of stock for the given symbol
     * @param symbol the stock symbol of the company you want a quote for
     *               e.g., APPL for Apple
     * @return a <CODE>StockQuote</CODE> instance consisting of
     *         a stockSymbol (String), stockPrice (double), and date (Date).
     */
    StockQuote getQuote(String symbol);
}
