<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="jakarta.servlet.jsp.jstl.core.Config" %>
<%@ tag import="java.util.Arrays" %>
<%@ tag import="jakarta.servlet.http.Cookie" %>

<%
    Cookie[] cookies = request.getCookies();
    String defaultLocale = Arrays.stream(cookies)
            .filter(c -> "defaultLocale".equals(c.getName()))
            .findAny()
            .map(c -> c.getValue())
            .orElse((String) request.getServletContext().getAttribute("jakarta.servlet.jsp.jstl.fmt.locale"));
    Config.set(request, "jakarta.servlet.jsp.jstl.fmt.locale", defaultLocale);
//    request.setAttribute("defaultLocale", defaultLocale);
%>