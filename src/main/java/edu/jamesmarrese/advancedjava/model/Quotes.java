package edu.jamesmarrese.advancedjava.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Models the Quotes table
 */

@Entity
@Table(name="quotes", catalog = "stocks")

public class Quotes {

    private int ID;
    private BigDecimal price;
    private String symbol;
    private Timestamp time;

    /**
     * Primary Key - Unique ID for a particular row in the quotes table.
     *
     * @return an integer value
     */
    @Id
    @Column(name = "quotes_ID", nullable = false, insertable = true, updatable = true)
    public int getID() { return ID; }

    /**
     * Set the unique ID for a particular row in the quotes table.
     *
     * @param ID a unique value.
     */
    public void setID (int ID) {this.ID = ID;}

    /**
     * @return the quote's price
     */
    @Id
    @Column(name = "price", nullable = false, insertable = true, updatable = true)
    public BigDecimal getPrice() {return price;}

    /**
     * Specify the quote's price
     * @param price a BigDecimal value
     */
    public void setPrice (BigDecimal price) {this.price = price;}

    /**
     * @return the quote's symbol
     */
    @Id
    @Column(name = "symbol", nullable = false, insertable = true, updatable = true)
    public String getSymbol() {return symbol;}

    /**
     * Specify the quote's symbol
     * @param symbol a String value, max 4 chars
     */
    public void setSymbol (String symbol) {this.symbol = symbol;}

    /**
     * @return the quote's timestamp
     */
    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true)
    public Timestamp getTime () {return time;}

    /**
     * Specify the quote's timestamp
     * @param time  the time the quote was inserted into the databse.
     */

    public void setTime (Timestamp time) {this.time = time;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quotes quote = (Quotes) o;

        if (ID != quote.getID()) return false;
        if (time != null ? !time.equals(quote.time) : quote.time != null)
            return false;
        if (symbol != null ? !symbol.equals(quote.symbol) : quote.symbol != null)
            return false;
        if (price != null ? !price.equals(quote.price) : quote.price != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Quotes{" +
                "quotes_ID=" + ID +
                ", price='" + price + '\'' +
                ", symbol='" + symbol + '\'' +
                ", time=" + time +
                '}';
    }





}
