package edu.jamesmarrese.advancedjava;


import edu.jamesmarrese.advancedjava.model.StockQuoteSearch;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for StockQuoteSearch class
 */
public class TestStockQuoteSearch {

    private String stockSymbol = "AAPL";
    private String beginDate = "2018-01-01T00:00:00.000-04:00";
    private String endDate = "2018-01-01T00:00:00.000-04:00";
    private String interval = "DAILY";

    public StockQuoteSearch createStockQuoteSearch() {
        StockQuoteSearch stockQuoteSearch = new StockQuoteSearch(stockSymbol, beginDate, endDate, interval);
        return stockQuoteSearch;
    }

    /**
     * Test getters and setters
     */
    @Test
    public void testStockQuoteSearchGettersAndSetters() {
        StockQuoteSearch search = createStockQuoteSearch();

        assertEquals("stock symbol matches", stockSymbol, search.getStockSymbol());
        assertEquals("begin date matches", beginDate, search.getBeginDate());
        assertEquals("end date matches", endDate, search.getEndDate());
        assertEquals("interval matches", interval, search.getInterval());

    }

}
