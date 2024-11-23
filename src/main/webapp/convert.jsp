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
<h1>Converting ${param.from} to ${param.to}</h1>
<p>
    ${param.amount} ${param.from} =
       <c:out value="${currencies.convert(param.amount , param.from, param.to)}" /> ${param.to};
</p>
</body>

</html>