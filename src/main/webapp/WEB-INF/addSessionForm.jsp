<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<l:setLocale/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="index.title"/></title>

</head>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<h3>Add Session</h3>
<form action="app" method="post">
    <input type="hidden" name="cmd" value="addSession"><br>
    <input type="hidden" name="date" value="${date}"><br>
    Session date: ${date} <br>
    Session time:
    <ul>

        <c:forEach var="time" items="${times}">
            <c:set var="isFree" value="true"/>
            <c:forEach var="occupiedTime" items="${occupiedTimes}">
                <c:if test="${occupiedTime == time}">
                    <c:set var="isFree" value="false"/>
                </c:if>
            </c:forEach>


            <c:choose>
                <c:when test="${isFree == false}">
                    <li>
                        <input type="radio" name="time" value="${time}" disabled>
                        ${time}
                        <div class="check"></div>
                    </li>
                </c:when>

                <c:otherwise>
                    <li>
                        <input type="radio" name="time" value="${time}">
                            ${time}
                        <div class="check"></div>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%--    <li>--%>
        <%--        <input type="radio" name="time" value="09:00">--%>
        <%--        9:00--%>
        <%--        <div class="check"></div>--%>
        <%--    </li>--%>
<%--        <li>--%>
<%--            <input type="radio" name="time" value="12:00">--%>
<%--            12:00--%>
<%--            <div class="check"></div>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <input type="radio" name="time" value="16:00">--%>
<%--            16:00--%>
<%--            <div class="check"></div>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <input type="radio" name="time" value="19:00">--%>
<%--            19:00--%>
<%--            <div class="check"></div>--%>
<%--        </li>--%>

<%--        <li>--%>
<%--            <input type="radio" name="time" value="22:00">--%>
<%--            22:00--%>
<%--            <div class="check"></div>--%>
<%--        </li>--%>

    </ul>
    <select name="filmId" id="film" class="form-control" required>
        <c:forEach var="film" items="${films}">
            <option value="${film.id}">${film.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Add">
</form>
</body>
</html>