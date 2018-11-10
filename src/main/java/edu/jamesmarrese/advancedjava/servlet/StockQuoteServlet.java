package edu.jamesmarrese.advancedjava.servlet;

import edu.jamesmarrese.advancedjava.model.StockQuoteSearch;
import edu.jamesmarrese.advancedjava.service.StockServiceException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

/**
 * Servlet for accessing form data
 */
public class StockQuoteServlet extends HttpServlet {

    private static final String STOCK_SYMBOL_PARAMETER_KEY = "stockSymbol";
    private static final String BEGIN_DATE_PARAMETER_KEY = "beginDate";
    private static final String END_DATE_PARAMETER_KEY = "endDate";
    private static final String INTERVAL_PARAMETER_KEY = "interval";

    /**
     * Request data from form submission in stockquote.jsp
     * Issue response to stockquoteResults.jsp
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String stockSymbol = request.getParameter(STOCK_SYMBOL_PARAMETER_KEY);
        String beginDate = request.getParameter(BEGIN_DATE_PARAMETER_KEY);
        String endDate = request.getParameter(END_DATE_PARAMETER_KEY);
        String interval = request.getParameter(INTERVAL_PARAMETER_KEY);

        HttpSession session = request.getSession();

        StockQuoteSearch stockQuoteSearch = new StockQuoteSearch(stockSymbol, beginDate, endDate, interval);

        try {
            stockQuoteSearch.getStockData(stockSymbol, beginDate, endDate, interval);
        } catch (StockServiceException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }

        session.setAttribute("stockQuoteSearch", stockQuoteSearch);

        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher("/stockquoteResults.jsp");
        dispatcher.forward(request, response);

    }

}
