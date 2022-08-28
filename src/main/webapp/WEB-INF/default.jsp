<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<!-- html 5 -->
<html>
<head>
  <meta charset="UTF-8">
  <title><fmt:message key="index.title"/> </title>
  <link href="css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<p><a href="app?cmd=addFilmForm"><fmt:message key="index.link.addFilm"/></a></body>
</body>
</html>