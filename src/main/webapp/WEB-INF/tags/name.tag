<%@ tag language="java" pageEncoding="UTF-8"%>
        <%@ attribute name="id" type="java.lang.Integer" %>

<hr>
ID: ${id}
<hr>

<%
    String lang = request.getParameter("lang");
%>