<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<l:setLocale/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="index.title"/></title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>

<div class="container">
    <div class="row col-md-9 col-md-offset-2 custyle">
        <table class="table table-striped custab">
            <thead>
            <tr>
                <th>Number</th>
                <th>Date</th>
                <th>Time</th>
                <th>Film</th>
                <th>Seat number</th>
                <th>Date of purchase</th>
            </tr>
            </thead>

            <c:forEach items="${tickets}" var="ticket" varStatus="loop">
                <c:set var="session" value="${ticket.session}"/>
                <c:set var="film" value="${session.film}"/>
                <c:set var="seat" value="${ticket.seat}"/>
                <tr>
                    <td>${loop.count}</td>
                    <td>${session.date}</td>
                    <td>${session.time}</td>
                    <td>${film.name}</td>
                    <td>${seat.number}</td>
                    <td>${ticket.date}</td>

                </tr>

            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>