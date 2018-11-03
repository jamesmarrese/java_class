<%@ page language="java" contentType="test/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<jsp:useBean id="stockQuoteSearch" class="edu.jamesmarrese.advancedjava.model.StockQuoteSearch" scope="request"/>
<jsp:setProperty name="stockQuoteSearch" property="*"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Stock Quote Search</title>

</head>
<body>

<h2>
    Enter stock information. Date format must be YYYY-MM-DD<br>
</h2>

<P></P>

<form name="myform" action="servlet/StockQuoteServlet" method="post">
    Stock Symbol : <input type="text" name="stockSymbol" title="stock symbol"><br>
    Begin Date   : <input type="datetime-local" name="beginDate" title="begin date"><br>
    End Date     : <input type="datetime-local" name="endDate" title="end date"><br>
    Interval (DAILY or WEEKLY): <input type="text" name="interval" title="interval"><br>

    <input type="SUBMIT" value="OK">
    <input type="HIDDEN" name="submit" value="true">
</form>

</body>
</html>