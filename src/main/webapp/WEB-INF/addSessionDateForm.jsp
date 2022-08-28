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
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<h3>Select Date</h3>
<form action="app" method="post">
    <input type="hidden" name="cmd" value="addSessionForm"><br>
    Session date: <input type="date" name="date"><br>
    <input type="submit" value="Continue">
</form>
</body>
</html>