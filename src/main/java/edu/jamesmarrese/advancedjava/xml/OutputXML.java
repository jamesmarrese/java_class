package edu.jamesmarrese.advancedjava.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Class for outputting in standard XML format.
 */

public class OutputXML {

    public void outputXML (Stocks stocks) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Stocks.class);
        Marshaller marshallerTwo = context.createMarshaller();

        marshallerTwo.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshallerTwo.marshal(stocks, System.out);
    }
}
