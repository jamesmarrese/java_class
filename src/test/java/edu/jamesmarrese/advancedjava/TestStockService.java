package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuote;
import edu.jamesmarrese.advancedjava.service.StockServiceException;
import edu.jamesmarrese.advancedjava.service.StockServiceFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public void testStockServiceStockListSizeShouldBeFive ()
            throws ParseException, StockServiceException {

        String mockStockSymbol = "GOOG";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //Set the begin date for the list of stock quotes
        Date beginDate = format.parse("2018-09-21");
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(beginDate);

        //Set the end date for the list of stock quotes
        Date stopDate = format.parse("2018-09-27");
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(stopDate);

        StockServiceFactory test = new StockServiceFactory();

        List<StockQuote> stockList = test.getQuote(mockStockSymbol, startDate, endDate);

        assertTrue("The length of the stock list should be 13",
                stockList.size() == 13);

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

        StockQuote stockQuote = new StockQuote("GOOG", date, new BigDecimal(100.25));

        Date testDate = stockQuote.getDateRecorded();

        assertNotNull("The date object is not null", testDate);
    }

}