<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 16.
  Time: 오후 5:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:set var="email" value="marco@nhnacademy.com" scope="request" />
    <c:set var="name">marco</c:set>
    <c:out value="1" /><br />
    <c:out value="${email}" /><br />
    <c:out value="${pageScope.email}" /><br />
    <c:out value="${requestScope.email}" /><br />
    <c:out value="${name}" /><br />
    <c:remove var="name" />    removedName:<c:out value="${name}" /><br />
</body>
</html>
