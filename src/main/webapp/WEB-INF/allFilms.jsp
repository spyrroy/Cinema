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
<h2>All products</h2>
<c:choose>
    <c:when test="${empty products}">
        <p> No products</p>
    </c:when>
    <c:otherwise>


        <ul>
            <c:forEach var="product" items="${products }">
                <li>
                        ${product }
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

</body>
</html>