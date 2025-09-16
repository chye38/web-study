<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 16.
  Time: 오후 4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> forward vs include </title>
</head>
<body>
<%
    String id = "marco";
%>

    <h1>THIS IS pageContext.jsp.</h1>
<%
    String type = request.getParameter("type");
    if("include".equalsIgnoreCase(type)){
%>
        <jsp:include page="sub.jsp">
            <jsp:param name="id" value="<%=id%>"/>
        </jsp:include>
<%
    }else if("forward".equalsIgnoreCase(type)){
%>
        <jsp:forward page="sub.jsp">
            <jsp:param name="id" value="<%=id%>"/>
        </jsp:forward>
<%
    }else{
        out.println("type parameter is needed.");
    }
%>

    <p>
        pageContext : Id is <%=request.getParameter("id")%>
    </p>
</body>
</html>