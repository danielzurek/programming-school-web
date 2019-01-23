<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 15/12/18
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Szkoła Programowania</title>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/theme.css">
</head>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>

<div class="main">
    <a href="${back}">Powrót</a>
    <h3>Szczegóły rozwiązania zadania</h3>
    <textarea cols="80" rows="24" readonly>${solution}</textarea>
</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>