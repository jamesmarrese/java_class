package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.util.DatabaseConnectionException;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;
import edu.jamesmarrese.advancedjava.xml.Stock;
import edu.jamesmarrese.advancedjava.xml.Stocks;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class XMLDataPersistence {

    public List<Stock> xmlParsing() throws javax.xml.bind.JAXBException {

        String xmlStocks = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<stocks>\n" +
                "    <stock symbol=\"OTOW\" price=\"60.41\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"VNET\" price=\"110.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"AGTK\" price=\"120.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"AKAM\" price=\"3.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"AOL\" price=\"30.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"BCOM\" price=\"10.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"BIDU\" price=\"10.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"BCOR\" price=\"12.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"WIFI\" price=\"16.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"BRNW\" price=\"0.70\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"CARB\" price=\"9.80\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"JRJC\" price=\"111.11\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"CCIH\" price=\"22.20\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"CHIC\" price=\"4.30\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"CNV\" price=\"13.43\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"CCOI\" price=\"1.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"CNCG\" price=\".10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"CXDO\" price=\"90.00\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"CRWG\" price=\"52.99\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"ELNK\" price=\"45.40\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"EATR\" price=\"15.60\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"EDXC\" price=\"18.40\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"ENV\" price=\"220.61\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"EPAZ\" price=\"101.11\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"FB\" price=\"500.17\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"FDIT\" price=\"160.90\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"FLPC\" price=\"177.70\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"GCLT\" price=\"8.90\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"GOOG\" price=\"700.10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"GOOG\" price=\".10\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"GREZ\" price=\"77.91\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"IACI\" price=\"40.52\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"ICOA\" price=\"48.30\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"IIJI\" price=\"32.80\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"ILIA\" price=\"188.22\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"INAP\" price=\"2.12\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"IPAS\" price=\"1.02\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"JCOM\" price=\"19.99\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"LOGL\" price=\"18.21\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"LLNW\" price=\"45.55\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"LOOK\" price=\"38.90\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"MEET\" price=\"21.27\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"MEET\" price=\"310.31\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"VOIS\" price=\"440.51\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"MOMO\" price=\"8.51\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"NETE\" price=\"13.16\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"NTES\" price=\"14.23\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"EGOV\" price=\"17.35\" time=\"2015-02-10 00:00:01\"/>\n" +
                "    <stock symbol=\"NQ\" price=\"110.77\" time=\"2015-02-10 00:00:01\"/>\n" +
                "</stocks>";

        JAXBContext jaxbContext = JAXBContext.newInstance(Stocks.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Stocks stocks = (Stocks) unmarshaller.unmarshal(new StringReader(xmlStocks));
        System.out.println(stocks.toString());

        JAXBContext context = JAXBContext.newInstance(Stocks.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(stocks, System.out);

        return (List<Stock>) stocks;

    }

    public void persistXMLDataToDatabase (List<Stock> stocks) throws javax.xml.bind.JAXBException,
            StockServiceException {

        stocks = xmlParsing();

        List<Stock> stockList = stocks;

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            //MySQL statement to insert data from XML file into database
            for (int i = 0; i <= numberOfXMLNodes(); i++) {
                String queryString = "INSERT INTO stock (symbol, stock_time, price) VALUES (" +
                        "'" + stockList.get(i).getSymbol() + "', " +
                        "'" + stockList.get(i).getTime() + "', " +
                        stockList.get(i).getPrice() + ");";

                ResultSet rs = statement.executeQuery(queryString);
                stockList = new ArrayList<>(rs.getFetchSize());
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }

        if (stockList.isEmpty()) {
            throw new StockServiceException("There is no stock data for: " + stockList.get(0).getSymbol());
        }

    }

    /**
     * Get the number of nodes in the XML file
     * @return numberOfNodes, an int value describing the number of nodes
     *         in the XML file being read.
     */
    public static int numberOfXMLNodes() {

        int numberOfNodes = 0;

        try {
            String filepath = "./src/main/java/resources/xml/stock_info.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            NodeList list = doc.getElementsByTagName("stock");

            numberOfNodes = list.getLength();

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (org.xml.sax.SAXException sae) {
            sae.printStackTrace();
        }

        return numberOfNodes;

    }

}
