<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 15/12/18
  Time: 16:44
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
    <a href="/panel/groups">Powrót</a>
    <c:if test="${empty group}">
        <h3>Dodaj grupę</h3>
        <form action="/panel/groups/save" method="post">
            <p>Nazwa: <input type="text" name="name" required/></p>
            <input type="submit" value="Zapisz"/>
        </form>
    </c:if>
    <c:if test="${not empty group}">
        <h3>Edytuj grupę</h3>
        <form action="/panel/groups/save" method="post">
            <p>Nazwa: <input type="text" name="name" placeholder="${group.name}" required/></p>
            <input type="hidden" name="id" value="${group.id}"/>
            <input type="submit" value="Zapisz"/>
        </form>
    </c:if>
</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>
