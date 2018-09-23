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
     * passed valid args.
     */
    @Test
    public void testMainShouldReturnValidResult() throws ParseException {

        String[] argString = new String [3];
        argString[0] = "APPL";
        argString[1] = "09/21/2018";
        argString[2] = "09/26/2018";

        StockQuoteApplication.main(argString);
    }

}
