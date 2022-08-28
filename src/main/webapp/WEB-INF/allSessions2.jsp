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
    <div class="timetable-img text-center">
        <img src="img/content/timetable.png" alt="">
    </div>
    <div class="table-responsive">
        <table class="table table-bordered text-center">
            <thead>
            <tr class="bg-light-gray">
                <th class="text-uppercase">Time</th>
                <th class="text-uppercase">Monday</th>
                <th class="text-uppercase">Tuesday</th>
                <th class="text-uppercase">Wednesday</th>
                <th class="text-uppercase">Thursday</th>
                <th class="text-uppercase">Friday</th>
                <th class="text-uppercase">Saturday</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="align-middle">09:00am</td>
                <c:forEach items="${sessions}" var="session" varStatus="loop">

            <tr>
<%--                <c:when test="${formatedTime == '09:00'}">--%>
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
                        <a class="btn btn-primary btn-xs" href="app?cmd=addTicket&id=${session.id}"><span
                                class="glyphicon glyphicon-shopping-cart"></span> Buy ticket</a></td>
<%--                </c:when>--%>
            </tr>

            </c:forEach>
            </tr>

            <tr>
                <td class="align-middle">15:00am</td>
<%--                <c:when test="${session.time == '15:00'}">--%>
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
                        <a class="btn btn-primary btn-xs" href="app?cmd=addTicket&id=${session.id}"><span
                                class="glyphicon glyphicon-shopping-cart"></span> Buy ticket</a></td>
<%--                </c:when>--%>
            </tr>

            <tr>
                <td class="align-middle">11:00am</td>
                <td>
                    <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                    <div class="margin-10px-top font-size14">11:00-12:00</div>
                </td>
                <td>
                    <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                    <div class="margin-10px-top font-size14">11:00-12:00</div>
                </td>
                <td>
                    <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                    <div class="margin-10px-top font-size14">11:00-12:00</div>
                </td>
                <td>
                    <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                    <div class="margin-10px-top font-size14">11:00-12:00</div>
                </td>
                <td>
                    <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                    <div class="margin-10px-top font-size14">11:00-12:00</div>
                </td>
                <td>
                    <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                    <div class="margin-10px-top font-size14">11:00-12:00</div>
                </td>
            </tr>

            <tr>
                <td class="align-middle">12:00pm</td>
                <td class="bg-light-gray">

                </td>
                <td>
                    <span class="bg-purple padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Art</span>
                    <div class="margin-10px-top font-size14">12:00-1:00</div>
                    <div class="font-size13 text-light-gray">Kate Alley</div>
                </td>
                <td>
                    <span class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Dance</span>
                    <div class="margin-10px-top font-size14">12:00-1:00</div>
                    <div class="font-size13 text-light-gray">Ivana Wong</div>
                </td>
                <td>
                    <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                    <div class="margin-10px-top font-size14">12:00-1:00</div>
                    <div class="font-size13 text-light-gray">Ivana Wong</div>
                </td>
                <td class="bg-light-gray">

                </td>
                <td>
                    <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                    <div class="margin-10px-top font-size14">12:00-1:00</div>
                    <div class="font-size13 text-light-gray">Marta Healy</div>
                </td>
            </tr>

            <tr>
                <td class="align-middle">01:00pm</td>
                <td>
                    <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                    <div class="margin-10px-top font-size14">1:00-2:00</div>
                    <div class="font-size13 text-light-gray">James Smith</div>
                </td>
                <td>
                    <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                    <div class="margin-10px-top font-size14">1:00-2:00</div>
                    <div class="font-size13 text-light-gray">Ivana Wong</div>
                </td>
                <td class="bg-light-gray">

                </td>
                <td>
                    <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                    <div class="margin-10px-top font-size14">1:00-2:00</div>
                    <div class="font-size13 text-light-gray">James Smith</div>
                </td>
                <td>
                    <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                    <div class="margin-10px-top font-size14">1:00-2:00</div>
                    <div class="font-size13 text-light-gray">Marta Healy</div>
                </td>
                <td>
                    <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                    <div class="margin-10px-top font-size14">1:00-2:00</div>
                    <div class="font-size13 text-light-gray">Ivana Wong</div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>