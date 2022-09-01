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

<%--            <ul>--%>
<%--                <li>--%>
                    <form action="app" method="get">
                        <input type="hidden" name="cmd" value="allSessions"><br>
                        <select name="date">Date
                            <option value="${date}">Date:</option>
                            <c:forEach items="${dates}" var="date">
                                <option value="${date}">${date}</option>
                            </c:forEach>
                        </select>
                        <select name="orderBy">
                            <option value="${orderBy}">Order By:</option>
<%--                            <option value="time">Start time</option>--%>
                            <option value="film_name">Film name</option>
                            <option value="free_seats">Free seats</option>
                        </select>
                        <select name="direction">
                            <option value="${direction}">Direction:</option>
                            <option value="ASC">Asc</option>
                            <option value="DESC">Desc</option>
                        </select>
                        <button type="submit" class="btn btn-info btn-sm">Sort</button>
                    </form>
<%--                </li>--%>
<%--            </ul>--%>

        <table class="table table-striped custab">
            <thead>
            <c:if test="${user.role.role == 'Admin'}">
            <a href="app?cmd=addSessionDateForm" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new Session</a>
            </c:if>
            <tr>
                <th>Number</th>
                <th>Date</th>
                <th>Time</th>
                <th>Film</th>
                <th>Free seats</th>
                <th class="text-center">Action</th>
                <th class="text-center">Ticket</th>
            </tr>
            </thead>

            <c:forEach items="${sessions}" var="session" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${session.date}</td>
                    <td>${session.time}</td>
                    <td>${session.film.name}</td>
                    <td>${session.freeSeats}</td>
                    <td class="text-center">
                        <a class="btn btn-danger btn-xs" href="app?cmd=deleteSession&id=${session.id}"><span
                                class="glyphicon glyphicon-remove"></span> Del</a></td>
                    <td class="text-center">
                        <a class="btn btn-primary btn-xs" href="app?cmd=addTicketForm&id=${session.id}"><span
                                class="glyphicon glyphicon-shopping-cart"></span> Buy ticket</a></td>

                </tr>

            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>