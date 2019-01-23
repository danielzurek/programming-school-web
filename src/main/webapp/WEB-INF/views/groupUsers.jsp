<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 15/12/18
  Time: 15:15
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
    <a href="/groups">Powrót</a>
    <c:if test="${empty users}">
        <h3>Brak użytkowników</h3>
    </c:if>
    <c:if test="${not empty users}">
        <h3>Lista użytkowników grupy: ${group.name}</h3>
        <table>
            <tr>
                <th>Nazwa</th>
                <th>Akcje</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.email}</td>
                    <td><a href="/user?id=${user.id}">Szczegóły</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>