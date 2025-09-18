<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 17.
  Time: 오전 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>학생 - 조회</title>
</head>
<link rel="stylesheet" href="./style.css">
<style>
    ul{
        list-style: none;
    }
    ul li{
        display: inline-block;
    }
</style>
<body>
    <table border="1">
        <tbody>
        <tr>
            <th>아이디</th>
            <td>${student.id}</td>
        </tr>
        <tr>
            <th>이름</th>
            <td>${student.name}</td>
        </tr>
        <tr>
            <th>성별</th>
            <td>${student.gender}</td>
        </tr>
        <tr>
            <th>나이</th>
            <td>${student.age}</td>
        </tr>
        <tr>
            <th>등록일</th>
            <td>${student.dateTime}</td>
        </tr>
        </tbody>
    </table>
    <ul>
        <li><a href="/student/list.do">리스트</a></li>
        <li>
            <c:url var="update_link" value="/student/update.do">
                <c:param name="id" value="${student.id}" />
            </c:url>
            <a href="${update_link}">수정</a>
        </li>
        <li>
            <form action="/student/delete.do" method="post">
                <input type="hidden" name="id" value="${student.id}">
                <input type="submit" value="삭제">
            </form>
        </li>
    </ul>
</body>
</html>