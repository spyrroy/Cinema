<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<l:setLocale/>

<!DOCTYPE html>
<!-- html 5 -->
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="index.title"/></title>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<p>
    <%@ include file="/WEB-INF/fragments/header.jspf" %>
    <c:if test="${user.role.role == 'Admin'}">
<p><a href="app?cmd=addFilmForm"><fmt:message key="index.link.addFilm"/></a></p>
<p><a href="app?cmd=addSessionDateForm">Add session</a></p>
</c:if>
<p><a href="app?cmd=allFilms">View films</a></p>

<p><a href="app?cmd=allSessions">View sessions</a></p>
<c:if test="${user != null}">
    <p><a href="app?cmd=allTickets">View tickets</a></p>
</c:if>
</body>
</html>