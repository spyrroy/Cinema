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
<h3>Add Film</h3>
<form action="app" method="post">
    <input type="hidden" name="cmd" value="editFilm"><br>
    <input type="hidden" name="id" value="${film.id}"><br>
    Film name: <input name="name" value="${film.name}"><br>
    Film description: <input name="description" value="${film.description}"><br>
    Film duration: <input name="duration" value="${film.duration}"><br>
    Film genre: <input name="genre" value="${film.genre.id}"><br>
    <input type="submit" value="Edit">
</form>
</body>
</html>