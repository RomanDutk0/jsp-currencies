<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" %>

<jsp:useBean id="currencies" class="com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" scope="request"/>


<html>

<head>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Exchange Rates</title>
</head>
<body>
<h1>Exchange Rates for ${param.from}</h1>

<ul>
    <c:forEach items="${currencies.getExchangeRates(param.from)}" var="rate" varStatus="loop">
        <li>${loop.count} ${param.from} = ${rate.value} ${rate.key}</li>
    </c:forEach>
</ul>
</body>

</html>