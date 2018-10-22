package edu.jamesmarrese.advancedjava.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="symbol" use="optional" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="price" use="optional" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="time" use="optional" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "symbol",
        "price",
        "time"
})

@XmlRootElement(name = "stock")
public class Stock implements XMLDomainObject {

    @XmlAttribute(name = "symbol", required = false)
    @XmlSchemaType(name = "anySimpleType")
    protected String symbol;

    @XmlAttribute(name = "time", required = false)
    @XmlSchemaType(name = "anySimpleType")
    protected String time;

    @XmlAttribute(name = "price", required = false)
    @XmlSchemaType(name = "anySimpleType")
    protected String price;

    /**
     * Gets the value of the symbol property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSymbol() {return symbol;}

    /**
     * Sets the value of the symbol property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSymbol(String value) {this.symbol = value;}

    /**
     * Gets the value of the time property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTime() {return time;}

    /**
     * Sets the value of the time property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTime(String value) {this.time = value;}

    /**
     * Gets the value of the price property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPrice() {return price;}

    /**
     * Sets the value of the price property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPrice(String value) {this.price = value;}

    @Override
    public String toString() {
        return "Stock{" +
                ", symbol='" + symbol + '\'' +
                ", time='" + time + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}
