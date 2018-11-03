<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="stockQuoteSearch" class="edu.jamesmarrese.advancedjava.servlet.StockQuoteServlet" scope="session">
    <c:set target='${stockQuoteSearch}'  value='${sessionScope.get("stockQuoteSearch")}'/>
</jsp:useBean>

<html>
<head>
    <title>Stock Quote Results</title>
</head>
<body>

<c:out value="${stockQuoteSearch}"/>

</body>
</html>