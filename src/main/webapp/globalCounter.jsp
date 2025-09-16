<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 16.
  Time: 오후 3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GlobalCounter</title>
</head>
<body>
    <%!
        private long counter = 0;
        private long increaseCounter(){
            return ++counter;
        }
        public void jspInit(){
            counter=100;
        }
    %>
    <h1>counter:<%=increaseCounter()%></h1>
</body>
</html>
