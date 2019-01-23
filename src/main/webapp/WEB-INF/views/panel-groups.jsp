<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 03/01/19
  Time: 18:22
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
    <a href="/panel">Powrót</a>
    <h3>Zarządzanie grupami użytkowników</h3>
    <a href="/panel/groups/addedit">Dodaj</a>
    <c:if test="${empty groups}">
        <h3>Brak grup</h3>
    </c:if>
    <c:if test="${not empty groups}">
        <table>
            <tr>
                <th>Nazwa grupy</th>
                <th>Akcje</th>
            </tr>
            <c:forEach var="group" items="${groups}">
                <tr>
                    <td>${group.name}</td>
                    <td><a href="/panel/groups/addedit?id=${group.id}">Edytuj</a> <a href="/panel/groups/delete?id=${group.id}">Usuń</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>

