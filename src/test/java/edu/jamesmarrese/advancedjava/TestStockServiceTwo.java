package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.StockService;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * This TestStockService class creates StockQuote objects that are created
 * from using the StockService interface. This test class uses mocks to test
 * the stockSymbol (String) and stockPrice (double) fields.
 *
 * @author  James Marrese
 */

public class TestStockServiceTwo {

    /**
     * Tests StockQuote instances using mocking to verify that the
     * correct stock symbols are returned.
     */

    @Test
    public void testStockServiceStockListSizeShouldBeFive () {

        String mockStockSymbol = "APPL";

        Calendar mockStartDate = new GregorianCalendar(2018,9,21);
        Calendar mockEndDate = new GregorianCalendar(2018,9,26);

        StockServiceFactory test = new StockServiceFactory();

        List<StockQuote> stockList = test.getQuote(mockStockSymbol, mockStartDate, mockEndDate);

        assertTrue("The length of the stock list should be 5",
                stockList.size() == 5);

    }

    /**
     * Tests StockQuote instances to verify that the date
     * returned in not null.
     */

    @Test
    public void testGetStockDate () {

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, Calendar.getInstance().getTime());

        Date testDate = stockQuote.getDateRecorded();

        assertNotNull("The date object is not null", testDate);
    }

}
