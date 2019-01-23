<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 15/12/18
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="pl.coderslab.model.Solution" %>
<%@ page import="pl.coderslab.model.Exercise" %>
<%@ page import="pl.coderslab.model.User" %>
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
    <c:if test="${empty solutions}">
        <h3>Brak rozwiązań</h3>
    </c:if>
    <c:if test="${not empty solutions}">
        <h3>Ostatenie rozwiązania</h3>
        <table>
            <tr>
                <th>Tytuł zadania</th>
                <th>Autor rozwiązania</th>
                <th>Data dodania</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="solution" items="${solutions}">
                <%
                    Solution solution = (Solution) pageContext.getAttribute("solution");
                    Exercise exercise = solution.getExercise();
                    User author = solution.getAuthor();
                %>
                <tr>
                    <td><%= exercise.getTitle() %></td>
                    <td><%= author.getFirstName() %> <%= author.getLastName() %></td>
                    <td><fmt:formatDate value="${solution.updatedAt}" pattern="yyyy-MM-dd, HH:mm:ss" /></td>
                    <td><a href="/solution?uid=${solution.userId}&eid=${solution.exerciseId}&home">szczegóły</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>