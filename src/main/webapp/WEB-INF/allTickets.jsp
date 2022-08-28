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
            <a href="app?cmd=addSessionForm&date=${date}" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new Session</a>
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
                        <a class='btn btn-info btn-xs' href="app?cmd=addSessionForm&id=${session.id}"><span
                                class="glyphicon glyphicon-edit"></span> Edit</a>
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