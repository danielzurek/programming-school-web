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
    <a href="/group?id=${user.groupId}">Powrót</a>
    <h3>Szczegóły użytkownika</h3>
    <p>Imię i nazwisko: <b>${user.firstName} ${user.lastName}</b></p>
    <p>Email: <b>${user.email}</b></p>
    <c:if test="${empty solutions}">
        <h4>Brak rozwiązanych zadań</h4>
    </c:if>
    <c:if test="${not empty solutions}">
        <h4>Dodane rozwiązania zadań</h4>
        <table>
            <tr>
                <th>Tytuł zadania</th>
                <th>Data dodania</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="solution" items="${solutions}">
                <tr>
                    <td>${solution.exercise.title}</td>
                    <td><fmt:formatDate value="${solution.updatedAt}" pattern="yyyy-MM-dd, HH:mm:ss" /></td>
                    <td><a href="/solution?uid=${solution.userId}&eid=${solution.exerciseId}">szczegóły</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>