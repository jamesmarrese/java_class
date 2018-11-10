package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockORM;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Tests for the Stock ORM class
 */

public class TestStockORM {

    public  static final Calendar time = Calendar.getInstance();

    static {
        time.set(2015, Calendar.OCTOBER, 18);
    }

    public static final String symbol = "ABCD";
    public static final BigDecimal price = new BigDecimal(123.45);
    public static final Timestamp timestamp = new Timestamp(time.getTimeInMillis());

    /**
     * Testing helper method for generating Stock test data
     *
     * @return a Stock ORM object that uses static constants for data.
     */
    public static StockORM createStockORM() {
        StockORM stockORM = new StockORM();
        stockORM.setSymbol(symbol);
        stockORM.setStockTime(timestamp);
        stockORM.setPrice(price);
        return stockORM;
    }

    @Test
    public void testStockORMGettersAndSetters() {
        StockORM stockORM = createStockORM();
        int id = 10;
        stockORM.setStockID(id);
        assertEquals("symbol matches", symbol, stockORM.getSymbol());
        assertEquals("time matches", timestamp, stockORM.getStockTime());
        assertEquals("price matches", price, stockORM.getPrice());
        assertEquals("id matches", id, stockORM.getStockID());
        assertTrue("the toString method works", stockORM.toString().equals(stockORM.toString()));
    }

}
