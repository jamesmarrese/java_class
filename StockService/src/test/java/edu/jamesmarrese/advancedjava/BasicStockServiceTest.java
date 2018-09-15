package edu.jamesmarrese.advancedjava;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicStockServiceTest {

    /*@Test
    public void testBasicStockServiceShouldReturnTrue () {

        /**
         * @return the hard-coded StockQuote instance created in the BasicStockService
         * class. Has fields "APPL", 100.43, and a date of 2019/09/13 11:34:34
         */
        //Create a date object for use in the StockQuote
      /*  DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("APPL", 100.43, date);

        BasicStockService newStock = new BasicStockService();

        assertTrue("The stocks are the same",
                stockQuote.equals(newStock) );
    }*/

    @Test
    public void testBasicStockServiceShouldReturnFalse () {

        /**
         * @return the hard-coded StockQuote instance created in the BasicStockService
         * class. Has fields "Apple", 100.43, and a date of 2019/09/13 11:34:34
         */
        //Create a date object for use in the StockQuote
        DateFormat dateFormat = new SimpleDateFormat("2019/09/13 11:34:34");
        Date date = new Date();
        dateFormat.format(date);

        StockQuote stockQuote = new StockQuote("AMZN", 100.25, date);

        BasicStockService newStock = new BasicStockService();

        assertFalse("The stocks are different",
                stockQuote.equals(newStock));
    }

}
