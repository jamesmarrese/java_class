package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.StockQuoteSearch;
import edu.jamesmarrese.advancedjava.service.StockServiceException;
import edu.jamesmarrese.advancedjava.servlet.StockQuoteServlet;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for StockQuoteServlet class
 */
public class TestStockQuoteServlet {

    private static final String STOCK_SYMBOL_PARAMETER_KEY = "stockSymbol";
    private static final String BEGIN_DATE_PARAMETER_KEY = "beginDate";
    private static final String END_DATE_PARAMETER_KEY = "endDate";
    private static final String INTERVAL_PARAMETER_KEY = "interval";

    private static HttpServletRequest request;
    private static HttpServletResponse response;

    private static StockQuoteServlet servlet;

    /**
     * Use mocks for the server request and response
     *
     * @throws IOException
     * @throws ServletException
     */
    @Before
    public void setUp() throws IOException, ServletException {

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        when(request.getParameter("stockSymbol")).thenReturn("AMZN");
        when(request.getParameter("beginDate")).thenReturn("2018-10-17");
        when(request.getParameter("endDate")).thenReturn("2018-10-19");
        when(request.getParameter("interval")).thenReturn("DAILY");

        String stockSymbol = request.getParameter(STOCK_SYMBOL_PARAMETER_KEY);
        String beginDate = request.getParameter(BEGIN_DATE_PARAMETER_KEY);
        String endDate = request.getParameter(END_DATE_PARAMETER_KEY);
        String interval = request.getParameter(INTERVAL_PARAMETER_KEY);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(servletContext.getRequestDispatcher("/stockquoteResults.jsp")).thenReturn(requestDispatcher);
        requestDispatcher.forward(request, response);

        /* This commented out code was causing CircleCI to fail, which doesn't make any sense, as I have a test
           in another class that tests the main method and calls the Yahoo Finance API. That test passes in
           CircleCI. The commented out code below is where CircleCI appears to be failing, during the call to
           the

         */
        StockQuoteSearch stockQuoteSearch = new StockQuoteSearch(stockSymbol, beginDate, endDate, interval);

        try {
            stockQuoteSearch.getStockData();
        } catch (StockServiceException e) {
            throw new RuntimeException(e.getMessage());
        }

        session.setAttribute("stockQuoteSearch", stockQuoteSearch);

    }

    @Test
    public void testStockQuoteSearchServletGetParameterUsingString() {
        assertTrue("String stockSymbol should be AMZN", request.getParameter("stockSymbol").equals("AMZN"));
        assertTrue("String beginDate should be 2018-10-17", request.getParameter("beginDate").equals("2018-10-17"));
        assertTrue("String endDate should be 2018-10-19", request.getParameter("endDate").equals("2018-10-19"));
        assertTrue("String interval should be DAILY", request.getParameter("interval").equals("DAILY"));
    }

    @Test
    public void testStockQuoteSearchServletGetParameterUsingParameterKey() {
        assertTrue("STOCK_SYMBOL_PARAMETER_KEY should be AMZN", request.getParameter(STOCK_SYMBOL_PARAMETER_KEY).equals("AMZN"));
        assertTrue("BEGIN_DATE_PARAMETER_KEY should be 2018-10-17", request.getParameter(BEGIN_DATE_PARAMETER_KEY).equals("2018-10-17"));
        assertTrue("END_DATE_PARAMETER_KEY should be 2018-10-19", request.getParameter(END_DATE_PARAMETER_KEY).equals("2018-10-19"));
        assertTrue("INTERVAL_PARAMETER_KEY should be DAILY", request.getParameter(INTERVAL_PARAMETER_KEY).equals("DAILY"));
    }

    @Test
    public void testStockQuoteSearchServletRequestAndResponse() {
        assertTrue("Request is not null", request != null);
        assertTrue("Response is not null", response != null);
    }

}
