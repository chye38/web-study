<%--
  Created by IntelliJ IDEA.
  User: chye
  Date: 25. 10. 2.
  Time: 오전 4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="d-flex justify-content-between mb-3">
    <h2>상품 관리</h2>
    <a href="/product/add.do" class="btn btn-primary">상품 추가</a>
</div>

<table class="table table-bordered table-hover">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>상품명</th>
        <th>가격</th>
        <th>재고</th>
        <th>관리</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.id}</td>
            <td>
                <a href="/product/view.do?id=${product.id}">${product.name}</a>
            </td>
            <td><fmt:formatNumber value="${product.price}" type="currency"/></td>
            <td>${product.stock}</td>
            <td>
                <a href="/product/edit.do?id=${product.id}" class="btn btn-sm btn-warning">수정</a>
                <a href="/product/delete.do?id=${product.id}" class="btn btn-sm btn-danger" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty productList}">
        <tr>
            <td colspan="5" class="text-center">등록된 상품이 없습니다.</td>
        </tr>
    </c:if>
    </tbody>
</table>
