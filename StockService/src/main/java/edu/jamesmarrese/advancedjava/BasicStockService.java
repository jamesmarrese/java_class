package edu.jamesmarrese.advancedjava;

/**
 * This BasicStockService class implements the StockService interface and
 * creates and returns a hard-coded StockQuote instance consisting of
 * a stockSymbol (String), stockPrice (double), and date (Date).
 *
 * @author  James Marrese
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicStockService implements StockService {

    /**
     * Create a new StockQuote object
     *
     * @param symbol the stock symbol for a company
     *
     * @return a hard-coded dummy instance of a StockQuote object,
     * based on the stockSymbol provided.
     * e.g., for "APPL", return Apple 100.25 2018/09/13 11:01:43
     */

    public StockQuote getQuote(String symbol) {

        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        return new StockQuote ("APPL", 100.43, date);
    }
}