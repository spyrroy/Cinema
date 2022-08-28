<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<l:setLocale/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="index.title"/></title>
    <link href="css/styles.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="fragments/header.jspf" %>
<h2>All Films</h2>
<c:choose>
    <c:when test="${empty films}">
        <p> No Films</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="film" items="${films }">
                <li>
                        ${film }
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>


</body>
</html>