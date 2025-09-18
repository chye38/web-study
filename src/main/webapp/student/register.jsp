<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn47
  Date: 25. 9. 17.
  Time: 오후 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>학생 - 등록</title>
    <link rel="stylesheet" href="./style.css">
</head>
<body>
    <c:choose>
        <c:when test="${empty student}">
            <c:set var="action" value="/student/register.do" />
        </c:when>
        <c:otherwise>
            <c:set var="action" value="/student/update.do" />
        </c:otherwise>
    </c:choose>

    <form action="${action}" method="post">
        <table border="1">
            <tr>
                <th>ID</th>
                <td><input type="text" name="id" value="${student.id}"
                    ${empty student.id ? 'required' : 'readonly'}
                ></td>
            </tr>
            <tr>
                <th>이름</th>
                <td><input type="text" name="name" value="${student.name}" required></td>
            </tr>
            <tr>
                <th>성별</th>
                <td>
                    <c:set var="gender" value="${student.gender}" />
                    <input type="radio" name="gender" id="gender_male" value="M"
                           ${gender == "M" ? 'checked' : ''}
                    >
                    남성
                    <input type="radio" name="gender" id="gender_female" value="F"
                            ${gender == "F" ? 'checked' : ''}
                    >
                    여성
                </td>
            </tr>
            <tr>
                <th>나이</th>
                <td><input type="text" name="age" id="age" value="${student.age}" required></td>
            </tr>
        </table>
        <p>
            <button type="submit">
                <c:choose>
                    <c:when test="${empty student}">
                        등록
                    </c:when>
                    <c:otherwise>
                        수정
                    </c:otherwise>
                </c:choose>
            </button>
        </p>
    </form>
</body>
</html>
