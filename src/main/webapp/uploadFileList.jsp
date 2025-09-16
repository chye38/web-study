<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 16.
  Time: 오후 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>업로드된 파일 목록</title>
    <style>
        table {
            width: 800px;
            border-collapse: collapse;
            border:1px #ccc solid;
        }
        table tr>td,th{
            padding:5px;
            border:1px #ccc solid;
        }
    </style>
</head>
<body>
    <h1>업로드된 파일 목록</h1>
    <c:set var="fileList" value="${fileList}" />
    <p>총 <b>${fileList.length()}</b>개의 파일이 업로드되어 있습니다.</p>
    <button></button>
</body>
</html>
