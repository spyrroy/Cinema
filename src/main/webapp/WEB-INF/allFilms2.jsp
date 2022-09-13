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
    <div class="row col-md-6 col-md-offset-2 custyle">
        <table class="table table-striped custab">
            <thead>
            <a href="app?cmd=addFilmForm" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new Film</a>
            <tr>
                <th>Number</th>
                <th>Name</th>
                <th>Description</th>
                <th>Duration</th>
                <th class="text-center">Action</th>
            </tr>
            </thead>

            <c:forEach items="${films}" var="film" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${film.name}</td>
                    <td>${film.description}</td>
                    <td>${film.duration}</td>
                    <td class="text-center"><a class='btn btn-info btn-xs' href="app?cmd=addFilmForm&id=${film.id}" ><span class="glyphicon glyphicon-edit"></span> Edit</a>
                        <a href="app?cmd=deleteFilm&id=${film.id}" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a></td>
                    </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>