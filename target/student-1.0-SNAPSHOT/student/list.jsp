<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 17.
  Time: 오전 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <title>학생 - 리스트</title>
    <link rel="stylesheet" href="./style.css">
</head>
<body>
    <h1>학생 리스트</h1>
    <p><a href="/student/register.do">학생(등록)</a></p>
    <table border="1">
        <thead>
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>성별</th>
                <th>나이</th>
                <th>cmd</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${studentList}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.gender}</td>
                    <td>${student.age}</td>
                    <td>
                        <c:url var="view_link" value="/student/view.do" scope="request">
                            <c:param name="id" value="${student.id}" />
                        </c:url>
                        <a href="${view_link}">조회</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
