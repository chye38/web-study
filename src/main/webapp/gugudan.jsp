<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 16.
  Time: 오후 3:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>구구단</title>
</head>
<body>

<%!
    int i = 1;
%>
<%!
    public void minus() {
        i--;
    }

    public void plus() {
        i++;
    }
%>
<button onclick="<%=minus()%>">minus</button>
<button onclick="<%=plus()%>">plus</button>

    <!-- <%= i %>단 시작 - html comment -->
    <%-- <%= i %>단 시작 - Scriptlet comment --%>
    <h1><%=i%>단</h1>
    <%
        for(int j=1; j<=9; j++){
    %>

    <p><%=i%> x <%=j%> = <%=i*j%></p>

    <%
        }
    %>
</body>
</html>
