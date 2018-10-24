package edu.jamesmarrese.advancedjava.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}stock"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "stock"
})

@XmlRootElement(name = "stocks")
public class Stocks implements XMLDomainObject {

    @XmlElement(required = true)
    protected List<Stock> stock;

    /**
     * Gets the value of the stock property.
     *
     * @return
     *     possible object is
     *     {@link Stock }
     *
     */
    public List<Stock> getStock() {
        if (stock == null) {
            stock = new ArrayList<Stock>();
        }
        return this.stock;
    }

    @Override
    public String toString() {

        return "Stocks{" +
                "stock=" + stock +
                "}";

        /*String output = "Stocks{";
        for (Stock stocks : stock) {
            output += "stock" + stocks;
        }
        output += '}';
        return output;*/
    }

}
