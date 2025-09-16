<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 16.
  Time: 오후 3:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SessionCounter</title>
</head>
<body>
    <%
        Long counter = 0l;
        if(Objects.nonNull(session.getAttribute("counter"))){
            counter = (Long) session.getAttribute("counter");
        }

        session.setAttribute("counter", ++counter);
    %>
<h1>
    counter : <%= counter%>
</h1>
</body>
</html>
