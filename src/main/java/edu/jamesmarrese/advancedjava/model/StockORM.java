package edu.jamesmarrese.advancedjava.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Models the Stock table
 */

@Entity
@Table(name="stock", catalog = "stocks")
public class StockORM {

    private int stockID;
    private String symbol;
    private Timestamp stockTime;
    private BigDecimal price;

    /**
     * Empty constructor
     */
    public StockORM() {}

    /**
     * Primary Key - Unique ID for a particular row in the person table.
     *
     * @return an integer value
     */
    @Id
    @Column(name = "stock_ID", nullable = false, insertable = true, updatable = true)
    public int getStockID() {return stockID;}

    /**
     * Set the unique ID for a particular row in the person table.
     *
     * @param ID a unique value.
     */
    public void setStockID (int ID) {this.stockID = ID;}

    /**
     * @return the stock's symbol
     */
    @Basic
    @Column(name = "symbol", nullable = false, insertable = true, updatable = true)
    public String getSymbol() {return symbol;}

    /**
     * Specify the srtock's symbol
     * @param symbol a String value
     */
    public void setSymbol(String symbol) {this.symbol = symbol;}

    /**
     * @return the stock's time
     */
    @Basic
    @Column(name = "stock_time", nullable = false, insertable = true, updatable = true)
    public Timestamp getStockTime() {return stockTime;}

    /**
     * Specify the stock's symbol
     * @param stockTime a String value
     */
    public void setStockTime (Timestamp stockTime) {this.stockTime = stockTime;}

    /**
     * @return the stock's price
     */
    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true)
    public BigDecimal getPrice () {return price;}

    /**
     * Specify the stock's price
     * @param price a String value
     */
    public void setPrice (BigDecimal price) {this.price = price;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockORM stock = (StockORM) o;

        if (stockID != stock.stockID) return false;
        if (symbol != null ? !symbol.equals(stock.symbol) : stock.symbol != null)
            return false;
        if (stockTime != null ? !stockTime.equals(stock.stockTime) : stock.stockTime!= null)
            return false;
        if (price != null ? !price.equals(stock.price) : stock.price != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stockID;
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (stockTime != null ? stockTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StockORM{" +
                "stock_ID=" + stockID +
                ", symbol='" + symbol + '\'' +
                ", time='" + stockTime + '\'' +
                ", price=" + price +
                '}';
    }









}
