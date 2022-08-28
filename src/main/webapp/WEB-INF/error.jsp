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
<%@ include file="fragments/header.jspf"%>


<h3>Something went wrong</h3>
<p>
    <${exception.getMessage() }
</p>

</body>
</html>