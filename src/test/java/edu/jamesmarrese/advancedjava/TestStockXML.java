package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.xml.Stock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestStockXML {

    public static final String symbol = "OTOW";
    public static final String price = "60.41";
    public static final String time = "2015-02-10 13:55:45";

    public static Stock createStock() {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setPrice(price);
        stock.setTime(time);
        return stock;
    }

    @Test
    public void testStockGettersAndSetters() {
        Stock stock = createStock();
        assertEquals("symbol matches", symbol, stock.getSymbol());
        assertEquals("price matches", price, stock.getPrice());
        assertEquals("time matches", time, stock.getTime());
    }

}
