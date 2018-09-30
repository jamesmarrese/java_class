package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.ui.StockQuoteApplication;
import org.junit.Test;

import java.text.ParseException;

/**
 * This TestStockQuoteApplication class performs tests on the
 * main method found in the StockQuoteApplication class.
 *
 * @author  James Marrese
 */

public class TestStockQuoteApplication {

    /**
     * Tests whether main method throws NullPointerException
     * when passed args of null.
     */
   @Test(expected = NullPointerException.class)

    public void testMainNegative() throws ParseException {
        StockQuoteApplication.main(null);
    }

    /**
     * Tests whether main method returns expected output when
     * passed valid args, including  an Interval enum value.
     */
    @Test
    public void testMainShouldReturnValidResultTestingIntervalEnum () throws ParseException {

        String[] argString = new String [4];
        argString[0] = "APPL";
        argString[1] = "09/21/2018";
        argString[2] = "09/26/2018";
        argString[3] = "HOURLY";

        StockQuoteApplication.main(argString);
    }

}
