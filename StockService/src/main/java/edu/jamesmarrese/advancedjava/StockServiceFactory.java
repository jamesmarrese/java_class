/**
 * This StockServiceFactory class implements the StockService interface
 * and returns StockQuote instances. StockQuote instances consist of a
 * stock symbol (String), stock price (double), and date (Date).
 *
 * @author  James Marrese
 * @version 1.0
 * @since   2018-09-13
 */

package edu.jamesmarrese.advancedjava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockServiceFactory implements StockService {

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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        if (symbol == null)
            throw new NullPointerException();
        if (symbol.equals("APPL"))
            return new StockQuote("Apple", 100.25, date);
        if (symbol.equals("AMZN"))
            return new StockQuote("Amazon", 150.75, date);
        if (symbol.equals("NFLX"))
            return new StockQuote("Netflix", 201.54, date);
        return null;
    }

}