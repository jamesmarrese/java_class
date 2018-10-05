package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This TestStockService class creates StockQuote objects that are created
 * from using the StockService interface. This test class uses mocks to test
 * the stockSymbol (String) and stockPrice (BigDecimal) fields.
 *
 * @author  James Marrese
 */

public class TestStockService {

    /**
     * Tests StockQuote instances to verify that the
     * correct stock symbols are returned.
     */

    @Test
    public void testStockServiceStockListSizeShouldBeFive () {

        String mockStockSymbol = "APPL";

        Calendar mockStartDate = new GregorianCalendar(2018,9,21);
        Calendar mockEndDate = new GregorianCalendar(2018,9,26);

        StockServiceFactory test = new StockServiceFactory();

        List<StockQuote> stockList = test.getQuote(mockStockSymbol, mockStartDate, mockEndDate);

        assertTrue("The length of the stock list should be 6",
                stockList.size() == 6);

    }

    /**
     * Tests StockQuote instances to verify that the date
     * returned is not null.
     */

    @Test
    public void testGetStockDate () {

        //Create a calendar object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("09/13/2018");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", new BigDecimal(100.25), date);

        Date testDate = stockQuote.getDateRecorded();

        assertNotNull("The date object is not null", testDate);
    }

}
